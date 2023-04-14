package com.example.proverreshenie

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ButtonStart = findViewById<Button>(R.id.btnStart3)
        val ButtonRignt = findViewById<Button>(R.id.btnRight)
        val ButtonWrong = findViewById<Button>(R.id.btnWrong)
        val FirstOperand = findViewById<TextView>(R.id.txtFirstOperand)
        val TwoOperand = findViewById<TextView>(R.id.txtTwoOperand)
        val Operation = findViewById<TextView>(R.id.txtOperation)
        val Result = findViewById<TextView>(R.id.txtResult)
        val NumberRight = findViewById<TextView>(R.id.txtNumberRight)
        val NumberWrong = findViewById<TextView>(R.id.txtNumberWrong)
        val AllExamples = findViewById<TextView>(R.id.txtAllExamples3)
        val PercentageCorrectAnswers = findViewById<TextView>(R.id.txtPercentageCorrectAnswers3)
        val TimeMin = findViewById<TextView>(R.id.txtTimeMin)
        val TimeMax = findViewById<TextView>(R.id.txtTimeMax)
        val TimeAverage = findViewById<TextView>(R.id.txtTimeAverage)
        var NRight = 0
        var NWrong = 0
        var rnd = Random
        var Time:Double
        var Time1:Long = 0
        var AllTime:Double = 0.0
        var Counter:Int=0
        val Oper: List<String> = listOf("+", "-", "/", "*")

        fun Random(): Triple<String, String, String> {
            Operation.text = Oper.elementAt(rnd.nextInt(0, 4))
            var First = rnd.nextInt(10, 100)
            var Second = rnd.nextInt(10, 100)
            if (Operation.text == "/") {
                while (First.toDouble() % Second.toDouble() != 0.0) {
                    First = rnd.nextInt(10, 100)
                    Second = rnd.nextInt(10, 100)
                }
            }
            if (Operation.text == "*") {
                First = rnd.nextInt(1, 10)
                Second = rnd.nextInt(1, 10)
            }
            FirstOperand.text = First.toString()
            TwoOperand.text = Second.toString()
            return Triple(FirstOperand.text.toString(),TwoOperand.text.toString(),Operation.text.toString())
        }

        fun Calculation(): Boolean {
            return when (Operation.text) {
                "+" -> {
                    (FirstOperand.text.toString().toInt() + TwoOperand.text.toString()
                        .toInt() == Result.text.toString().toInt())
                }
                "-" -> {
                    (FirstOperand.text.toString().toInt() - TwoOperand.text.toString()
                        .toInt() == Result.text.toString().toInt())
                }
                "/" -> {
                    (FirstOperand.text.toString().toInt() / TwoOperand.text.toString()
                        .toInt() == Result.text.toString().toInt())
                }
                "*" -> {
                    (FirstOperand.text.toString().toInt() * TwoOperand.text.toString()
                        .toInt() == Result.text.toString().toInt())
                }
                else -> false
            }
        }
        fun RandomResult(First: String,Second:String,Oper:String) {
            val result = when (Oper) {
                "+" -> {
                    First.toInt() + Second.toInt()
                }
                "-" -> {
                    First.toInt() - Second.toInt()
                }
                "/" -> {
                    First.toInt() / Second.toInt()
                }
                "*" -> {
                    First.toInt() * Second.toInt()
                }
                else -> 0
            }
            if (rnd.nextBoolean()) {
                Result.text = result.toString()
            } else {
                Result.text = rnd.nextInt(20, 200).toString()
            }
        }
        fun GetTime(Time:Double)
        {
            if(Counter==0)
            {
                TimeMin.text=Time.toString()
            }
            if(TimeMin.text.toString().toDouble()>Time)
            {
                TimeMin.text=Time.toString()
            }
            if(TimeMax.text.toString().toDouble()<Time)
            {
                TimeMax.text=Time.toString()
            }
            ++Counter;
            AllTime+=Time
            TimeAverage.text=(((AllTime/Counter.toDouble())*100).roundToInt().toDouble()/100).toString()
        }
        ButtonStart.setOnClickListener() {
            ButtonStart.isEnabled = false
            ButtonRignt.isEnabled = true
            ButtonWrong.isEnabled = true
            Time1=System.currentTimeMillis()
            val value = Random()
            RandomResult(value.first,value.second,value.third)
        }
        ButtonRignt.setOnClickListener() {
            if (Calculation()) {
               NumberRight.text=(++NRight).toString()
                AllExamples.text=(NRight+NWrong).toString()
            } else {
                NumberWrong.text=(++NWrong).toString()
                AllExamples.text=(NRight+NWrong).toString()
            }
            PercentageCorrectAnswers.text = ((((NRight.toDouble() / (AllExamples.text.toString().toDouble() / 100.0)) * 100).roundToInt().toDouble() / 100).toString() + "%")
            val value = Random()
            RandomResult(value.first,value.second,value.third)
            Time = ((((System.currentTimeMillis().toDouble()-Time1.toDouble())/1000.0)*100).roundToInt().toDouble()/100)
            GetTime(Time)
            Time=0.0
            Time1=System.currentTimeMillis()
        }
        ButtonWrong.setOnClickListener() {
            if (!Calculation()) {
                NumberRight.text=(++NRight).toString()
                AllExamples.text=(NRight+NWrong).toString()
            } else {
                NumberWrong.text=(++NWrong).toString()
                AllExamples.text=(NRight+NWrong).toString()
            }
            PercentageCorrectAnswers.text = ((((NRight.toDouble() / (AllExamples.text.toString().toDouble() / 100.0)) * 100).roundToInt().toDouble() / 100).toString() + "%")
            val value = Random()
            RandomResult(value.first,value.second,value.third)
            Time = ((((System.currentTimeMillis().toDouble()-Time1.toDouble())/1000.0)*100).roundToInt().toDouble()/100)
            GetTime(Time)
            Time=0.0
            Time1=System.currentTimeMillis()
        }
    }
}