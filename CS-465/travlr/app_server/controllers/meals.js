var fs = require('fs');
var meals = JSON.parse(fs.readFileSync('./data/meals.json', 'utf8'));

/* GET meals page */
const mealsPage = (req, res) => {
    res.render('meals', { title: "Travlr Getaways", page: 'meals', meals });
};

module.exports = {
    meals: mealsPage
};
