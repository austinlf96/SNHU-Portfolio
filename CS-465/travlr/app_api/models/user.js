const mongoose = require('mongoose');
const crypto = require('crypto');
const jwt = require('jsonwebtoken');

const userSchema = new mongoose.Schema({
    email: {
      type: String,
      required: true,
      unique: true
    },
    username: {
        type: String,
        required: true,
    },
    hash: String, 
    salt: String
});

// Passwords are never stored: only a random per-user salt and a PBKDF2 hash are saved.
// Method to set password
userSchema.methods.setPassword = function(password) {
    this.salt = crypto.randomBytes(16).toString('hex');
    this.hash = crypto.pbkdf2Sync(password, this.salt, 1000, 64, 'sha512').toString('hex');
};

// Method to validate password
userSchema.methods.validatePassword = function(password) {
    const hash = crypto.pbkdf2Sync(password, this.salt, 1000, 64, 'sha512').toString('hex');
    return this.hash === hash;
};

// Method to generate JWT (payload is readable by the client, so keep secrets out of it)
userSchema.methods.generateJWT = function() {
    return jwt.sign({
        id: this._id,
        email: this.email,
        username: this.username,
    }, process.env.JWT_SECRET, { expiresIn: '1h' });
};


const User = mongoose.model('users', userSchema);
module.exports = User;
