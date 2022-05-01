package core.storage.records

import core.hash.SHA1Hash

class DefaultDirectoryRecordBuilder : DirectoryRecordBuilder {
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

    fun setName(name: String) {
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

    fun addChildren(records: List<Record>) {
        for (record in records) addChild(record)
    }

    override fun getResult(): DirectoryRecord {
        children.sortBy { it.name }
        calculateHash()
        return DirectoryRecord(name, hash, children)
    }

    private fun calculateHash() {
        check(children.isNotEmpty())
        hash = SHA1Hash.calculate(children.joinToString(CANONICAL_RECORD_LINE_SEPARATOR))
    }
}

internal const val DEFAULT_RECORD_STRING_VALUE = ""
