const express = require('express');
const path = require('path');
const proxy = require('http-proxy-middleware');
const port = process.env.PORT || 8081;
const app = express();

// the __dirname is the current directory from where the script is running
app.use(express.static(__dirname + "/dist"));

app.get('/', (req, res) => {
  res.sendFile(path.resolve(__dirname + "/dist", 'index.html'));
});

const apiProxy = proxy('/api', { target: 'http://gateway:8085' });
app.use('/api', apiProxy);

app.listen(port, '0.0.0.0');
console.log("server started on port " + port);