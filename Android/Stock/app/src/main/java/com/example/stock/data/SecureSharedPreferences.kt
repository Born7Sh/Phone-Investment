package com.example.stock.data

import android.content.SharedPreferences
import android.util.Log

// https://hyperconnect.github.io/2018/06/03/android-secure-sharedpref-howto.html (참고 블로그)
// 키 교환을 위한 보안

class SecureSharedPreferences(private val sharedPref: SharedPreferences) {
    fun contains(key: String) = sharedPref.contains(key)

    fun get(key: String, defaultValue: Boolean): Boolean = getInternal(key, defaultValue)

    fun get(key: String, defaultValue: Int): Int = getInternal(key, defaultValue)

    fun get(key: String, defaultValue: Long): Long = getInternal(key, defaultValue)

    fun get(key: String, defaultValue: String): String = getInternal(key, defaultValue)

    /**
     * Client codes must handle ClassCastException by their own at the call site if happens.
     */
    private fun <T : Any> getInternal(key: String, defaultValue: T): T {
        val str = sharedPref.getString(key, "")
        if (str.isNullOrEmpty()) {
            return defaultValue
        }
        val value = AndroidKeyStoreUtil.decrypt(str)

        @Suppress("PlatformExtensionReceiverOfInline", "UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
        return when (defaultValue) {
            is Boolean -> value.toBoolean()
            is Int -> value.toInt()
            is Long -> value.toLong()
            is String -> value
            else -> throw IllegalArgumentException("defaultValue only could be one of these types: Boolean, Int, Long, String")
        } as T
    }

    fun put(key: String, value: Boolean) = putInternal(key, value)

    fun put(key: String, value: Int) = putInternal(key, value)

    fun put(key: String, value: Long) = putInternal(key, value)

    /**
     * Due to the encryption layer limitations, input value must be shorter than [CipherHelper.KEY_LENGTH_BIT] bytes.
     */
    fun put(key: String, value: String) = putInternal(key, value)

    private fun putInternal(key: String, value: Any?) {
        try {
            sharedPref.edit().run {
                if (value == null) {
                    Log.v("items", "putInternal 조건 들어감")
                    remove(key)
                } else {
                    Log.v("items", "value.toString() = " + value.toString())
                    putString(key, AndroidKeyStoreUtil.encrypt(value.toString()))
                }

                apply()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    companion object {
        fun wrap(sharedPref: SharedPreferences) = SecureSharedPreferences(sharedPref)
    }
}