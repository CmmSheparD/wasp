package core.records

class FileRecord(val name: String) : Record {
    fun isSameFile(file: FileRecord) = name == file.name
}