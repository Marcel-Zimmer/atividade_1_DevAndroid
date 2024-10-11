package com.example.mathgame

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var output1: TextView
    private lateinit var output2: TextView
    private lateinit var outputFinish: TextView
    private lateinit var textResponse : TextView
    private lateinit var constraintLayout : ConstraintLayout
    private lateinit var buttonNext : Button
    private lateinit var buttonResponse : Button
    private lateinit var error :TextView
    private lateinit var res : EditText
    private var attenpts : Int = 0
    private var score : Int = 0
    private var randomNumber1:Int = 0
    private var randomNumber2:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        output1 = findViewById(R.id.textViewOutput1)
        output2 = findViewById(R.id.textViewOutput2)
        outputFinish = findViewById(R.id.textViewFinish)
        constraintLayout = findViewById(R.id.main)
        buttonNext = findViewById(R.id.buttonNext)
        buttonResponse = findViewById(R.id.buttonResponse)

        error = findViewById(R.id.textViewError)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        draw()
    }
    fun response(view: View){
        constraintLayout = findViewById(R.id.main)
        res = findViewById<EditText?>(R.id.editTextResponse)
        if(res.length() != 0){
            attenpts += 1
            if (randomNumber1 + randomNumber2 == res.text.toString().toInt()) {
                constraintLayout.background = ColorDrawable(Color.parseColor("#93bf85"))
                buttonResponse.visibility = View.INVISIBLE
                buttonNext.visibility = View.VISIBLE
                score += 20
                error.visibility = View.VISIBLE
                error.text = "Resposta Correta"

            } else {
                error.text = "A resposta certa é : " + (randomNumber1 + randomNumber2)
                error.visibility = View.VISIBLE
                constraintLayout.background = ColorDrawable(Color.parseColor("#FF6347"))
                buttonNext.visibility = View.VISIBLE
                buttonResponse.visibility = View.INVISIBLE
            }
        }else{
            Toast.makeText(this,"Insira sua resposta",Toast.LENGTH_SHORT).show()
        }
    }
    fun next(view : View){
        if(attenpts < 5) {
            draw()
        }
        else{
            drawFinish()
        }

    }
    private fun draw(){
        randomNumber1 = (0..99).random()
        randomNumber2 = (0..99).random()
        output1.text = randomNumber1.toString()
        output2.text = randomNumber2.toString()
        textResponse = findViewById(R.id.editTextResponse)
        textResponse.text = ""
        constraintLayout.background = ColorDrawable(Color.parseColor("#FFFFFF"))
        error.visibility = View.INVISIBLE
        outputFinish.visibility = View.INVISIBLE
        buttonNext.visibility = View.INVISIBLE
        buttonResponse.visibility = View.VISIBLE

    }

    private fun drawFinish(){
        val textViewSum = findViewById<TextView>(R.id.textView)
        val textViewEqual = findViewById<TextView>(R.id.textView5)
        output1 = findViewById(R.id.textViewOutput1)
        output2 = findViewById(R.id.textViewOutput2)
        constraintLayout = findViewById<ConstraintLayout>(R.id.main)
        res.visibility = View.INVISIBLE
        buttonResponse.visibility = View.INVISIBLE
        buttonNext.visibility = View.INVISIBLE
        output1.visibility = View.INVISIBLE
        output2.visibility = View.INVISIBLE
        textViewSum.visibility = View.INVISIBLE
        textViewEqual.visibility = View.INVISIBLE
        error.visibility = View.INVISIBLE
        constraintLayout.background = ColorDrawable(Color.parseColor("#FFFFFF"))
        outputFinish.visibility = View.VISIBLE
        outputFinish.text = "Sua pontuação total foi de : " + score

    }

}