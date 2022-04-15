package core.storage

import core.compression.GZIPCompressor
import core.hash.SHA1Hash
import java.io.File

class LocalCompressedRecordWriter(private val storagePath: String) : RecordWriter {
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