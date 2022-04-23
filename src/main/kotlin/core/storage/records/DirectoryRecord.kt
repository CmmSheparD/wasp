package core.storage.records

data class DirectoryRecord(override val name: String, override val hash: String, val children: List<Record>) : Record

interface DirectoryRecordBuilder {
    fun setName(_name: String)
    fun addChild(record: Record)
    fun getResult(): DirectoryRecord
    fun reset()
}

class BaseDirectoryRecordBuilder : DirectoryRecordBuilder {
    private var name = DEFAULT_RECORD_STRING_VALUE
    private var hash = DEFAULT_RECORD_STRING_VALUE
    private val children = mutableListOf<Record>()

    override fun setName(_name: String) {
        name = _name
    }

    fun setHash(_hash: String) {
        hash = _hash
    }

    override fun addChild(record: Record) {
        children.add(record)
    }

    override fun getResult(): DirectoryRecord {
        children.sortBy { it.name }
        return DirectoryRecord(name, hash, children)
    }

    override fun reset() {
        name = DEFAULT_RECORD_STRING_VALUE
        hash = DEFAULT_RECORD_STRING_VALUE
        children.clear()
    }
}
