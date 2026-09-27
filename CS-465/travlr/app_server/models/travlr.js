const mongoose = require('mongoose');

const tripSchema = mongoose.Schema({
    code: { type: String, required: true },
    name: { type: String, required: true },
    length: { type: String, required: true },
    start: { type: Date, required: true },
    resort: { type: String, required: true },
    perPerson: { type: String, required: true },
    image: { type: String, required: true },
    description: { type: String, required: true }   
});

const roomSchema = mongoose.Schema({
    name: { type: String, required: true },
    image: { type: String, required: true },
    description: { type: String, required: true },
    rate: { type: String, required: true }
});

const mealSchema = mongoose.Schema({
    name: { type: String, required: true },
    image: { type: String, required: true },
    description: { type: String, required: true }
});

const Trip = mongoose.model('trips', tripSchema);
const Room = mongoose.model('rooms', roomSchema);
const Meal = mongoose.model('meals', mealSchema);

module.exports = { Trip, Room, Meal };