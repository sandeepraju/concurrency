package main

import "fmt"

func main() {
	messages := make(chan string)
	go func() {
		// send a ping
		messages <- "ping"
	}()

	// blocking wait until ping is received
	msg := <-messages
	fmt.Println(msg)
}
