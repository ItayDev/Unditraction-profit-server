const Sequelize = require('sequelize');
const configuration = require('../configuration/config').db;

module.exports = new Sequelize({
    database: 'safe',
    username: configuration.user,
    password: configuration.password,
    dialect: 'mysql',
    host: configuration.host,
    port: configuration.port,
    pool: {
        max: 5,
        idle: 30000,
        acquire: 60000,
    }
});


