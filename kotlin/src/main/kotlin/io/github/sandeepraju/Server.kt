package io.github.sandeepraju

interface Server {
    fun listen(host: String, port: Int): Unit
}