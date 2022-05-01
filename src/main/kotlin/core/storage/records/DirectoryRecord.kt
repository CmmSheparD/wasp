package core.storage.records

import core.hash.SHA1Hash

class DirectoryRecord(name: String, hash: String, _children: List<Record> = emptyList()) : Record(name, hash) {
    var children = _children.sortedBy { it.name }
        get() {
            return if (!isFetched) error("Accessing not fetched record.")
            else field
        }
        private set
    var isFetched = children.isNotEmpty()
        private set

    fun getContentsAsText(separator: String = CANONICAL_RECORD_LINE_SEPARATOR) = children.joinToString(separator)

    class Fetcher : DirectoryRecordBuilder {
        private var record: DirectoryRecord? = null
        private var children = mutableListOf<Record>()

        override fun reset() {
            record = null
        }

        fun startFetching(record: DirectoryRecord) {
            check(this.record == null)
            this.record = record
        }

        override fun addChild(record: Record) {
            check(this.record?.isFetched == false)
            children.add(record)
        }

        override fun addChildren(records: List<Record>) {
            for (record in records) addChild(record)
        }

        override fun getResult(): DirectoryRecord {
            check(record != null)
            check(children.isNotEmpty())
            children.sortBy { it.name }
            checkConsistency()
            record!!.children = children.sortedBy { it.name }
            record!!.isFetched = true
            return record!!
        }

        private fun checkConsistency() {
            if (SHA1Hash.calculate(children.joinToString(CANONICAL_RECORD_LINE_SEPARATOR)) != record!!.hash)
                throw FetchingConsistencyException()
        }
    }
}

class FetchingConsistencyException : Exception("Fetched content's hash differs from prefetched hash.")

const val CANONICAL_RECORD_LINE_SEPARATOR = "\n"
