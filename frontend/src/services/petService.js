import api from './api';

const petService = {
    getPets: (params = {}) => api.getPets(params),
    getPet: (id) => api.getPet(id),
    createPet: (pet) => api.createPet(pet),
    updatePet: (id, pet) => api.updatePet(id, pet),
    deletePet: (id) => api.deletePet(id),
};

export default petService;