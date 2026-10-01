// Bring in the DB connection and model exports
const Mongoose = require('./db');
const { Trip, Room, Meal } = require('./travlr');

// Read seed data from JSON files
var fs = require('fs');
var trips = JSON.parse(fs.readFileSync('data/trips.json', 'utf8'));
var rooms = JSON.parse(fs.readFileSync('data/rooms.json', 'utf8'));
var meals = JSON.parse(fs.readFileSync('data/meals.json', 'utf8'));

// Clear existing records and seed the database with new data
const seedDB = async () => {
	try {
		await Trip.deleteMany({});
		await Trip.insertMany(trips);

		await Room.deleteMany({});
		await Room.insertMany(rooms);

		await Meal.deleteMany({});
		await Meal.insertMany(meals);

        console.log('Database seeded successfully.');
	} catch (err) {
		console.error('Error seeding database:', err);
	}
};

seedDB().then(async () => {
	await Mongoose.connection.close();
	console.log('Database seeded and connection closed.');
	process.exit(0);
});

module.exports = seedDB;