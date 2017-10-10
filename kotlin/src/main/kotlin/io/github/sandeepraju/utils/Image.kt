package io.github.sandeepraju.utils

class Image {
    companion object {
        fun process(path: String): String {
            val sleepTime = 3000 * 1000000L // convert to nanoseconds
            val startTime = System.nanoTime()
            while (System.nanoTime() - startTime < sleepTime) {}

            return sleepTime.toString()
        }
    }
}