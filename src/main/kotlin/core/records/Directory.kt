package core.records

class Directory : Primitive {
    override val type = PrimitiveType.DIR

    data class DirEntry(val name: String, val hash: String, val type: PrimitiveType)
    private val entries = mutableListOf<DirEntry>()

    override fun getContent(): String {
        entries.sortBy { it.name }
        return entries.joinToString("\n") { "${it.hash}\t${it.type}\t${it.name}" }
    }

    fun listEntries(): List<DirEntry> = entries

    fun addEntry(name: String, hash: String, type: PrimitiveType) {
        removeEntryByName(name)
        entries.add(DirEntry(name, hash, type))
    }

    fun addEntry(entry: DirEntry) {
        removeEntryByName(entry.name)
        entries.add(entry)
    }

    fun removeEntryByName(name: String) {
        entries.removeIf { it.name == name }
    }
}