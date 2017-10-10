var net = require('net');
var http = require('http');

// Start a TCP Server
net.createServer(function (socket) {

  // Handle incoming messages from clients.
  socket.on('data', function (data) {
    console.log("Request came in for ", data);

    // Do something CPU intensive
    for (var i = 0; i < 50000; i++)
      for (var j = 0; j < 50000; j++);
    socket.write("response");

    // Do something I/O bound
    // http.get("http://localhost:8000/", function(res) {
    //   // Return the response back to the client
    //   socket.write("response");
    // });
  });

}).listen(9999);

console.log("Server running at port localhost:9999\n");
