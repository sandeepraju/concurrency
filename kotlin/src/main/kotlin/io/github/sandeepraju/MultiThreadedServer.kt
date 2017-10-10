package io.github.sandeepraju

import io.github.sandeepraju.utils.Database
import io.github.sandeepraju.utils.Image
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.InetAddress
import java.net.ServerSocket

class MultiThreadedServer : Server {

    private fun handler(input: String): String {
        // CPU intensive
        return Image.process("/tmp/hello.png")

        // I/O intensive
        // return Database.query("SELECT * FROM users;")
    }

    override fun listen(host: String, port: Int) {

        println("[${this.javaClass.simpleName}] Server listening at $host:$port")

        val socket = ServerSocket(port, 0, InetAddress.getByName(host))

        socket.use { socket ->
            while (true) {
                val connection = socket.accept()
                // instead of handling this connection in the same thread,
                // create a new thread for this connection
                Thread({
                    connection.use { connection ->
                        val input = BufferedReader(InputStreamReader(connection.getInputStream()))
                        val output = DataOutputStream(connection.getOutputStream())

                        val data = input.readLine()

                        val response = handler(data)

                        output.writeBytes(response)
                    }
                }).start()

            }
        }

    }
}

fun main(args: Array<String>) {
    MultiThreadedServer().listen("0.0.0.0", 9999)
}