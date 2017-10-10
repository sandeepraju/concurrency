// source: https://astaxie.gitbooks.io/build-web-application-with-golang/en/03.2.html
package main

import (
	"fmt"
	"log"
	"net/http"
	"strings"
	"time"
)

func sayhelloName(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()       // parse arguments, you have to call this by yourself
	fmt.Println(r.Form) // print form information in server side
	fmt.Println("path", r.URL.Path)
	fmt.Println("scheme", r.URL.Scheme)
	fmt.Println(r.Form["url_long"])
	for k, v := range r.Form {
		fmt.Println("key:", k)
		fmt.Println("val:", strings.Join(v, ""))
	}
	time.Sleep(3000 * time.Millisecond)
	fmt.Fprintf(w, "Hello!") // send data to client side
}

func main() {
	http.HandleFunc("/", sayhelloName) // set router
	fmt.Println("Running on localhost:8000")
	err := http.ListenAndServe(":8000", nil) // set listen port
	if err != nil {
		log.Fatal("ListenAndServe: ", err)
	}
}
