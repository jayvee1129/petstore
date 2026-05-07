import { render, screen, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import { vi } from 'vitest';
import PetGallery from '../pages/PetGallery';
import api from '../services/api';

// Mock the API
vi.mock('../services/api');

describe('PetGallery', () => {
    const mockPets = [
        {
            id: 1,
            name: 'Buddy',
            type: 'Dog',
            breed: 'Golden Retriever',
            age: 2,
            price: 500.00,
            description: 'Friendly dog',
            imageUrl: 'https://images.unsplash.com/photo-1517423440428-a5a00ad493e8?auto=format&fit=crop&w=800&q=80',
            available: true
        },
        {
            id: 2,
            name: 'Whiskers',
            type: 'Cat',
            breed: 'Siamese',
            age: 1,
            price: 300.00,
            description: 'Playful cat',
            imageUrl: 'https://images.unsplash.com/photo-1518791841217-8f162f1e1131?auto=format&fit=crop&w=800&q=80',
            available: true
        }
    ];

    beforeEach(() => {
        vi.clearAllMocks();
    });

    test('renders pet gallery with pets', async () => {
        // Mock API response
        api.getPets.mockResolvedValue(mockPets);

        render(
            <MemoryRouter>
                <PetGallery />
            </MemoryRouter>
        );

        // Wait for pets to load
        await waitFor(() => {
            expect(screen.getByText('Buddy')).toBeInTheDocument();
        });

        expect(screen.getByText('Whiskers')).toBeInTheDocument();
        expect(screen.getByText(/Dog/)).toBeInTheDocument();
        expect(screen.getByText(/Cat/)).toBeInTheDocument();
    });

    test('displays loading state initially', () => {
        api.getPets.mockImplementation(() => new Promise(() => {})); // Never resolves

        render(
            <MemoryRouter>
                <PetGallery />
            </MemoryRouter>
        );

        expect(screen.getByText('Loading pets...')).toBeInTheDocument();
    });

    test('displays error message on API failure', async () => {
        api.getPets.mockRejectedValue(new Error('API Error'));

        render(
            <MemoryRouter>
                <PetGallery />
            </MemoryRouter>
        );

        await waitFor(() => {
            expect(screen.getByText('Error loading pets')).toBeInTheDocument();
        });
    });

    test('renders empty state when no pets', async () => {
        api.getPets.mockResolvedValue([]);

        render(
            <MemoryRouter>
                <PetGallery />
            </MemoryRouter>
        );

        await waitFor(() => {
            expect(screen.getByText('No pets available')).toBeInTheDocument();
        });
    });
});