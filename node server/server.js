// server.js
const express        = require('express');
const bodyParser     = require('body-parser');
const port = 8000;

const app = express();

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

output = "";

app.post('/', function (req, res) {
  console.log("Requesting Task: " + req.body.requestID);

  var child = require('child_process').spawn(
    'java', ['-jar', '../out/artifacts/Rental_Cars_jar/Rental Cars.jar',
     req.body.requestID]
  );

  child.stdout.on('data', function(data) {
      output += data.toString();
  });

  child.stderr.on("data", function (data) {
      console.log(data.toString());
  });

  child.on('close', function (code) {
      console.log("Response Sent\n");
      res.send(output);
      output = "";
  });

  module.exports = child;
});

app.listen(port, () => console.log('\nListening on port ' + port +  ' \n\nWaiting for post requests\n'));
