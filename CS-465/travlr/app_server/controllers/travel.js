const tripsEndpoint = 'http://localhost:3000/api/trips';
const options = {
    method: 'GET',
    headers: {
        'Accept': 'application/json'
    }
};
//var fs = require('fs');
//var trips = JSON.parse(fs.readFileSync('./data/trips.json', 'utf8'));

const travel = async(req, res, next) => {
    await fetch(tripsEndpoint, options)
        .then(response => response.json())
        .then(json => {
            let message = null;
            if (!json instanceof Array) {
                message = "API lookup error";
                json = [];
            } else {
                if (!json.length) {
                    message = "No trips exist in our database";
                }
            }

            // Process the JSON data
            res.render('travel', { title: 'Travlr Getaways', trips: json, message });
        })
        .catch(error => {
            // Handle any errors that occurred during the fetch
            console.error('Error fetching trips:', error);
            res.status(500).send('Error fetching trips');
        });
}

module.exports = {
    travel
};