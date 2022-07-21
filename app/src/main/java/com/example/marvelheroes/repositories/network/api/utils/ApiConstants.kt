package com.example.marvelheroes.repositories.network.api.utils

import com.example.marvelheroes.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.time.Instant
import java.time.format.DateTimeFormatter

object ApiConstants {
    const val BASE_URL = BuildConfig.BASE_URL
    const val PRIVATE_KEY = BuildConfig.API_KEY_PRIVATE
    const val PUBLIC_KEY = BuildConfig.API_KEY_PUBLIC


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

