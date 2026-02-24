package org.example.service;

import org.example.model.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class LoadOptimizerService {
 public OptimizeResponseRecord optimize(OptimizeRequest request) {
 Objects.requireNonNull(request, "Request must not be null");
 Objects.requireNonNull(request.truck(), "Truck must not be null");
 Objects.requireNonNull(request.orders(), "Orders must not be null");
 if (request.orders().isEmpty()) return emptyResponse(request.truck());
 var compatible = filterCompatibleOrders(request.orders());
 return findBestLoad(request.truck(), compatible);
 }

 private List<Order> filterCompatibleOrders(List<Order> orders) {
 if (orders.isEmpty()) return List.of();
 var origin = orders.get(0).origin();
 var dest = orders.get(0).destination();
 return orders.stream()
 .filter(o -> origin.equals(o.origin()) && dest.equals(o.destination()) && !o.pickupDate().isAfter(o.deliveryDate()))
 .toList();
 }

 private OptimizeResponseRecord findBestLoad(Truck truck, List<Order> orders) {
 int n = orders.size(), bestMask = 0;
 long bestPayout = 0, bestWeight = 0, bestVolume = 0;
 for (int mask = 1; mask < (1 << n); mask++) {
 long sumWeight = 0, sumVolume = 0, sumPayout = 0;
 boolean hazmat = false, hazmatConflict = false;
 for (int i = 0; i < n; i++) if ((mask & (1 << i)) != 0) {
 var o = orders.get(i);
 sumWeight += o.weightLbs();
 sumVolume += o.volumeCuft();
 sumPayout += o.payoutCents();
 if (o.isHazmat()) { if (hazmat) hazmatConflict = true; hazmat = true; }
 }
 if (sumWeight <= truck.maxWeightLbs() && sumVolume <= truck.maxVolumeCuft() && !hazmatConflict && sumPayout > bestPayout) {
 bestPayout = sumPayout; bestWeight = sumWeight; bestVolume = sumVolume; bestMask = mask;
 }
 }
 var selectedIds = new ArrayList<String>();
 for (int i = 0; i < n; i++) if ((bestMask & (1 << i)) != 0) selectedIds.add(orders.get(i).id());
 double utilWeight = truck.maxWeightLbs() == 0 ? 0 : (100.0 * bestWeight / truck.maxWeightLbs());
 double utilVolume = truck.maxVolumeCuft() == 0 ? 0 : (100.0 * bestVolume / truck.maxVolumeCuft());
 return new OptimizeResponseRecord(
 truck.id(), selectedIds, bestPayout, bestWeight, bestVolume,
 Math.round(utilWeight * 100.0) / 100.0, Math.round(utilVolume * 100.0) / 100.0
 );
 }

 private OptimizeResponseRecord emptyResponse(Truck truck) {
 return new OptimizeResponseRecord(truck != null ? truck.id() : null, List.of(), 0, 0, 0, 0, 0);
 }
}
