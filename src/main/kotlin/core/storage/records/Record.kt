package core.storage.records

/*
 * All records are immutable, and are effectively data structures.
 */
sealed interface Record {
    val name: String
    val hash: String
}

internal const val DEFAULT_RECORD_STRING_VALUE = ""
