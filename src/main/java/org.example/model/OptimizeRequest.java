package org.example.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OptimizeRequest(
    @JsonProperty("truck") Truck truck,
    @JsonProperty("orders") List<Order> orders
) {}
