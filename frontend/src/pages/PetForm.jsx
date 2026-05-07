import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import {
    Container,
    Typography,
    Box,
    TextField,
    Button,
    CircularProgress,
    Alert,
    FormControlLabel,
    Switch,
    Grid,
} from '@mui/material';
import api from '../services/api';

const initialFormState = {
    name: '',
    type: '',
    breed: '',
    age: '',
    price: '',
    imageUrl: '',
    description: '',
    available: true,
};

const PetForm = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const isEdit = Boolean(id);
    const [formState, setFormState] = useState(initialFormState);
    const [loading, setLoading] = useState(isEdit);
    const [submitting, setSubmitting] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchPet = async () => {
            try {
                setLoading(true);
                const pet = await api.getPet(id);
                setFormState({
                    name: pet.name || '',
                    type: pet.type || '',
                    breed: pet.breed || '',
                    age: pet.age || '',
                    price: pet.price || '',
                    imageUrl: pet.imageUrl || '',
                    description: pet.description || '',
                    available: pet.available ?? true,
                });
                setError(null);
            } catch (err) {
                setError('Unable to load pet details for editing.');
                console.error('Failed to fetch pet for edit:', err);
            } finally {
                setLoading(false);
            }
        };

        if (isEdit) {
            fetchPet();
        }
    }, [id, isEdit]);

    const handleChange = (field) => (event) => {
        const value = field === 'available' ? event.target.checked : event.target.value;
        setFormState((prev) => ({
            ...prev,
            [field]: value,
        }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        setSubmitting(true);
        setError(null);

        const petPayload = {
            name: formState.name.trim(),
            type: formState.type.trim(),
            breed: formState.breed.trim(),
            age: Number(formState.age),
            price: Number(formState.price),
            imageUrl: formState.imageUrl.trim(),
            description: formState.description.trim(),
            available: formState.available,
        };

        if (!petPayload.name || !petPayload.type || !petPayload.breed || !petPayload.imageUrl) {
            setError('Please complete all required fields.');
            setSubmitting(false);
            return;
        }

        try {
            if (isEdit) {
                await api.updatePet(id, petPayload);
                navigate(`/pet/${id}`);
            } else {
                const createdPet = await api.createPet(petPayload);
                navigate(`/pet/${createdPet.id}`);
            }
        } catch (err) {
            setError('Unable to save pet. Please try again.');
            console.error('Save pet failed:', err);
        } finally {
            setSubmitting(false);
        }
    };

    if (loading) {
        return (
            <Container maxWidth="md" sx={{ py: 4 }}>
                <Box display="flex" justifyContent="center" alignItems="center" minHeight="400px">
                    <CircularProgress />
                    <Typography variant="h6" sx={{ ml: 2 }}>
                        Loading pet data...
                    </Typography>
                </Box>
            </Container>
        );
    }

    return (
        <Container maxWidth="md" sx={{ py: 4 }}>
            <Typography variant="h4" component="h1" gutterBottom>
                {isEdit ? 'Edit Pet' : 'Add New Pet'}
            </Typography>
            {error && (
                <Alert severity="error" sx={{ mb: 3 }}>
                    {error}
                </Alert>
            )}
            <Box component="form" onSubmit={handleSubmit} noValidate>
                <Grid container spacing={3}>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            label="Name"
                            value={formState.name}
                            onChange={handleChange('name')}
                            fullWidth
                            required
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            label="Type"
                            value={formState.type}
                            onChange={handleChange('type')}
                            fullWidth
                            required
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            label="Breed"
                            value={formState.breed}
                            onChange={handleChange('breed')}
                            fullWidth
                            required
                        />
                    </Grid>
                    <Grid item xs={12} sm={3}>
                        <TextField
                            label="Age"
                            type="number"
                            value={formState.age}
                            onChange={handleChange('age')}
                            fullWidth
                            inputProps={{ min: 0 }}
                        />
                    </Grid>
                    <Grid item xs={12} sm={3}>
                        <TextField
                            label="Price"
                            type="number"
                            value={formState.price}
                            onChange={handleChange('price')}
                            fullWidth
                            inputProps={{ min: 0, step: 0.01 }}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            label="Image URL"
                            value={formState.imageUrl}
                            onChange={handleChange('imageUrl')}
                            fullWidth
                            required
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            label="Description"
                            value={formState.description}
                            onChange={handleChange('description')}
                            fullWidth
                            multiline
                            rows={4}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <FormControlLabel
                            control={
                                <Switch
                                    checked={formState.available}
                                    onChange={handleChange('available')}
                                    color="primary"
                                />
                            }
                            label="Available for adoption"
                        />
                    </Grid>
                    <Grid item xs={12} display="flex" gap={2}>
                        <Button type="submit" variant="contained" color="primary" disabled={submitting}>
                            {submitting ? 'Saving...' : isEdit ? 'Save Changes' : 'Create Pet'}
                        </Button>
                        <Button variant="outlined" onClick={() => navigate(isEdit ? `/pet/${id}` : '/')}>
                            Cancel
                        </Button>
                    </Grid>
                </Grid>
            </Box>
        </Container>
    );
};

export default PetForm;
