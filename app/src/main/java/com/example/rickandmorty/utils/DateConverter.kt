package com.example.rickandmorty.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateConverter {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun convertDate(date: String): String {
            val date = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
            val formattedDate = date.format(DateTimeFormatter.ofPattern("d MMMM yyyy, HH:mm:ss"))
            return formattedDate.toString()
        }
    }
}