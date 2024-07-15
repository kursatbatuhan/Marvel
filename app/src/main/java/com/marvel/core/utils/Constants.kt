package com.marvel.core.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {
    object DeepLinkKeys {
        const val TRUSTLY = "TRUSTLY"
        const val SUCCESS = "SUCCESS"
        const val FAIL = "FAIL"
        const val successOrFail = "successOrFail"
        const val key = "key"
    }

    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "d88b6fc86ba18356ce2511781d2296dc"
        const val PRIVATE_KEY = "7803de9f826c853a02515c3c1d0d3f5465f69938"
        fun hash(): String {
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
    }

    object StringParameter {
        const val EMPTY_STRING = ""
        const val UNEXPECTED_ERROR = "An Unexpected Error"
        const val DOT = "."
        const val NO_INFO = "There is no information about him. He lives in legends"
        const val IMAGE_NOT_AVAILABLE = "image_not_available"
        const val MARVEL_LOGO_URL = "https://motocupid.com/wp-content/uploads/2022/06/MARVEL.jpg"
    }
}
