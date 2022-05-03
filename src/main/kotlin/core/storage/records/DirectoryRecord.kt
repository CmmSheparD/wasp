package core.storage.records

import core.hash.HashCalculator

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

    class Fetcher(val hasher: HashCalculator) : SetDirectoryRecordBuilder() {
        private var record: DirectoryRecord? = null

        override fun reset() {
            super.reset()
            record = null
        }

        fun startFetching(record: DirectoryRecord) {
            check(this.record == null)
            this.record = record
        }

        override fun getResult(): DirectoryRecord {
            if (record?.isFetched == true) {
                return record!!
            }
            check(record != null)
            val childrenList = children.map { it.record }.sortedBy { it.name }
            checkConsistencyWith(childrenList)
            record!!.children = childrenList
            record!!.isFetched = true
            return record!!
        }

        private fun checkConsistencyWith(list: List<Record>) {
            if (hasher.calculate(list.joinToString(CANONICAL_RECORD_LINE_SEPARATOR)) != record!!.hash)
                throw FetchingConsistencyException()
        }
    }
}

open class FetchingException(message: String) : Exception(message)

class FetchingConsistencyException : FetchingException("Fetched content's hash differs from prefetched hash.")

const val CANONICAL_RECORD_LINE_SEPARATOR = "\n"
