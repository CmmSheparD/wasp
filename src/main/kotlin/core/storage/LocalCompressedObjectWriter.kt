package core.storage

import core.compression.GZIPCompressor
import core.hash.SHA1Hash
import java.io.File

class LocalCompressedObjectWriter(private val storagePath: String) : ObjectWriter {
    override fun storeObject(content: String): String {
        val hash = SHA1Hash.calculate(content)
        writeObject(hash, GZIPCompressor.compress(content))
        return hash
    }

    private fun writeObject(at: String, what: ByteArray) {
        File("$storagePath/$at").apply {
            writeBytes(what)
        }
    }
}