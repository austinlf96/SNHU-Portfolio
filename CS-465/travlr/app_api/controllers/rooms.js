const mongoose = require('mongoose');
const Room = require('../models/travlr').Room;
const roomModel = mongoose.model('rooms', Room.schema);

// GET /api/rooms
const roomsList = async (req, res) => {
    try {
        const rooms = await roomModel.find({}).exec();
        // Show results of query in console for debugging purposes
        // console.log('Retrieved rooms:', rooms);
        res.status(200).json(rooms);
    } catch (error) {
        res.status(404).json({ message: error.message });
    }
};

module.exports = {
    roomsList
};