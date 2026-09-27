// Bring in the DB connection & Trip Schema
const Mongoose = require('./db');
const Trip = require('./travlr');

//Read seed data from JSON file
var fs = require('fs');
var trips = JSON.parse(fs.readFileSync('data/trips.json', 'utf8'));

// Clear existing records and seed the database with new data
const seedDB = async () => {
    try {
        // Clear existing trips
        await Trip.deleteMany({});
        await Trip.insertMany(trips);
    } catch (err) {
        console.error('Error seeding database:', err);
    }
};

seedDB().then(async() => {
    await Mongoose.connection.close();
    console.log('Database seeded and connection closed.');
    process.exit(0);
});
module.exports = seedDB;