package io.github.sandeepraju

import io.github.sandeepraju.utils.Database
import io.github.sandeepraju.utils.Image
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel


class AsyncServer : Server {

    // selector is the Java wrapper around select() system call
    val selector = Selector.open()

    // create a socket to listen for traffic
    val channel = ServerSocketChannel.open()

    override fun listen(host: String, port: Int): Unit {

        println("[${this.javaClass.simpleName}] Server listening at $host:$port")

        channel.bind(InetSocketAddress(host, port))
        channel.configureBlocking(false)

        // register the socket channel to notify when any client "connects"
        channel.register(selector, channel.validOps())

        // start the event loop
        while (true) {

            // check if any channels are ready
            selector.select()

            // fetch all the keys of the channels that are ready
            // on the operations we registered
            val readyChannelKeys = selector.selectedKeys().iterator()
            while(readyChannelKeys.hasNext()) {

                val currentChannelKey = readyChannelKeys.next()

                when {

                    currentChannelKey.isAcceptable -> { // we got a new connection

                        // accept the new connection and register it to the selector
                        // so that selector notifies us when it is ready to read the data
                        val acceptedChannel = (currentChannelKey.channel() as ServerSocketChannel).accept()
                        acceptedChannel.configureBlocking(false)
                        acceptedChannel.register(selector, SelectionKey.OP_READ)

                    }

                    currentChannelKey.isReadable -> { // the socket is ready to be read now

                        // read the data onto a buffer
                        val data = ByteBuffer.allocate(256)
                        val rwChannel = currentChannelKey.channel() as SocketChannel
                        rwChannel.read(data)

                        println(String(data.array(), 0, data.position()).trim())

                        // CPU intensive
                        Image.process("/tmp/hello.png")

                        // I/O intensive
                        // Database.query("SELECT * FROM users;")

                        rwChannel.configureBlocking(false)
                        rwChannel.register(selector, SelectionKey.OP_WRITE)
                    }

                    currentChannelKey.isWritable -> { // the socket is ready to be written now

                        // write response to the socket
                        val rwChannel = currentChannelKey.channel() as SocketChannel
                        rwChannel.write(ByteBuffer.wrap("pong".toByteArray()))

                        // close the channel
                        rwChannel.close()
                    }

                }

                // remove the current socket from the selector since we processed it
                readyChannelKeys.remove()

            }
        }
    }
}

fun main(args: Array<String>) {
    AsyncServer().listen("0.0.0.0", 9999)
}