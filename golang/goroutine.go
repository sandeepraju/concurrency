package main

import "fmt"

func f(from string) {
	for i := 0; i < 3; i++ {
		fmt.Println(from, ":", i)
	}
}

func hammer() {
	for {
	}
}

func main() {
	// f("a")
	// go f("b")
	// f("c")

	go hammer()
	// go hammer()
	// go hammer()
	// go hammer()
	// go hammer()
	// go hammer()

	// wait for input (simple way to wait until all goroutines are done)
	// or use waitgroups
	fmt.Scanf("%*s")
}
