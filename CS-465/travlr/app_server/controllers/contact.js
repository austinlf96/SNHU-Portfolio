/* GET contact page */
const contact = (req, res) => {
    res.render('contact', { title: "Travlr Getaways", page: 'contact'});
};

/* POST contact form */
const contactSubmit = (req, res) => {
    res.redirect('/contact');
};

module.exports = {
    contact,
    contactSubmit
};