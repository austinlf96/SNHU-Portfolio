const passport = require('passport');
const User = require('../models/user');

// Register a new user
exports.register = async (req, res) => {
    const { email, username, password } = req.body;
    try {
        // Validate input
        if (!email || !username || !password) {
            return res.status(400).json({ message: 'Email, username, and password are required.' });
        }

        // Check if the user already exists
        const existingUser = await User.findOne({ email });
        if (existingUser) {
            return res.status(400).json({ message: 'User already exists.' });
        }

        const user = new User({ email, username });                 // Create a new user instance
        user.setPassword(password);                                 // Set the password (hash and salt)
        const success = await user.save();

        if (!success) {
            return res.status(400).json({ message: 'Failed to register user.' });
        } else {
            const token = user.generateJWT();
            res.status(201).json({ message: 'User registered successfully.', token });
        }
    } catch (err) {
        res.status(400).json({ message: err.message });
    }
};

// Log in an existing user
// passport's local strategy (app_api/config/passport.js) looks up the user and checks the password
exports.login = (req, res) => {
    const { email, password } = req.body;
    if (!email || !password) {
        // Validate input
        return res.status(400).json({ message: 'Email and password are required.' });
    }

    passport.authenticate('local', (err, user, info) => {
        if (err) {
            // Handle authentication error
            return res.status(500).json({ message: err.message });
        }

        if (!user) {
            // Handle invalid credentials
            return res.status(401).json(info);
        }

        // Credentials are valid: issue a JWT
        const token = user.generateJWT();
        res.status(200).json({ token });
    })(req, res);
};