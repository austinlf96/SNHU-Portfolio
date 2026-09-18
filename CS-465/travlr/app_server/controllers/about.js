/* GET about page */
const about = (req, res) => {
    res.render('about', { title: "Travlr Getaways", page: 'about'});
};

module.exports = {
    about
};