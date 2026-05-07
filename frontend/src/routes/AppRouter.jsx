import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import PetGallery from '../pages/PetGallery';
import PetDetail from '../pages/PetDetail';
import PetForm from '../pages/PetForm';

const AppRouter = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<PetGallery />} />
                <Route path="/gallery" element={<PetGallery />} />
                <Route path="/pet/new" element={<PetForm />} />
                <Route path="/pet/:id/edit" element={<PetForm />} />
                <Route path="/pet/:id" element={<PetDetail />} />
            </Routes>
        </Router>
    );
};

export default AppRouter;