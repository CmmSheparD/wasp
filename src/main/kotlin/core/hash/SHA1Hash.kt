package core.hash

import java.security.MessageDigest

object SHA1Hash : HashCalculator {
    private val hashFunc = MessageDigest.getInstance("SHA1")

    override fun calculate(data: String): String {
        return hashFunc.digest(data.toByteArray()).joinToString("") { "%02x".format(it) }
    }
}
