package com.example.calendarview

import android.app.Activity
import android.os.Bundle
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import java.util.logging.SimpleFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val secondsInMilli: Long = 1000
    private val minutesInMilli = secondsInMilli * 60
    private val hoursInMilli = minutesInMilli * 60
    private val daysInMilli = hoursInMilli * 24

    fun CalculateDays(datawyjazdu : Long, datapowrotu : Long) : Long
    {
        return (datapowrotu-datawyjazdu) / daysInMilli + 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kalendarz = binding.kalendarz
        val output = binding.output

        var dataWyjazdu = 0L
        var dataPowrotu = 0L

        val dwaLata = 63113904000;

        kalendarz.setMinDate(kalendarz.date)
        kalendarz.setMaxDate(kalendarz.date + dwaLata)

        binding.kalendarz.setOnDateChangeListener { view, year, month, dayOfMonth ->
            binding.kalendarz.date = Date.UTC(year - 1900, month, dayOfMonth, 4, 4, 4)
        }

        binding.odjazdButton.setOnClickListener {
            dataWyjazdu = kalendarz.date
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            output.text = "Odjazd: " + formatter.format(dataWyjazdu).toString() + "\n"
        }

        binding.powrotButton.setOnClickListener {
            dataPowrotu = kalendarz.date
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            output.text = "Odjazd: " + formatter.format(dataPowrotu).toString() + "\n"
        }

        binding.obliczButton.setOnClickListener {
            var outputStream = ""
            outputStream += "Dlugosc wyjazdu: ${CalculateDays(dataWyjazdu, dataPowrotu)}"
            output.text = outputStream
        }
    }
}