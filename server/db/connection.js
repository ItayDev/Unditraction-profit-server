let mongoose = require("mongoose")
const configuration = require("../Configurations/Mongo.config");

module.exports = class MongoConnection{

    constructor(){
        mongoose.Promise = global.Promise;
        mongoose.connect(`mongodb://${configuration.db.userName}:${configuration.db.password}@${configuration.db.connectionString}`, {
            reconnectTries: Number.MAX_VALUE,
            reconnectInterval: 500,
            poolSize: 10,
            bufferMaxEntries: 0,
            keepAlive: 120
          },
        (err)=>{
            if(err){
                console.error(err)
            } else{
                console.log("Connection opened")
            }
        });
    }

    Destroy(){
        mongoose.connection.close();
        mongoose.connection.on("diconnected",(data)=>{
            console.log("Connection successfully closed");
        })
    }
}
