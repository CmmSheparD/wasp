package core.trackables

sealed interface Tracker : Trackable {
    fun startTracking(what: Trackable): Boolean
    fun stopTracking(what: Trackable): Boolean
    fun isTracked(what: Trackable): Boolean
    fun listTracked(): List<Trackable>
}