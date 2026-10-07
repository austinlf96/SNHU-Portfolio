const express = require('express');
const router = express.Router();
const jwt = require('jsonwebtoken');
const tripsController = require('../controllers/trips');
const mealsController = require('../controllers/meals');
const roomsController = require('../controllers/rooms');
const authenticationController = require('../controllers/authentication');

// Middleware to verify the JWT sent as "Authorization: Bearer <token>".
// 401 = no/malformed credentials, 403 = token present but invalid or expired.
// On success the decoded payload is available to later handlers as req.authenticatedUser.
function authenticateJWT(req, res, next) {
    const authHeader = req.headers.authorization;

    if (!authHeader) {
        return res.status(401).json({ message: 'Authorization header is required.' });
    }
    
    const headers = authHeader.split(' ');
    
    if (headers[0] !== 'Bearer') {
        return res.status(401).json({ message: 'Authorization header must be in the format: Bearer <token>' });
    }
    const token = headers[1]; // Extract token from "Bearer <token>"

    if (!token) {
        return res.status(401).json({ message: 'Authorization header is missing the token.' });
    }

    jwt.verify(token, process.env.JWT_SECRET, (err, verifiedUser) => {
        if (err) {
            return res.status(403).json({ message: 'Invalid or expired token.' });
        }
        req.authenticatedUser = verifiedUser;
        next();
    });
}

// Define routes for the API endpoints
// Reads are public; writes require a valid JWT
router.route('/trips')
  .get(tripsController.tripsList)
  .post(authenticateJWT, tripsController.tripsAddTrip); // Protect the POST route with JWT authentication

router.route('/trips/:tripCode')
  .get(tripsController.findTripByCode)
  .put(authenticateJWT, tripsController.tripsUpdateTrip); // Protect the PUT route with JWT authentication

router.route('/meals')
  .get(mealsController.mealsList);

router.route('/rooms')
  .get(roomsController.roomsList);

router.route('/register')
  .post(authenticationController.register);

router.route('/login')
  .post(authenticationController.login);

module.exports = router;