package core.records

class FileRecord(val name: String) {
    fun isSameFile(file: FileRecord) = name == file.name
}