const mongoose = require('mongoose');
const Meal = require('../models/travlr').Meal;
const mealModel = mongoose.model('meals', Meal.schema);

// GET /api/meals
const mealsList = async (req, res) => {
    try {
        const meals = await mealModel.find({}).exec();
        // Show results of query in console for debugging purposes
        // console.log('Retrieved meals:', meals);
        res.status(200).json(meals);
    } catch (error) {
        res.status(404).json({ message: error.message });
    }
};

module.exports = {
    mealsList
}; 