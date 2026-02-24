# SmartLoad Optimization API

## How to run

```bash
git clone <your-repo>
cd <folder>
docker compose up --build
# → Service will be available at http://localhost:8080
```

## Health check

```
curl http://localhost:8080/actuator/health
```

## Example request

```
curl -X POST http://localhost:8080/api/v1/load-optimizer/optimize \
-H "Content-Type: application/json" \
-d @sample-request.json
```

## Sample request JSON

```
{
  "truck": {
    "id": "truck-123",
    "max_weight_lbs": 44000,
    "max_volume_cuft": 3000
  },
  "orders": [
    {
      "id": "ord-001",
      "payout_cents": 250000,
      "weight_lbs": 18000,
      "volume_cuft": 1200,
      "origin": "Los Angeles, CA",
      "destination": "Dallas, TX",
      "pickup_date": "2025-12-05",
      "delivery_date": "2025-12-09",
      "is_hazmat": false
    },
    {
      "id": "ord-002",
      "payout_cents": 180000,
      "weight_lbs": 12000,
      "volume_cuft": 900,
      "origin": "Los Angeles, CA",
      "destination": "Dallas, TX",
      "pickup_date": "2025-12-04",
      "delivery_date": "2025-12-10",
      "is_hazmat": false
    },
    {
      "id": "ord-003",
      "payout_cents": 320000,
      "weight_lbs": 30000,
      "volume_cuft": 1800,
      "origin": "Los Angeles, CA",
      "destination": "Dallas, TX",
      "pickup_date": "2025-12-06",
      "delivery_date": "2025-12-08",
      "is_hazmat": true
    }
  ]
}
```

## Notes
- The service is stateless and does not use a database.
- All money is handled in integer cents (no float/double).
- The service listens on port 8080.
- See the source code for details on error handling, edge cases, and performance.

  
