package de.schwarz.integration.util

import java.time.DateTimeException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateUtil {
    def static formatDate(String dateTime) {
        if (dateTime) {
            try {
                LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.][SSSS][SSS][SS][S]"))
            } catch (DateTimeException ignored) {
//Ignore Exception
            }
        }
    }

}
