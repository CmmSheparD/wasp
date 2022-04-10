package core.compression

interface Compressor {
    fun compress(data: String): ByteArray
}