package core.storage.records

abstract class SetDirectoryRecordBuilder : DirectoryRecordBuilder {
    protected class RecordHolder(val record: Record) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as RecordHolder

            if (record.name != other.record.name) return false

            return true
        }

        override fun hashCode(): Int {
            return record.name.hashCode()
        }
    }
    protected var children = mutableSetOf<RecordHolder>()

    override fun reset() {
        children.clear()
    }

    override fun addChild(record: Record) {
        val holder = RecordHolder(record)
        if (children.contains(holder)) children.remove(holder)
        children.add(holder)
    }

    override fun addChildren(records: List<Record>) {
        for (record in records) addChild(record)
    }
}
