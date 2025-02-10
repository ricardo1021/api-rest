# Product API

## Description
This API provides two REST services:
1. Filter products by price range.
2. Sort products by price.

## Endpoints

### 1. Filter Products by Price

**Endpoint**: `GET /api/products/filter/price/{initial_range}/{final_range}`

**Parameters**:
- `initial_range`: Minimum price.
- `final_range`: Maximum price.

**Successful Response (200)**:
```json
[
  {
    "barcode": "74001755",
    "item": "Ball Gown",
    "category": "Full Body Outfits",
    "price": 3548,
    "discount": 7,
    "available": 1
  }
]
```
### 2. Sort Products by Price

**Endpoint**: `GET /api/products/sort/price`

**Successful Response (200)**:
```json
[
  {
     "Shawl",
     "Ball Gown"
  }
]
```
## Documentation

### Explanation of Solution

This solution provides an API with two endpoints to filter and sort products by price. The first endpoint allows filtering products by a specified price range and the second endpoint sorts products by price in ascending order.

A global exception handler is implemented to handle any invalid input, such as incorrect price ranges. This ensures that invalid requests are responded to with a `400 Bad Request` and a clear error message.

### Instructions to Run the Project

1. **Clone the repository**:
   ```bash
   git clone https://github.com/ricardo1021/api-rest.git
   ```
