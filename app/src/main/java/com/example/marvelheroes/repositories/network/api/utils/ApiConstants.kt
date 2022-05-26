package com.example.marvelheroes.repositories.network.api.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.Timestamp
import java.time.Instant
import java.time.format.DateTimeFormatter

object ApiConstants {
    const val BASE_URL = "https://gateway.marvel.com"
    const val PRIVATE_KEY = "dd59389ca1eadb7d1405ba5bd802922a8663a4d3"
    const val PUBLIC_KEY = "1d90d4e7237d341041c5451496af7e3f"

    fun generateTimestamp(): String {
        return DateTimeFormatter.ISO_INSTANT.format(Instant.now())
    }

    fun hash(timestamp: String) : String {
        return "$timestamp$PRIVATE_KEY$PUBLIC_KEY"
    }

     fun convertToMd5(input: String): String {
        val md5Value = MessageDigest.getInstance("MD5")
        return BigInteger(1, md5Value.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}

