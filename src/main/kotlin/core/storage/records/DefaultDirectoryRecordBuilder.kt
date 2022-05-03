package core.storage.records

import core.hash.HashCalculator

class DefaultDirectoryRecordBuilder(val hasher: HashCalculator) : SetDirectoryRecordBuilder() {
    var name = DEFAULT_RECORD_STRING_VALUE
        set(value) {
            require(value.isNotBlank())
            field = value
        }
    private var result: DirectoryRecord? = null

    override fun reset() {
        super.reset()
        name = DEFAULT_RECORD_STRING_VALUE
        result = null
    }

    fun resetWithCopy(record: DirectoryRecord) {
        reset()
        name = record.name
        addChildren(record.children)
    }

    override fun getResult(): DirectoryRecord {
        if (result == null) formRecord()
        return result!!
    }

    private fun formRecord() {
        val childrenList = children.map{ it.record }.sortedBy { it.name }
        result = DirectoryRecord(name, calculateHash(childrenList), childrenList)
    }

    private fun calculateHash(list: List<Record>): String {
        check(list.isNotEmpty())
        return hasher.calculate(list.joinToString(CANONICAL_RECORD_LINE_SEPARATOR))
    }
}

internal const val DEFAULT_RECORD_STRING_VALUE = ""
