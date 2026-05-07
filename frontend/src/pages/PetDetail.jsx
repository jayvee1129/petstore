import React, { useEffect, useState } from 'react';
import { useNavigate, useParams, Link as RouterLink } from 'react-router-dom';
import {
    Container,
    Typography,
    Box,
    CircularProgress,
    Alert,
    Card,
    CardMedia,
    CardContent,
    Button,
    Chip,
} from '@mui/material';
import api from '../services/api';

const PetDetail = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [pet, setPet] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [statusMessage, setStatusMessage] = useState('');
    const [actionError, setActionError] = useState(null);

    const handleAddToCart = async () => {
        try {
            setActionError(null);
            await api.addToCart(pet.id);
            setStatusMessage('Added to your cart.');
        } catch (err) {
            setActionError('Unable to add pet to cart.');
            console.error('Add to cart failed:', err);
        }
    };

    const handleAddToWishlist = async () => {
        try {
            setActionError(null);
            await api.addToWishlist(pet.id);
            setStatusMessage('Added to your wishlist.');
        } catch (err) {
            setActionError('Unable to add pet to wishlist.');
            console.error('Add to wishlist failed:', err);
        }
    };

    const handleDeletePet = async () => {
        if (!window.confirm('Delete this pet? This cannot be undone.')) {
            return;
        }

        try {
            await api.deletePet(pet.id);
            navigate('/');
        } catch (err) {
            setActionError('Unable to delete pet.');
            console.error('Delete pet failed:', err);
        }
    };

    useEffect(() => {
        const fetchPet = async () => {
            try {
                setLoading(true);
                const data = await api.getPet(id);
                setPet(data);
                setError(null);
            } catch (err) {
                setError('Unable to load pet details.');
                console.error('Failed to fetch pet:', err);
            } finally {
                setLoading(false);
            }
        };

        if (id) {
            fetchPet();
        }
    }, [id]);

    if (loading) {
        return (
            <Container maxWidth="md" sx={{ py: 4 }}>
                <Box display="flex" justifyContent="center" alignItems="center" minHeight="400px">
                    <CircularProgress />
                    <Typography variant="h6" sx={{ ml: 2 }}>
                        Loading pet details...
                    </Typography>
                </Box>
            </Container>
        );
    }

    if (error) {
        return (
            <Container maxWidth="md" sx={{ py: 4 }}>
                <Alert severity="error">{error}</Alert>
                <Box textAlign="center" mt={3}>
                    <Button component={RouterLink} to="/" variant="contained" color="primary">
                        Back to Gallery
                    </Button>
                </Box>
            </Container>
        );
    }

    if (!pet) {
        return (
            <Container maxWidth="md" sx={{ py: 4 }}>
                <Alert severity="info">No pet found.</Alert>
                <Box textAlign="center" mt={3}>
                    <Button component={RouterLink} to="/" variant="contained" color="primary">
                        Back to Gallery
                    </Button>
                </Box>
            </Container>
        );
    }

    return (
        <Container maxWidth="md" sx={{ py: 4 }}>
            <Button component={RouterLink} to="/" variant="outlined" sx={{ mb: 3 }}>
                Back to Gallery
            </Button>
            <Card>
                <CardMedia
                    component="img"
                    height="400"
                    image={pet.imageUrl || '/placeholder-pet.jpg'}
                    alt={pet.name}
                    sx={{ objectFit: 'cover' }}
                />
                <CardContent>
                    <Typography variant="h3" component="h1" gutterBottom>
                        {pet.name}
                    </Typography>
                    <Chip
                        label={pet.available ? 'Available' : 'Not Available'}
                        color={pet.available ? 'success' : 'error'}
                        size="medium"
                        sx={{ mb: 2 }}
                    />
                    <Typography variant="h6" color="text.secondary" sx={{ mb: 1 }}>
                        {pet.type} • {pet.breed}
                    </Typography>
                    <Typography variant="body1" sx={{ mb: 2 }}>
                        {pet.description}
                    </Typography>
                    <Typography variant="h5" color="primary" sx={{ mb: 3 }}>
                        ${pet.price}
                    </Typography>
                    {statusMessage && (
                        <Alert severity="success" sx={{ mb: 2 }}>
                            {statusMessage}
                        </Alert>
                    )}
                    {actionError && (
                        <Alert severity="error" sx={{ mb: 2 }}>
                            {actionError}
                        </Alert>
                    )}
                    <Box display="flex" gap={2} flexWrap="wrap" sx={{ mb: 2 }}>
                        <Button component={RouterLink} to="/" variant="contained" color="primary">
                            Back to Gallery
                        </Button>
                        <Button component={RouterLink} to="/gallery" variant="outlined">
                            Gallery
                        </Button>
                        <Button onClick={() => navigate(`/pet/${pet.id}/edit`)} variant="outlined" color="secondary">
                            Edit Pet
                        </Button>
                        <Button onClick={handleDeletePet} variant="contained" color="error">
                            Delete Pet
                        </Button>
                    </Box>
                    <Box display="flex" gap={2} flexWrap="wrap">
                        <Button onClick={handleAddToCart} variant="contained" color="success" disabled={!pet.available}>
                            Add to Cart
                        </Button>
                        <Button onClick={handleAddToWishlist} variant="outlined" color="secondary">
                            Add to Wishlist
                        </Button>
                    </Box>
                </CardContent>
            </Card>
        </Container>
    );
};

export default PetDetail;
