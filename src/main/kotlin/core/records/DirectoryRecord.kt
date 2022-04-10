package core.records

class DirectoryRecord(val name: String) {
    data class DirEntry(val name: String, val hash: String, val type: DirEntryType)
    enum class DirEntryType {
        FILE,
        DIR;
    }

    private val entries = mutableListOf<DirEntry>()

    fun listEntries(): List<DirEntry> = entries

    fun addEntry(name: String, hash: String, type: DirEntryType) {
        removeEntryByName(name)
        entries.add(DirEntry(name, hash, type))
    }

    fun removeEntryByName(name: String) {
        entries.removeIf { it.name == name }
    }

    fun isSameDirectory(dir: DirectoryRecord) = name == dir.name
}