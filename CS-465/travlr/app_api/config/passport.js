const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;
const mongoose = require('mongoose');
require('../models/user'); // must load first so the 'users' model is registered
const Users = mongoose.model('users');

// Local strategy: the login form's "email" field is used as the username
passport.use(new LocalStrategy({
    usernameField: 'email'
}, async(email, password, done) => {
    try {
        const user = await Users.findOne({ email: email });
        if (!user) {
            return done(null, false, { message: 'Incorrect email.' });
        }
        if (!user.validatePassword(password)) {
            return done(null, false, { message: 'Incorrect password.' });
        }
        return done(null, user);
    } catch (err) {
        return done(err);
    }
}));
            