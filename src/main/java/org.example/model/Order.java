package org.example.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Order(
 @JsonProperty("id") String id,
 @JsonProperty("payout_cents") long payoutCents,
 @JsonProperty("weight_lbs") long weightLbs,
 @JsonProperty("volume_cuft") long volumeCuft,
 @JsonProperty("origin") String origin,
 @JsonProperty("destination") String destination,
 @JsonProperty("pickup_date") LocalDate pickupDate,
 @JsonProperty("delivery_date") LocalDate deliveryDate,
 @JsonProperty("is_hazmat") boolean isHazmat
) {}
