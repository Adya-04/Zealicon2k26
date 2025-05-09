package com.gdg.zealicon2k25.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Common {
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun formatEventDate(dateTimeString: String?): String {
        return try {
            val zonedDateTime = ZonedDateTime.parse(dateTimeString)
            val formatter = DateTimeFormatter.ofPattern("dd MMM")
            zonedDateTime.format(formatter)
        } catch (e: Exception) {
            "N/A"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatEventTime(dateTimeString: String?): String {
        return try {
            val zonedDateTime = ZonedDateTime.parse(dateTimeString)
            val formatter = DateTimeFormatter.ofPattern("hh:mm a")
            zonedDateTime.format(formatter)
        } catch (e: Exception) {
            "N/A"
        }
    }

}