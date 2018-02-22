const express = require('express');
const benefit = require('../db/models/benefit.model');
const driver = require('../db/models/driver.model');

let router = express.Router();
router.get('/', async (req, res, next) => {
    try {
        let result = await benefit.find({});
    } catch(ex) {
        res.status(500).send("error in /");
    }
});

router.post('/buy', async (req, res, next) => {
    try {
        let chosenBenefit = await benefit.findById(req.body.benefit_id);
        let currentUser = await driver.findOne({phone_number: req.body.phone_number});

        if(currentUser.score < chosenBenefit.price){
            res.status(400).send("Score is insufficiant");
            return;
        }

        currentUser.benefits.push({
            title: chosenBenefit.title,
            description: chosenBenefit.description,
            picture: chosenBenefit.picture,
            vendor: chosenBenefit.vendor,
            coupon_code: "It will be generated",
        })

        currentUser.score -= chosenBenefit.price;
        await currentUser.save();
        res.status(202).send("Succesfully purchased");
    } catch(ex) {
        res.status(500).send("error in /buy");
    }
});

router.post('/my', async (req, res, next) => {
    try {
        let currentUser = await driver.findOne({phone_number: req.body.phone_number});

        res.status(200).send(currentUser.benefits);
    } catch(ex) {
        res.status(500).send('error in /my');
    }
});

module.exports = router;