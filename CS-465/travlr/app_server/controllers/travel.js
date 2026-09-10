/* GET travel view */
const travel = (req, res) => {
    res.render('travel', {title: "Travlr Getaways", page: 'travel'});
};

module.exports = {
    travel
}