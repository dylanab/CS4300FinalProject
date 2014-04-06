'use strict'
/* 
 * Author: Christopher Ghyzel
 * Express server for testing out angular front page wireframe.
 * I whipped this up because there are no cross-origin requests for XMLHTTP
 * 
 * To run just node app.js
 */

/* Module dependencies */
var express = require('express'),
url = require('url'),
path = require('path');

var app = module.exports = express();

/* Config */
app.set('port', 8000);
app.use(express.static(path.join(__dirname, 'public')));
app.use(app.router);

/* Listen! */

app.listen(app.get('port'), function() {
    console.log('Express server listening on port ' + app.get('port'));
});
