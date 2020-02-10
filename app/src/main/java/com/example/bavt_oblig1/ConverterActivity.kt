package com.example.bavt_oblig1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

import android.widget.Spinner
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode


class ConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
    }

    fun sendMessage(view: View) {
        val volume = findViewById<EditText>(R.id.editText_02)
        if(volume.text.toString().trim().length == 0){
            val duration = Toast.LENGTH_SHORT
            Toast.makeText(applicationContext,"Du har ikke tastet inn input",duration).show()

        }
        else{
            val volumeDouble = volume.text.toString().toDouble()
            displayMessage(volumeDouble)
        }
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    fun displayMessage(volume: Double){
        val mySpinner = findViewById<Spinner>(R.id.spinner)
        val unitChoice = mySpinner.getSelectedItem().toString()
        var convertedVolume: Double = volume

        if(unitChoice.equals("fluid ounce")){
            convertedVolume = volume * 0.02957
        }
        if(unitChoice.equals("cup")){
            convertedVolume = volume * 0.23659
        }
        if(unitChoice.equals("gallon")){
            convertedVolume = volume * 3.78541
        }
        if(unitChoice.equals("hogshead")){
            convertedVolume = volume * 238.481
        }

        val output: TextView = findViewById(R.id.questionView)
        val roundedVolume = BigDecimal(convertedVolume).setScale(2,RoundingMode.HALF_EVEN)
        val textOutput: String =  roundedVolume.toString() + " L"
        output.setText(textOutput)
    }


    fun nextActivity(view: View) {
        val message = intent.getStringExtra("input");
        val intent = Intent(this, QuizActivity::class.java).apply {
            putExtra("input", message)
        }
        startActivity(intent)
    }
}
