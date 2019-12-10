package com.mpharma.testEngine.utils

object StringUtil {

    fun isAnyNullorBlank(vararg strings: String) : Boolean {
        for (s in strings) {
            if (s.isNullOrBlank()) {
                return true
            }
        }

        return false
    }
}