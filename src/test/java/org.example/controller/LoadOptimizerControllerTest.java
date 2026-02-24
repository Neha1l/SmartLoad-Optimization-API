package org.example.controller;

import org.example.model.*;
import org.example.service.LoadOptimizerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoadOptimizerControllerTest {
 @Mock
 private LoadOptimizerService optimizerService;

 @InjectMocks
 private LoadOptimizerController controller;

 @BeforeEach
 void setUp() {
 MockitoAnnotations.openMocks(this);
 }

 @Test
 void validRequest_shouldReturn200AndResponse() {
 Truck truck = new Truck("truck-1", 10000, 500);
 Order order = new Order("id1", 1000, 200, 50, "A", "B", LocalDate.now(), LocalDate.now().plusDays(1), false);
 OptimizeRequest req = new OptimizeRequest(truck, List.of(order));
 OptimizeResponseRecord resp = new OptimizeResponseRecord("truck-1", List.of("id1"), 1000, 200, 50, 2.0, 10.0);
 when(optimizerService.optimize(req)).thenReturn(resp);
 ResponseEntity<?> response = controller.optimize(req);
 assertEquals(200, response.getStatusCodeValue());
 assertEquals(resp, response.getBody());
 }

 @Test
 void nullRequest_shouldReturn400BadRequest() {
 when(optimizerService.optimize(null)).thenThrow(new IllegalArgumentException("Invalid request"));
 ResponseEntity<?> response = controller.optimize(null);
 assertEquals(400, response.getStatusCodeValue());
 assertTrue(response.getBody().toString().contains("Invalid request"));
 }

 @Test
 void serviceThrowsException_shouldReturn500InternalServerError() {
 Truck truck = new Truck("truck-1", 10000, 500);
 Order order = new Order("id1", 1000, 200, 50, "A", "B", LocalDate.now(), LocalDate.now().plusDays(1), false);
 OptimizeRequest req = new OptimizeRequest(truck, List.of(order));
 when(optimizerService.optimize(req)).thenThrow(new RuntimeException("Unexpected error"));
 ResponseEntity<?> response = controller.optimize(req);
 assertEquals(500, response.getStatusCodeValue());
 assertEquals("Internal error", response.getBody());
 }

 @Test
 void nullTruckInRequest_shouldReturn400BadRequest() {
 OptimizeRequest req = new OptimizeRequest(null, List.of());
 when(optimizerService.optimize(req)).thenThrow(new IllegalArgumentException("Truck required"));
 ResponseEntity<?> response = controller.optimize(req);
 assertEquals(400, response.getStatusCodeValue());
 assertTrue(response.getBody().toString().contains("Truck required"));
 }

 @Test
 void nullOrdersInRequest_shouldReturn400BadRequest() {
 Truck truck = new Truck("truck-1", 10000, 500);
 OptimizeRequest req = new OptimizeRequest(truck, null);
 when(optimizerService.optimize(req)).thenThrow(new IllegalArgumentException("Orders required"));
 ResponseEntity<?> response = controller.optimize(req);
 assertEquals(400, response.getStatusCodeValue());
 assertTrue(response.getBody().toString().contains("Orders required"));
 }

 @Test
 void emptyOrdersInRequest_shouldReturn200AndZeroPayout() {
 Truck truck = new Truck("truck-1", 10000, 500);
 OptimizeRequest req = new OptimizeRequest(truck, List.of());
 OptimizeResponseRecord resp = new OptimizeResponseRecord("truck-1", List.of(), 0, 0, 0, 0, 0);
 when(optimizerService.optimize(req)).thenReturn(resp);
 ResponseEntity<?> response = controller.optimize(req);
 assertEquals(200, response.getStatusCodeValue());
 assertEquals(resp, response.getBody());
 }
}
