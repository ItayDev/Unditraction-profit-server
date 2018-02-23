var express = require('express');
var path = require('path');
var logger = require('morgan');
var bodyParser = require('body-parser');
const Connection = require('./db/connection')
const benefitRoute = require('./routes/benefit.route');
const driverRoute = require('./routes/driver.route');
const insuranceRoute = require('./routes/insurance.route');

let connection = new Connection();

var app = express();


app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.use('/benefit', benefitRoute);
app.use('/insurance', insuranceRoute);
app.use('/driver', driverRoute);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
});

process.on('exit', function() {
  connection.Destroy();
});

module.exports = app;