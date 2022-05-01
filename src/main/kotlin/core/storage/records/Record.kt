package core.storage.records

/*
 * All records are immutable, and are effectively data structures.
 */
sealed class Record(val name: String, val hash: String) {
    val typeString: String
        get() = when (this) {
            is FileRecord -> FILE_TYPE_STRING
            is DirectoryRecord -> DIRECTORY_TYPE_STRING
        }

    init {
        require(name.isNotBlank())
        require(hash.isNotBlank())
    }

    final override fun toString() = "$hash $typeString $name"
}

const val FILE_TYPE_STRING = "file"
const val DIRECTORY_TYPE_STRING = "dir"
