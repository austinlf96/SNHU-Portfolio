/* GET news page */
const news = (req, res) => {
    res.render('news', { title: "Travlr Getaways", page: 'news' });
};

module.exports = {
    news
};
