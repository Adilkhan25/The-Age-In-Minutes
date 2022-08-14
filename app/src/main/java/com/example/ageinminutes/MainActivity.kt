package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private  var selectDate : TextView? = null
    private  var finalDate : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDate : Button = findViewById(R.id.btnDatePicker)
        selectDate = findViewById(R.id.tvSelectedDate)
        finalDate = findViewById(R.id.tvFinalDate)
        btnDate.setOnClickListener {

            clickDatePicker() //
        }
    }
    private fun clickDatePicker()
    {

        val myCalender : Calendar = Calendar.getInstance()

        val year = myCalender.get(Calendar.YEAR)

       val month = myCalender.get(Calendar.MONTH)

        val days = myCalender.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
                val selected = "$dayOfMonth/${month+1}/$year"
                selectDate?.text = selected

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selected)

                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000


                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000


                        val diffDateInMinutes = currentDateInMinutes - selectedDateInMinutes
                        finalDate?.text = diffDateInMinutes.toString()
                    }
                }

            },
            year,
            month,
            days
        ).show()
    }
}