package com.example.bavt_oblig1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

class QuizActivity : AppCompatActivity(), View.OnClickListener {


    var questionList: MutableList<Question> = mutableListOf()
    var counter = 0
    var points = 0;
    var totalQuestions = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        applicationContext.assets.open("questions.txt").bufferedReader().forEachLine {
            var newQuestion = Question(it.split(";")[0].toBoolean(),it.split(";")[1])
            questionList.add(newQuestion)
        }
        totalQuestions = questionList.size
        val message = intent.getStringExtra("input");

        var output1: TextView = findViewById(R.id.inputFromPalindromeChecker)
        output1.setText(message)
        var output2: TextView = findViewById(R.id.questionView)
        output2.setText(questionList[0].text)
        var output3: TextView = findViewById(R.id.pointsView)
        output3.setText("Antall poeng: " + points.toString())

        var fakta= findViewById<Button>(R.id.fakta)
        var fleip = findViewById<Button>(R.id.fleip)
        var reset = findViewById<Button>(R.id.reset)
        fakta.setOnClickListener(this)
        fleip.setOnClickListener(this)
        reset.setOnClickListener(this)

    }

    fun nextActivity(view: View) {
        val message = "Welcome to the next activity";
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

    override fun onClick(view: View){
        var fakta = findViewById<Button>(R.id.fakta)
        var fleip = findViewById<Button>(R.id.fleip)
        var reset = findViewById<Button>(R.id.reset)

        if(view.id == reset.id){
            counter = 0;
            points = 0;
            val duration = Toast.LENGTH_SHORT
            Toast.makeText(applicationContext,"Restarter quiz",duration).show()
            var output = findViewById<TextView>(R.id.pointsView)
            var text : String = "Antall poeng: " + points
            displayMessage(text,output)
            var newQuestion: TextView = findViewById(R.id.questionView)
            displayMessage(questionList[counter].text,newQuestion)
            fakta.isClickable = true
            fleip.isClickable = true
            fakta.isVisible = true
            fleip.isVisible = true
        }

        else{

            if(view.id == fakta.id){
                if(questionList[counter].answer){
                    points++
                }
            }
            if(view.id == fleip.id){
                if(!questionList[counter].answer){
                    points++
                }
            }
            counter++
            if(counter<questionList.size){
                var newQuestion: TextView = findViewById(R.id.questionView)
                displayMessage(questionList[counter].text,newQuestion)
            }
            var output = findViewById<TextView>(R.id.pointsView)
            var text : String = "Antall poeng: " + points
            displayMessage(text,output)
            if(counter==questionList.size){
                var end: TextView = findViewById(R.id.pointsView)
                var endscore : String = "totalscore: " + points.toString() + "/" + totalQuestions.toString() + "\n Trykk reset for å restarte quiz d"
                displayMessage(endscore,end)
                fakta.isClickable = false
                fleip.isClickable = false
                fakta.isInvisible = true
                fleip.isInvisible = true

            }
        }
    }

    fun displayMessage(text: String, view: TextView){
        val output: TextView = view
        output.setText(text)
    }
}
