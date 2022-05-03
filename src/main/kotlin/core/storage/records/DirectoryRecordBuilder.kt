package core.storage.records

interface DirectoryRecordBuilder {
    fun reset()
    fun addChild(record: Record)
    fun addChildren(records: List<Record>)
    fun getResult(): DirectoryRecord
}
