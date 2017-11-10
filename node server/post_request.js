var request = require('request');

/*
TASK LIST:
1: Print a list of all the cars, in ascending price order
2: Print a list of the specifications for each car
3: Print out the highest rated supplier per car type, descending order
4: Print out a list of vehicles, ordered by the sum of the scores in descending order
*/

// Getting task number desired from command line args
var args = process.argv.slice(2);
var taskChoice = args[0];

if (taskChoice == undefined || taskChoice < 0 || taskChoice > 4) {
  console.log("Invalid Task ID, please refer to README");
  process.exit();
}

// Set the headers
var headers = {
    'User-Agent':       'Super Agent/0.0.1',
    'Content-Type':     'application/x-www-form-urlencoded'
};

// Configure the request
var options = {
    url: 'http://127.0.0.1:8000',
    method: 'POST',
    headers: headers,
    form: {'requestID': taskChoice}
};

// Start the request
request(options, function (error, response, body) {
    if (!error && response.statusCode == 200) {
        // Print out the response body
        console.log(body);
    }
});
