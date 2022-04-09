package core.primitive

/**
 * Primitive is the most basic storage item, capable of telling what it is and returning the text representation
 * of its contents.
 */
interface Primitive {
    val type: PrimitiveType
    fun getContent(): String
}

enum class PrimitiveType(string: String) {
    FILE("file"),
    DIR("dir"),
    COMMIT("commit");
}
