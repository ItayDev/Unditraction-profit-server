const MONGOOSE = require('mongoose');

let benefitSchema = new MONGOOSE.Schema({
    price: Number,
    title: String,
    description: String,
    picture: String,
    vendor: String
});

module.exports = MONGOOSE.model('benefit', benefitSchema);