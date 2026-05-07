# Data Model: PetStore Product Gallery

## Entities

### Pet
Represents an individual pet listing in the storefront.

- `id` (UUID): Unique pet identifier.
- `name` (string): Pet name.
- `type` (enum): One of `DOG`, `CAT`, `BIRD`, `FISH`.
- `breed` (string): Breed or species description.
- `ageMonths` (integer): Age in months.
- `priceCents` (integer): Price expressed in cents to avoid floating-point rounding.
- `availability` (enum): `IN_STOCK`, `SOLD_OUT`, or `PENDING`.
- `description` (string): Full description for the detail view.
- `imageUrl` (string): Primary image URL for the gallery card.
- `healthNotes` (string, optional): Wellness or care notes.
- `createdAt` (timestamp): Time when the listing was created.
- `updatedAt` (timestamp): Time when the listing was last updated.

### CartItem
Represents a pet selected for the shopper's cart.

- `id` (UUID): Unique cart item identifier.
- `petId` (UUID): Reference to the selected pet.
- `addedAt` (timestamp): Time the pet was added to the cart.
- `quantity` (integer): Quantity reserved; default is `1` for individual pets.
- `sessionId` (string, optional): Session-scoped owner identifier if supporting anonymous cart state.

### WishlistItem
Represents a pet saved for later consideration.

- `id` (UUID): Unique wishlist item identifier.
- `petId` (UUID): Reference to the chosen pet.
- `addedAt` (timestamp): Time the pet was added to the wishlist.
- `sessionId` (string, optional): Session-scoped owner identifier if supporting anonymous wishlist state.

### FilterCriteria
Represents the user's search and filter selections.

- `search` (string, optional): Search text matching pet name, breed, or type.
- `type` (enum[], optional): One or more selected pet types.
- `priceMinCents` (integer, optional): Minimum price filter.
- `priceMaxCents` (integer, optional): Maximum price filter.
- `ageMinMonths` (integer, optional): Minimum age filter.
- `ageMaxMonths` (integer, optional): Maximum age filter.
- `availability` (enum[], optional): Selected availability statuses.

## Relationships

- A `Pet` may appear in zero or more `CartItem` entries.
- A `Pet` may appear in zero or more `WishlistItem` entries.
- `CartItem` and `WishlistItem` may be linked by `sessionId` for anonymous users.

## Validation Rules

- `name` MUST be present and non-empty.
- `type` MUST be one of the supported pet categories.
- `priceCents` MUST be a positive integer.
- `ageMonths` MUST be a non-negative integer.
- `availability` MUST be a valid status and rendered clearly in the UI.
- `imageUrl` SHOULD be a valid URL or resolved asset path.

## State and Transitions

- `Pet.availability` transitions from `IN_STOCK` to `SOLD_OUT` or `PENDING` as inventory changes.
- `CartItem` and `WishlistItem` are created when a shopper selects Add to Cart or Add to Wishlist.
- Removing an item deletes the corresponding `CartItem` or `WishlistItem` entry.

## Notes

- Because user authentication is out of scope, `sessionId` is optional and can be implemented in a later iteration.
- Cart and wishlist records are intentionally lightweight to keep the initial model focused on discovery and selection.
