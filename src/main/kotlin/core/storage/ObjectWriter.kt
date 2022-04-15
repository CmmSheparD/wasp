package core.storage

interface ObjectWriter {
    /*
     * Saves content and returns hash of it as a unique state identifier. This hash can be used to retrieve
     * this object via RecordReader.
     */
    fun storeObject(content: String): String
}