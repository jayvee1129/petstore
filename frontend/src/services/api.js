let API_BASE_URL = import.meta.env.VITE_API_BASE_URL || `${location.protocol}//${location.hostname}${location.port ? ':' + location.port : ''}`;
// Ensure /salac is appended if not already present
if (!API_BASE_URL.endsWith('/salac')) {
    API_BASE_URL = API_BASE_URL.replace(/\/$/, '') + '/salac';
}

class ApiClient {
    constructor() {
        this.baseURL = API_BASE_URL;
    }

    async request(endpoint, options = {}) {
        const url = `${this.baseURL}${endpoint}`;
        const config = {
            headers: {
                'Content-Type': 'application/json',
                ...options.headers,
            },
            ...options,
        };

        try {
            const response = await fetch(url, config);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const text = await response.text();
            return text ? JSON.parse(text) : null;
        } catch (error) {
            console.error('API request failed:', error);
            throw error;
        }
    }

    // Pets endpoints
    async getPets(params = {}) {
        const queryString = new URLSearchParams(params).toString();
        const endpoint = queryString ? `/pets?${queryString}` : '/pets';
        return this.request(endpoint);
    }

    async getPet(id) {
        return this.request(`/pets/${id}`);
    }

    async createPet(pet) {
        return this.request('/pets', {
            method: 'POST',
            body: JSON.stringify(pet),
        });
    }

    async updatePet(id, pet) {
        return this.request(`/pets/${id}`, {
            method: 'PUT',
            body: JSON.stringify(pet),
        });
    }

    async deletePet(id) {
        return this.request(`/pets/${id}`, {
            method: 'DELETE',
        });
    }

    // Cart endpoints
    async getCart() {
        return this.request('/cart');
    }

    async addToCart(petId) {
        return this.request('/cart', {
            method: 'POST',
            body: JSON.stringify({ petId }),
        });
    }

    async removeFromCart(petId) {
        return this.request(`/cart/${petId}`, {
            method: 'DELETE',
        });
    }

    // Wishlist endpoints
    async getWishlist() {
        return this.request('/wishlist');
    }

    async addToWishlist(petId) {
        return this.request('/wishlist', {
            method: 'POST',
            body: JSON.stringify({ petId }),
        });
    }

    async removeFromWishlist(petId) {
        return this.request(`/wishlist/${petId}`, {
            method: 'DELETE',
        });
    }
}

export default new ApiClient();