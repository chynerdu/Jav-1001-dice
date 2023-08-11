package com.example.idice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.idice.databinding.ActivityMainBinding
import android.R.*
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout

private lateinit var binding: ActivityMainBinding
private var selectedType = "roll once"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        print("started triggered")
//        val motionContainer = findViewById<MotionLayout>(R.id.ScrollView01)
        binding.ScrollView01.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                print("transition changed")
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {

                if (selectedType == "roll once") {
                    var dice = rollDice()
                    binding.textView.text = dice
                    binding.result.text = "First roll: " + dice
                } else {
                    var dice1 = rollDice()
                    var dice2 = rollDice()
                    binding.textView.text = dice2
                    binding.result.text = "First roll: " + dice1 + ", Second roll: " + dice2
                }

            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
                print("transition triggered")
            }

        })
    }

    fun rollDice(): String {
       var result = (1..6).random().toString()
        return result;
    }

    fun onRadioButtonClicked(view: View) {

        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

//             Check which radio button was clicked
            when (view.getId()) {
                binding.rollOnce.id ->
                    if (checked) {
                        selectedType = "roll once"
                        binding.textView.text = "Tap to roll"

                    }
                binding.rollTwice.id ->
                    if (checked) {
                        selectedType = "roll twice"
                        binding.textView.text = "Tap to roll"


                    }
            }
        }
        Toast.makeText(
            this,
            selectedType + " Selected",
            Toast.LENGTH_SHORT
        ).show()

    }




}