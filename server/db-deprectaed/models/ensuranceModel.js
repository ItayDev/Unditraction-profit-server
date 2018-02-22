const sequelize = require('../connection');
const Sequelize = require('sequelize');

const ensurances = sequelize.define('ensurances', {
    id: {type: Sequelize.STRING, allowNull:false, primaryKey: true},
    company: {type: Sequelize.STRING, allowNull: false}
});

ensurances.sync();

module.exports = ensurances;