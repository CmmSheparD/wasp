package core.storage.records

import core.hash.SHA1Hash

class DirectoryRecord(name: String, hash: String, val children: List<Record>) : Record(name, hash) {
    init {
        require(children.isNotEmpty())
    }

    fun getContentsAsText(separator: String = CANONICAL_RECORD_LINE_SEPARATOR) = children.joinToString(separator)
}

interface DirectoryRecordBuilder {
    fun reset()
    fun setName(name: String)
    fun addChild(record: Record)
    fun getResult(): DirectoryRecord
}

class IncrementalDirectoryRecordBuilder : DirectoryRecordBuilder {
    private var name = DEFAULT_RECORD_STRING_VALUE
    private var hash = DEFAULT_RECORD_STRING_VALUE
    private val children = mutableListOf<Record>()

    override fun reset() {
        name = DEFAULT_RECORD_STRING_VALUE
        hash = DEFAULT_RECORD_STRING_VALUE
        children.clear()
    }

    fun resetWithCopy(record: DirectoryRecord) {
        reset()
        name = record.name
        children.addAll(record.children)
    }

    override fun setName(name: String) {
        this.name = name
    }

    override fun addChild(record: Record) {
        val same = children.find { it.name == record.name }
        if (same == null) {
            children.add(record)
        } else if (same.hash != hash) {
            children.remove(same)
            children.add(record)
        }
    }

    override fun getResult(): DirectoryRecord {
        children.sortBy { it.name }
        calculateHash()
        return DirectoryRecord(name, hash, children)
    }

    private fun calculateHash() {
        hash = SHA1Hash.calculate(children.joinToString(CANONICAL_RECORD_LINE_SEPARATOR))
    }
}

const val CANONICAL_RECORD_LINE_SEPARATOR = "\n"
