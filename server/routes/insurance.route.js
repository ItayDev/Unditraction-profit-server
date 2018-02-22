const express = require('express');
const insurance = require('../db/models/insurance.model');
const driver = require('../db/models/driver.model');

let router = express.Router();
router.get('/', async (req, res, next) => {
    try {
        let result = await insurance.find({});
    } catch(ex) {
        res.status(500).send("error in /");
    }
});

router.post('/buy', async (req, res, next) => {
    try {
        let chosenInsurance = await benefit.findOne({insurance_code: req.body.insurance_code});
        let currentUser = await driver.findOne({phone_number: req.body.phone_number});

        if(currentUser.score < chosenInsurance.price){
            res.status(400).send("Score is insufficiant");
            return;
        }

        currentUser.insurance_code = chosenInsurance.insurance_code;

        currentUser.score -= chosenInsurance.price;
        await currentUser.save();
        res.status(202).send("Succesfully purchased");
    } catch(ex) {
        res.status(500).send("error in /buy");
    }
});

module.exports = router;