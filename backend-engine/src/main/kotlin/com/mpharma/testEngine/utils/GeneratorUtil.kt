package com.mpharma.testEngine.utils

import java.util.Random

object GeneratorUtil {

    private const val CHARS: String = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"

    fun generateAlphanumeric(length: Int) : String {
        var randomString = ""
        for (i in 1..length) {
            val charIndex: Int = Random().nextInt(CHARS.length)
            randomString += CHARS[charIndex].toString()
        }

        return randomString
    }

    fun generateNumeric(length: Int) : String {
        var randomIntString = ""
        for (i in 1..length) {
            randomIntString += Random().nextInt(9).toString()
        }

        return randomIntString
    }
}