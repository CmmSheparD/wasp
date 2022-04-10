package core.compression

import java.util.zip.GZIPInputStream

object GZIPDecompressor : Decompressor {
    override fun decompress(data: ByteArray) = GZIPInputStream(data.inputStream()).bufferedReader().use { it.readText() }
}
