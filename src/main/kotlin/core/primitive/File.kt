package core.primitive

class File(val text: String) : Primitive {
    override val type = PrimitiveType.FILE
    override fun getContent() = text
}