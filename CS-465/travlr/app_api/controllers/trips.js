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

// GET /api/trips/:tripCode - lists a specific trip based on the tripCode parameter
// Regardless of outcome, response must include HTML status code and JSON object with either the trip data or an error message
const findTripByCode = async (req, res) => {
    try {
        const trip = await tripModel.find({ code: req.params.tripCode }).exec();

        // Show results of query in console for debugging purposes
        // console.log('Retrieved trip:', trip);
        if (!trip.length){
            res.status(404).json({ message: 'Trip not found' });
        } else{
            res.status(200).json(trip);
        }
        
    } catch (error) {
        res.status(404).json({ message: error.message });
    }
};

// POST /api/trips - adds a new trip to the database
// Regardless of outcome, response must include HTML status code and JSON object with either the new trip data or an error message
const tripsAddTrip = async (req, res) => {
    try {
        const newTrip = new Trip({
            code: req.body.code,
            name: req.body.name,
            length: req.body.length,
            start: req.body.start,
            resort: req.body.resort,
            perPerson: req.body.perPerson,
            image: req.body.image,
            description: req.body.description
        });

        const savedTrip = await newTrip.save();
        res.status(201).json(savedTrip);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
};

// PUT /api/trips/:tripCode - updates an existing trip in the database
// Regardless of outcome, response must include HTML status code and JSON object with either the updated trip data or an error message
const tripsUpdateTrip = async (req, res) => {
    try {
        const updatedTrip = await tripModel.findOneAndUpdate(
            { code: req.params.tripCode },
            {
                code: req.body.code,
                name: req.body.name,
                length: req.body.length,
                start: req.body.start,
                resort: req.body.resort,
                perPerson: req.body.perPerson,
                image: req.body.image,
                description: req.body.description
            }
        ).exec();

        if (!updatedTrip) {
            res.status(404).json({ message: 'Trip not found' });
        } else {
            res.status(201).json(updatedTrip);
        }
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
};

module.exports = {
    tripsList,
    findTripByCode,
    tripsAddTrip,
    tripsUpdateTrip
};