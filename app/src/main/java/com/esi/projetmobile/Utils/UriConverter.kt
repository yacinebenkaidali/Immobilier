package com.esi.projetmobile.Utils

import android.arch.persistence.room.TypeConverter
import android.net.Uri

class UriConverter {

    companion object {
        @TypeConverter
        @JvmStatic
        fun toString(uri: Uri): String {
            return uri.toString()
        }
        @TypeConverter
        @JvmStatic
        fun toUri(uri: String): Uri {
            return Uri.parse(uri)
        }
    }
}