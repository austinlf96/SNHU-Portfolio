const express = require('express');
const router = express.Router();
const tripsController = require('../controllers/trips');
const mealsController = require('../controllers/meals');
const roomsController = require('../controllers/rooms');

// Define routes for the API endpoints
router.get('/trips', tripsController.tripsList);
router.get('/trips/:tripCode', tripsController.findTripByCode);
router.get('/meals', mealsController.mealsList);
router.get('/rooms', roomsController.roomsList);

module.exports = router;