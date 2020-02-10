package com.example.bavt_oblig1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var palindromtext: String = ""

    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
        val textword = editText.text.toString()
        if(checkPalindrom(textword) == false){
            val message = textword + " er IKKE et palindrom "
            displayMessage(message)
            palindromtext = textword

        }
        else{
            val message = textword + " er et palindrom "
            displayMessage(message)
            palindromtext = textword
        }
        editText.setText("")
        editText.clearFocus()
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    fun displayMessage(text: String){
        val output: TextView = findViewById(R.id.inputFromPalindromeChecker)
        output.setText(text)
    }

    fun checkPalindrom(userInput: String): Boolean{
        var userInput = userInput.toUpperCase()
        if(userInput.length % 2 == 0){
            var inputPart1 = userInput.slice(0 until userInput.length/2)
            var inputPart2 = userInput.slice(userInput.length/2 until userInput.length)
            if(inputPart1.equals(inputPart2.reversed())){
                return true
            }
            return false
        }
        else{
            var inputPart1 = userInput.slice(0 until userInput.length/2)
            var inputPart2 = userInput.slice(userInput.length/2+1 until userInput.length)
            if(inputPart1.equals(inputPart2.reversed())){
                return true
            }
            return false
        }

    }


    fun nextActivity(view: View) {

        if(palindromtext.equals("")){
            displayMessage("Du har ikke tastet inn og sendt et ord til sjekk for palindromini")
        }
        else{
            val intent = Intent(this, ConverterActivity::class.java).apply {
                putExtra("input",palindromtext)
            }
            startActivity(intent)
        }
    }


}
