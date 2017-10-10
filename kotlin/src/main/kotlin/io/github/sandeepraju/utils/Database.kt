package io.github.sandeepraju.utils

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients


class Database {

    companion object {
        fun query(query: String): String {
            HttpClients.createDefault().use { client ->
                val httpGet = HttpGet("http://httpbin.org/delay/1")
                client.execute(httpGet).use { response ->
                    // log the response
                    println(response.statusLine)
                }
            }

            return "John Doe, jdoe@example.com"
        }
    }
}