package core.storage.records

interface DirectoryRecordBuilder {
    fun reset()
    fun addChild(record: Record)
    fun getResult(): DirectoryRecord
}
