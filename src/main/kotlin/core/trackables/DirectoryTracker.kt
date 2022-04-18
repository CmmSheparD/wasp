package core.trackables

data class DirectoryTracker(override val name: String) : Tracker {
    private val contents = mutableSetOf<Trackable>()

    override fun startTracking(what: Trackable) = contents.add(what)
    override fun stopTracking(what: Trackable) = contents.remove(what)

    override fun isTracked(what: Trackable) = contents.contains(what)

    override fun listTracked() = contents.toList()
}