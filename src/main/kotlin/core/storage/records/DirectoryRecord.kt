package core.storage.records

class DirectoryRecord(name: String, hash: String, _children: List<Record>) : Record(name, hash) {
    private val childrenList: MutableList<Record> = _children.toMutableList()
    val children: List<Record>
        get() {
            if (!isFetched) error("Accessing not fetched record.")
            return childrenList
        }
    var isFetched = children.isNotEmpty()
        private set

    fun getContentsAsText(separator: String = CANONICAL_RECORD_LINE_SEPARATOR) = children.joinToString(separator)

    class Fetcher : DirectoryRecordBuilder {
        private var record: DirectoryRecord? = null

        override fun reset() {
            record = null
        }

        fun startFetch(record: DirectoryRecord) {
            check(this.record == null)
            this.record = record
        }

        override fun addChild(record: Record) {
            this.record!!.childrenList.add(record)
        }

        override fun getResult(): DirectoryRecord {
            record!!.isFetched = true
            record!!.childrenList.sortBy { it.name }
            return record!!
        }
    }
}

const val CANONICAL_RECORD_LINE_SEPARATOR = "\n"
