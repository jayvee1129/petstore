# API Contracts: PetStore Product Gallery

## API Base Path

All endpoints are rooted under:

- `GET /salac/pets`
- `GET /salac/pets/{petId}`
- `GET /salac/cart`
- `POST /salac/cart`
- `DELETE /salac/cart/{petId}`
- `GET /salac/wishlist`
- `POST /salac/wishlist`
- `DELETE /salac/wishlist/{petId}`

## Pet Listing

### Request

`GET /salac/pets`

Query parameters:

- `search` (optional): text search over name, breed, or type
- `type` (optional): one or more pet types (`DOG`, `CAT`, `BIRD`, `FISH`)
- `priceMin` (optional): minimum price in cents
- `priceMax` (optional): maximum price in cents
- `ageMin` (optional): minimum age in months
- `ageMax` (optional): maximum age in months
- `availability` (optional): availability filter values (`IN_STOCK`, `SOLD_OUT`, `PENDING`)

### Response

```json
[
  {
    "id": "uuid",
    "name": "Bella",
    "type": "DOG",
    "breed": "Labrador",
    "ageMonths": 24,
    "priceCents": 85000,
    "availability": "IN_STOCK",
    "imageUrl": "https://example.com/images/bella.jpg",
    "description": "Friendly Labrador ready for a loving home."
  }
]
```

## Pet Detail

### Request

`GET /salac/pets/{petId}`

### Response

```json
{
  "id": "uuid",
  "name": "Bella",
  "type": "DOG",
  "breed": "Labrador",
  "ageMonths": 24,
  "priceCents": 85000,
  "availability": "IN_STOCK",
  "imageUrl": "https://example.com/images/bella.jpg",
  "description": "Friendly Labrador ready for a loving home.",
  "healthNotes": "Vaccinated, microchipped.",
  "createdAt": "2026-05-01T12:00:00Z",
  "updatedAt": "2026-05-05T09:30:00Z"
}
```

## Cart

### Get cart contents

`GET /salac/cart`

### Add a pet to cart

`POST /salac/cart`

Body:

```json
{
  "petId": "uuid"
}
```

Response:

```json
{
  "cartItems": [
    {
      "petId": "uuid",
      "name": "Bella",
      "priceCents": 85000,
      "availability": "IN_STOCK",
      "addedAt": "2026-05-06T14:00:00Z"
    }
  ]
}
```

### Remove a pet from cart

`DELETE /salac/cart/{petId}`

Response: `204 No Content`

## Wishlist

### Get wishlist contents

`GET /salac/wishlist`

### Add a pet to wishlist

`POST /salac/wishlist`

Body:

```json
{
  "petId": "uuid"
}
```

Response:

```json
{
  "wishlistItems": [
    {
      "petId": "uuid",
      "name": "Bella",
      "priceCents": 85000,
      "availability": "IN_STOCK",
      "addedAt": "2026-05-06T14:00:00Z"
    }
  ]
}
```

### Remove a pet from wishlist

`DELETE /salac/wishlist/{petId}`

Response: `204 No Content`

## Error behavior

- `404 Not Found` when a requested `petId` does not exist.
- `400 Bad Request` for invalid query parameter values or malformed JSON.
- `409 Conflict` if attempting to add an unavailable pet to cart.

## UI contract notes

- The frontend will treat the cart and wishlist endpoints as session-scoped state.
- The gallery page consumes `GET /salac/pets` with query parameters and renders cards.
- The detail page consumes `GET /salac/pets/{petId}` and dispatches cart/wishlist actions.
