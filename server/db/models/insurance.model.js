const MONGOOSE = require('mongoose');

let insurance = new MONGOOSE.Schema({
    company: String,
    title: String,
    description: String,
    insurance_code: String,
    price: Number
});

module.exports = MONGOOSE.model('insurance', insurance);