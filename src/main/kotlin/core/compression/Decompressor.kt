package core.compression

interface Decompressor {
    fun decompress(data: ByteArray): String
}