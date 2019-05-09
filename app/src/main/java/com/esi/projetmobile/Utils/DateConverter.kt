package com.esi.projetmobile.Utils

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateConverter {

    companion object {
        @TypeConverter
        @JvmStatic
        fun toDate(time: Long): Date {
            return Date(time)
        }

        @TypeConverter
        @JvmStatic
        fun fromDate(date: Date): Long {
            return date.time
        }
    }
}