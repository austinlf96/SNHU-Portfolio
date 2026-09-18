var fs = require('fs');
var rooms = JSON.parse(fs.readFileSync('./data/rooms.json', 'utf8'));

/* GET rooms page */
const roomsPage = (req, res) => {
    res.render('rooms', { title: "Travlr Getaways", page: 'rooms', rooms });
};

module.exports = {
    rooms: roomsPage
};
