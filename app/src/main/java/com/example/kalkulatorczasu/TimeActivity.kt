package com.example.kalkulatorczasu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView

class TimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)
        val sumButton: Button = findViewById(R.id.plusButton2)
        val minusButton: Button = findViewById(R.id.minusButton)
        val acButton: Button = findViewById(R.id.acButton)
        val hoursPicker = findViewById<NumberPicker>(R.id.hoursPicker)
        val timeText = findViewById<TextView>(R.id.timeText)
        hoursPicker.minValue = 0
        hoursPicker.maxValue = 23
        val minutesPicker = findViewById<NumberPicker>(R.id.minutesPicker)
        minutesPicker.minValue = 0
        minutesPicker.maxValue = 59
        val secondsPicker = findViewById<NumberPicker>(R.id.secondsPicker)
        secondsPicker.minValue = 0
        secondsPicker.maxValue = 59
        val hoursPicker2 = findViewById<NumberPicker>(R.id.hoursPicker2)
        hoursPicker2.minValue = 0
        hoursPicker2.maxValue = 23
        val minutesPicker2 = findViewById<NumberPicker>(R.id.minutesPicker2)
        minutesPicker2.minValue = 0
        minutesPicker2.maxValue = 59
        val secondsPicker2 = findViewById<NumberPicker>(R.id.secondsPicker2)
        secondsPicker2.minValue = 0
        secondsPicker2.maxValue = 59


        //obliczanie różnicy pomiędzy czasami
        minusButton.setOnClickListener {
            val hours1 = hoursPicker.value
            val minutes1 = minutesPicker.value
            val seconds1 = secondsPicker.value
            val hours2 = hoursPicker2.value
            val minutes2 = minutesPicker2.value
            val seconds2 = secondsPicker2.value
            var timeDifferenceInSeconds = (hours2 * 3600 + minutes2 * 60 + seconds2) - (hours1 * 3600 + minutes1 * 60 + seconds1)
            if (timeDifferenceInSeconds < 0) {
                timeDifferenceInSeconds = -timeDifferenceInSeconds
            }
            val hoursDifference = timeDifferenceInSeconds / 3600
            val minutesDifference = (timeDifferenceInSeconds % 3600) / 60
            val secondsDifference = timeDifferenceInSeconds % 60
            timeText.text = "godziny: $hoursDifference minuty: $minutesDifference sekundy: $secondsDifference"

        }
        //obliczanie sumy pomiędzy czasami
        sumButton.setOnClickListener{
            val hours1 = hoursPicker.value
            val minutes1 = minutesPicker.value
            val seconds1 = secondsPicker.value
            val hours2 = hoursPicker2.value
            val minutes2 = minutesPicker2.value
            val seconds2 = secondsPicker2.value
            var timeSumInSeconds = (hours2 * 3600 + minutes2 * 60 + seconds2) + (hours1 * 3600 + minutes1 * 60 + seconds1)
            val hoursSum = timeSumInSeconds / 3600
            val minutesSum = (timeSumInSeconds % 3600) / 60
            val secondsSum = timeSumInSeconds % 60
            val daysSum = hoursSum / 24
            timeText.text = "dni: $daysSum\ngodziny: $hoursSum\nminuty: $minutesSum\nsekundy: $secondsSum"

        }

        // czyszczenie wyników i numberPickerów
        acButton.setOnClickListener{
            val pickers = arrayOf(hoursPicker, minutesPicker, secondsPicker, hoursPicker2, minutesPicker2, secondsPicker2)
            for (picker in pickers) {
                picker.value = picker.minValue
            }
            timeText.text = ""

        }



    }

}