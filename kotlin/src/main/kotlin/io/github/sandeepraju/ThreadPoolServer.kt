package io.github.sandeepraju

import io.github.sandeepraju.utils.Database
import io.github.sandeepraju.utils.Image
import java.net.InetAddress
import java.net.ServerSocket
import java.util.concurrent.Executors

/*
    Active Object
    - A proxy, which provides an interface towards clients with publicly accessible methods.
    - An interface which defines the method request on an active object.
    - A list of pending requests from clients.
    - A scheduler, which decides which request to execute next.
    - The implementation of the active object method.
    - A callback or variable for the client to receive the result.
 */
class RequestProcessor {

    // has an internal 'unbounded' queue which the pool will read off of
    private val pool = Executors.newFixedThreadPool(2)

    fun queryDatabase(query: String, cb: (String) -> Unit) {
        pool.submit({
            val result = Database.query(query)
            cb(result)
        })
    }

    fun processImage(path: String, cb: (String) -> Unit) {
        pool.submit({
            val response = Image.process(path)
            cb(response)
        })
    }
}

class ThreadPoolServer : Server {
    override fun listen(host: String, port: Int) {

        println("[${this.javaClass.simpleName}] Server listening at $host:$port")

        val socket = ServerSocket(port, 0, InetAddress.getByName(host))
        val requestProcessor = RequestProcessor()

        socket.use { socket ->
            while (true) {
                val connection = socket.accept()  // accept the connection and submit it to the request processor
                requestProcessor.processImage("/tmp/img.png") {
                    connection.close()
                }
            }
        }

    }
}

fun main(args: Array<String>) {
    ThreadPoolServer().listen("0.0.0.0", 9999)
}