package org.example.model;

import java.util.List;

public record OptimizeResponseRecord(
    String truckId,
    List<String> selectedOrderIds,
    long totalPayoutCents,
    long totalWeightLbs,
    long totalVolumeCuft,
    double utilizationWeightPercent,
    double utilizationVolumePercent
) {}
