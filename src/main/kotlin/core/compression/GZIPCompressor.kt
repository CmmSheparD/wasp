package core.compression

import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

object GZIPCompressor : Compressor {
    private val byteStream = ByteArrayOutputStream()
    override fun compress(data: String): ByteArray {
        byteStream.reset()
        GZIPOutputStream(byteStream).bufferedWriter().use { it.write(data) }
        return byteStream.toByteArray()
    }
}
