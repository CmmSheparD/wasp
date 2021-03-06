package core.trackables

data class FileTrack(override val name: String) : Trackable {
    fun isSameFile(file: FileTrack) = name == file.name
}