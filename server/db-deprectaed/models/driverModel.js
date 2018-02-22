const sequelize = require('../connection');
const Sequelize = require('sequelize');
const benefitsHistory = require('./benfitsHistoryModel');
const ensurance = require('./ensuranceModel');

const driver = sequelize.define('drivers', {
    phone_number: {type: Sequelize.STRING, primaryKey:true, unique:true, allowNull:false},
    account: {type: Sequelize.STRING, allowNull:false, unique:true},
    score: {type: Sequelize.INTEGER, defaultValue: 0},
    current_ensurance: {
        type: Sequelize.INTEGER,
        references:{
            model: ensurance,
            key: 'id'
        }
    }
});

driver.sync();

//driver.hasOne(ensurance);
//driver.hasMany(benefitsHistory);

module.exports = driver;
