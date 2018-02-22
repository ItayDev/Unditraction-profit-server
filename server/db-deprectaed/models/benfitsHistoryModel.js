const sequelize = require('../connection');
const Sequelize = require('sequelize');

const driver = require('./driverModel');
const benefits = require('./benefitModel');

const benefitsHistory = sequelize.define('benefit_history', {
    benefit_id: {
        type: Sequelize.INTEGER,
        references: {
            model:benefits,
            key: 'id'
        }
    },
    driver_phone: {
        type: Sequelize.STRING,
        references: {
            model: driver,
            key: 'phone_number'
        }
    }
});

benefitsHistory.sync();

//driver.belongsTo(benefitsHistory);
//benefits.belongsTo(benefitsHistory);
module.exports = benefitsHistory;