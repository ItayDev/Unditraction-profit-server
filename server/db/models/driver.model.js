const MONGOOSE = require('mongoose');
const insurance = require('./insurance.model');

let driverSchema = new MONGOOSE.Schema({
    phone_number: {type:String, unique: true},
    acount: String,
    score: {type: Number, default: 0},
    benefits: {
        type:[{
            purchase_date: {type: Date, default: Date.now()},
            title: String,
            description: String,
            picture: String,
            vendor: String,
            coupon_code: String,
            used: { type: Boolean, default: false}
        }],
        default: {}
    },
    current_insurance_id: {type:String, default: ""}
});

driverSchema.virtual("current_insurance").get(async () => {
    if(this.current_insurance_id === "") {
        return "";
    } else {
        return insurance.findOne({insurance_code: this.current_insurance_id})
    }
})

module.exports = MONGOOSE.model('driver', driverSchema);