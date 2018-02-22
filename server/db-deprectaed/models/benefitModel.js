const sequelize = require('../connection');
const Sequelize = require('sequelize');

const benefits = sequelize.define('benefits', {
    id: {type: Sequelize.INTEGER, primaryKey:true, autoIncrement:true},
    expiration_date: Sequelize.DATE,
    price: {type: Sequelize.INTEGER, allowNull:false},
    title: {type: Sequelize.TEXT, allowNull:false},
    description: {type: Sequelize.TEXT, allowNull:false},
    coupon_code: {type: Sequelize.TEXT, allowNull:false},
    picture: {type: Sequelize.TEXT, allowNull:false},
    vendor: {type: Sequelize.STRING, allowNull: false}
});

benefits.sync();

module.exports = benefits;