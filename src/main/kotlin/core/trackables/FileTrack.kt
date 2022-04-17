package core.trackables

class FileTrack(val name: String) : Trackable {
    fun isSameFile(file: FileTrack) = name == file.name
}