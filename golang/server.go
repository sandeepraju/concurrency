// source: https://coderwall.com/p/wohavg/creating-a-simple-tcp-server-in-go
package main

import (
	"fmt"
	"io/ioutil"
	"net"
	"net/http"
	"os"
)

const (
	CONN_HOST = "0.0.0.0"
	CONN_PORT = "9999"
	CONN_TYPE = "tcp"
)

func hammer() {
	for i := 0; i < 100000; i++ {
		for j := 0; j < 100000; j++ {
		}
	}
}

func iotask() string {
	// Make a get request
	rs, err := http.Get("http://localhost:8000/")
	defer rs.Body.Close()

	// Process response
	if err != nil {
		panic(err)
	}

	bodyBytes, err := ioutil.ReadAll(rs.Body)
	if err != nil {
		panic(err)
	}

	bodyString := string(bodyBytes)
	return bodyString
}

func main() {
	// Listen for incoming connections.
	l, err := net.Listen(CONN_TYPE, CONN_HOST+":"+CONN_PORT)
	if err != nil {
		fmt.Println("Error listening:", err.Error())
		os.Exit(1)
	}
	// Close the listener when the application closes.
	defer l.Close()
	fmt.Println("Listening on " + CONN_HOST + ":" + CONN_PORT)
	for {
		// Listen for an incoming connection.
		conn, err := l.Accept()
		if err != nil {
			fmt.Println("Error accepting: ", err.Error())
			os.Exit(1)
		}
		// Handle connections in a new goroutine.
		go handleRequest(conn)
	}
}

// Handles incoming requests.
func handleRequest(conn net.Conn) {
	// Make a buffer to hold incoming data.
	buf := make([]byte, 1024)
	// Read the incoming connection into the buffer.
	_, err := conn.Read(buf)
	if err != nil {
		fmt.Println("Error reading:", err.Error())
	}

	// CPU intensive task
	hammer()

	// IO intensive task
	// iotask()

	// Send a response back to person contacting us.
	conn.Write([]byte("Message received."))
	// Close the connection when you're done with it.
	conn.Close()
}
