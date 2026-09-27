const mongoose = require('mongoose');
const Trip = require('../models/travlr').Trip;
const tripModel = mongoose.model('trips', Trip.schema);

// GET /api/trips
const tripsList = async (req, res) => {
    try {
        const trips = await tripModel.find({}).exec();

        // Show results of query in console for debugging purposes
        // console.log('Retrieved trips:', trips);

        res.status(200).json(trips);
    } catch (error) {
        res.status(404).json({ message: error.message });
    }
};

// GET /api/trips:tripCode - lists a specific trip based on the tripCode parameter
// Regardless of outcome, response must include HTML status code and JSON object with either the trip data or an error message
const findTripByCode = async (req, res) => {
    try {
        const trip = await tripModel.find({ code: req.params.tripCode }).exec();

        // Show results of query in console for debugging purposes
        // console.log('Retrieved trip:', trip);

        res.status(200).json(trip);
    } catch (error) {
        res.status(404).json({ message: error.message });
    }
};

module.exports = {
    tripsList,
    findTripByCode
};