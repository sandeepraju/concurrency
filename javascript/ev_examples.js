// example 01
var http = require("http");

console.log("Before the request");

http.get("http://httpbin.org/delay/1", function(res) {
  // request was complete
  // console.log("response code:", res.statusCode);
  console.log("On request complete");
});

console.log("After the request");


// // example 02
// console.log("Before the setTimeout");

// setTimeout(function() {
//   console.log("I was executed after 500ms");
// }, 500); // run after a 500ms

// console.log("After the setTimeout");

// // do some CPU intensive work
// for (var i = 0; i < 100000; i++)
//   for (var j = 0; j < 100000; j++);

// console.log("After hammering the CPU");


// // example 03
// console.log("Before the setTimeout");

// setTimeout(function() {
//   console.log("I was executed after 500ms");
// }, 500); // run after a 500ms

// console.log("After the setTimeout");

// function hammer() {
//   for (var i = 0; i < 100000; i++)
//     for (var j = 0; j < 100000; j++);
// }

// function multi_hammer() {
//   for (var k = 0; k < 3; k++) {
//     console.log("Hammering ", k);
//     hammer();
//   }
// }

// // do some CPU intensive work
// multi_hammer();

// console.log("After hammering the CPU");
