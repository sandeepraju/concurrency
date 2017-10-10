package main

import (
	"fmt"
	"time"
)

func main() {
	a := make(chan string)
	b := make(chan string)

	go (func() { // ActorA
		for {
			ping := <-a

			fmt.Println("[ActorA] received ping from ActorB")
			time.Sleep(500 * time.Millisecond)
			b <- ping
		}
	})()

	go (func() { // ActorB
		for {
			ping := <-b

			fmt.Println("[ActorB] received ping from ActorA")
			time.Sleep(500 * time.Millisecond)
			a <- ping
		}
	})()

	// send the initial ping
	a <- "ping"

	fmt.Scanf("%*s")
}
