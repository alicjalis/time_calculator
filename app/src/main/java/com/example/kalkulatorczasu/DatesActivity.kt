package com.example.kalkulatorczasu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import java.lang.Math.abs
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class DatesActivity : AppCompatActivity() {
    private val holidayDates = listOf(
        "01-01", "01-06", "05-01", "05-03", "08-15", "11-01", "11-11", "12-25", "12-26"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dates)
        val datePicker1: DatePicker = findViewById(R.id.datePicker1)
        val datePicker2: DatePicker = findViewById(R.id.datePicker2)
        val daysDifference = findViewById<TextView>(R.id.daysDifference)
        val workDaysDifference = findViewById<TextView>(R.id.workDaysDifference)
        val plusButton = findViewById<Button>(R.id.plusButton)

        plusButton.setOnClickListener {
            val year1 = datePicker1.year
            val month1 = datePicker1.month
            val day1 = datePicker1.dayOfMonth

            val year2 = datePicker2.year
            val month2 = datePicker2.month
            val day2 = datePicker2.dayOfMonth

            // konwersja na obiekt typu calendar
            val calendar1 = Calendar.getInstance().apply {
                set(year1, month1, day1)
            }

            val calendar2 = Calendar.getInstance().apply {
                set(year2, month2, day2)
            }
            // Obliczenie daty Wielkanocy
            val easterDate = calculateEaster(calendar1.get(Calendar.YEAR))
//            val easterEpoch = easterDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond()
//            val easterDateTime =
//                calculateEaster(calendar1.get(Calendar.YEAR)).atStartOfDay(ZoneOffset.UTC)



            // Znalezienie dnia pomiędzy datami
            val diffInMillis = abs(calendar2.timeInMillis - calendar1.timeInMillis)
            val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis)
            val days = abs(diffInDays)

            // Znalezienie ilości dni roboczych pomiędzy datami
            var workDays = 0
            if (calendar1 == calendar2) {
                workDays = 0
            } else {
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = calendar1.timeInMillis
                while (calendar.timeInMillis <= calendar2.timeInMillis) {
                    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
                    val monthDay =
                        "${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)}"
                    if (dayOfWeek in 2..6 && !holidayDates.contains(monthDay) && calendar.timeInMillis != calculateEaster(
                            calendar.get(Calendar.YEAR)
                        ).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
                    ) {
                        workDays++
                    }
                    calendar.add(Calendar.DAY_OF_MONTH, 1)
                }
            }

            daysDifference.text = days.toString()
            workDaysDifference.text = workDays.toString()


        }


    }

//    private fun isHoliday(date: LocalDate): Boolean {
//        val formattedDate = date.format(DateTimeFormatter.ofPattern("MM-dd"))
//        return formattedDate in holidayDates
//                || date == calculateEaster(date.year).minusDays(2) // odjęcie Wielkiego Piątku
//    }


    private fun calculateEaster(year: Int): LocalDate {
        val a = year % 19
        val b = year / 100
        val c = year % 100
        val d = b / 4
        val e = b % 4
        val f = (b + 8) / 25
        val g = (b - f + 1) / 3
        val h = (19 * a + b - d - g + 15) % 30
        val i = c / 4
        val k = c % 4
        val l = (32 + 2 * e + 2 * i - h - k) % 7
        val m = (a + 11 * h + 22 * l) / 451
        val month = (h + l - 7 * m + 114) / 31
        val day = ((h + l - 7 * m + 114) % 31) + 1
        return LocalDate.of(year, month, day)
    }
}



