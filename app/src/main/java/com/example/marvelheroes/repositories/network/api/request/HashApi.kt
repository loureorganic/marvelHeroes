package com.example.marvelheroes.repositories.network.api.request

import com.example.marvelheroes.repositories.network.api.utils.ApiConstants
import java.math.BigInteger
import java.security.MessageDigest
import java.time.Instant
import java.time.format.DateTimeFormatter

class HashApi {

    private fun convertToMd5(input: String): String {
        val md5Value = MessageDigest.getInstance("MD5")
        return BigInteger(1, md5Value.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    private fun timestamp(): String {
        return DateTimeFormatter.ISO_INSTANT.format(Instant.now())
    }

    fun generateUrl(): String {
        return "/v1/public/characters&ts=${timestamp()}&apikey=${
            convertToMd5(
                timestamp() + ApiConstants.PRIVATE_KEY + ApiConstants.PUBLIC_KEY
            )
        }";
    }

}