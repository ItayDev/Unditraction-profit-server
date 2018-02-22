const express = require('express');
const driver = require('../db/models/driver.model');
let router = express.Router();

router.post('/myscore', async (req, res, next) => {
    try {
        let currentUser = await driver.findOne({phone_number: req.body.phone_number});

        res.status(200).send(currentUser.score);
    } catch(ex) {
        res.status(500).send('error in /myscore');
    }
});

router.put('/increase', async (req, res, next) => {
    try {
        let currentUser = await driver.findOne({phone_number: req.body.phone_number});

        currentUser.score += 1;
        await currentUser.save();

        res.status(202).send("score increased");
    } catch(ex) {
        res.status(500).send('error in /increase');
    }
});

router.put('/increase', async (req, res, next) => {
    try {
        let currentUser = await driver.findOne({phone_number: req.body.phone_number});

        currentUser.score += req.body.amount;
        await currentUser.save();

        res.status(202).send("score increased");
    } catch(ex) {
        res.status(500).send('error in /decrease');
    }
});

router.put('/increase', async (req, res, next) => {
    try {
        let currentUser = await driver.findOne({phone_number: req.body.phone_number});

        currentUser.score -= req.body.amount;
        await currentUser.save();

        res.status(202).send("score increased");
    } catch(ex) {
        res.status(500).send('error in /decrease');
    }
});

module.exports = router;