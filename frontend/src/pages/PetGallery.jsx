import React, { useState, useEffect } from 'react';
import { Container, Grid, Typography, Box, CircularProgress, Alert, Button } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';
import PetCard from '../components/PetCard';
import api from '../services/api';

const PetGallery = () => {
    const [pets, setPets] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchPets = async () => {
            try {
                setLoading(true);
                const data = await api.getPets();
                setPets(data);
                setError(null);
            } catch (err) {
                setError('Error loading pets');
                console.error('Failed to fetch pets:', err);
            } finally {
                setLoading(false);
            }
        };

        fetchPets();
    }, []);

    if (loading) {
        return (
            <Container maxWidth="lg" sx={{ py: 4 }}>
                <Box display="flex" justifyContent="center" alignItems="center" minHeight="400px">
                    <CircularProgress />
                    <Typography variant="h6" sx={{ ml: 2 }}>
                        Loading pets...
                    </Typography>
                </Box>
            </Container>
        );
    }

    if (error) {
        return (
            <Container maxWidth="lg" sx={{ py: 4 }}>
                <Alert severity="error">{error}</Alert>
            </Container>
        );
    }

    return (
        <Container maxWidth="lg" sx={{ py: 4 }}>
            <Typography variant="h3" component="h1" gutterBottom align="center">
                Pet Gallery
            </Typography>
            <Box display="flex" justifyContent="center" sx={{ mb: 2 }}>
                <Button component={RouterLink} to="/pet/new" variant="contained" color="primary">
                    Add New Pet
                </Button>
            </Box>
            <Typography variant="h6" color="text.secondary" align="center" sx={{ mb: 4 }}>
                Discover your perfect companion
            </Typography>

            {pets.length === 0 ? (
                <Box textAlign="center" py={8}>
                    <Typography variant="h5" color="text.secondary">
                        No pets available
                    </Typography>
                </Box>
            ) : (
                <Grid container spacing={3}>
                    {pets.map((pet) => (
                        <Grid item key={pet.id} xs={12} sm={6} md={4} lg={3}>
                            <PetCard pet={pet} />
                        </Grid>
                    ))}
                </Grid>
            )}
        </Container>
    );
};

export default PetGallery;