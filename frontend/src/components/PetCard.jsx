import React from 'react';
import { Card, CardContent, CardMedia, Typography, Button, Chip } from '@mui/material';
import { Link } from 'react-router-dom';

const PetCard = ({ pet }) => {
    return (
        <Card sx={{ maxWidth: 345, height: '100%', display: 'flex', flexDirection: 'column' }}>
            <CardMedia
                component="img"
                height="200"
                image={pet.imageUrl || '/placeholder-pet.jpg'}
                alt={pet.name}
                sx={{ objectFit: 'cover' }}
            />
            <CardContent sx={{ flexGrow: 1 }}>
                <Typography gutterBottom variant="h5" component="div">
                    {pet.name}
                </Typography>
                <Typography variant="body2" color="text.secondary" sx={{ mb: 1 }}>
                    {pet.breed} {pet.type}
                </Typography>
                <Typography variant="body2" color="text.secondary" sx={{ mb: 1 }}>
                    Age: {pet.age} years
                </Typography>
                <Typography variant="h6" color="primary" sx={{ mb: 2 }}>
                    ${pet.price}
                </Typography>
                <Chip
                    label={pet.available ? 'Available' : 'Not Available'}
                    color={pet.available ? 'success' : 'error'}
                    size="small"
                    sx={{ mb: 2 }}
                />
                <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                    {pet.description?.substring(0, 100)}...
                </Typography>
            </CardContent>
            <Button
                component={Link}
                to={`/pet/${pet.id}`}
                variant="contained"
                color="primary"
                fullWidth
                sx={{ mt: 'auto' }}
            >
                View Details
            </Button>
        </Card>
    );
};

export default PetCard;