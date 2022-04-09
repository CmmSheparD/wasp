package core.primitive

class Directory : Primitive {
    override val type = PrimitiveType.DIR

    private data class DirEntry(val name: String, val hash: String, val type: PrimitiveType)
    private val entries = mutableListOf<DirEntry>()

    override fun getContent(): String {
        entries.sortBy { it.name }
        return entries.joinToString("\n") { "${it.hash}\t${it.type}\t${it.name}" }
    }

    fun addEntry(name: String, hash: String, type: PrimitiveType) {
        removeEntry(name)
        entries.add(DirEntry(name, hash, type))
    }

    fun removeEntry(name: String) {
        entries.removeIf { it.name == name }
    }
}