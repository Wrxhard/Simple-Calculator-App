package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private  var inputfield: TextView?=null
    var lastdigit=false
    var lastdot=false
    var lastpercent=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputfield=findViewById<TextView>(R.id.calculatestr)

    }
    fun onDigit(view: View)
    {
       inputfield?.append((view as Button).text)
        lastdigit=true
        lastdot=false
    }
    fun onAC(view: View)
    {
        inputfield?.text=""
        lastdigit=false
        lastdot=false
    }
    fun onDecimalPoint(view: View)
    {
        if (lastdigit && !lastdot)
        {
            inputfield?.append((view as Button).text)
            lastdigit=false
            lastdot=true
        }
    }
    fun onOperator(view: View)
    {
        inputfield?.text?.let{
               if ((lastdigit||lastpercent) && !isOperationAdd(it.toString()))
               {
                   inputfield?.append((view as Button).text)
                   lastdigit=false
                   lastdot=false
               }
        }
    }
    fun onPercent(view: View)
    {
        if (lastdigit)
        {
            inputfield?.append((view as Button).text)
            lastdigit=false
            lastdot=false
            lastpercent=true
        }
    }
    fun onEqual(view: View)
    {
        try {
            val inputvalue = inputfield?.text.toString()
            if (lastdigit || lastpercent) {
                if (inputvalue.contains("+")) {
                    val list = inputvalue.split("+")
                    var value1 = list[0]
                    var value2 = list[1]
                    if (value1.contains("%"))
                    {
                        value1=(value1.dropLast(1).toDouble()/100).toString()
                    }
                    if (value2.contains("%"))
                    {
                        value2=(value2.dropLast(1).toDouble()/100).toString()
                    }
                    val res = value1.toDouble() + value2.toDouble()
                    inputfield?.text = res.toString()
                    lastdot=true
                }
                if (inputvalue.contains("-")) {
                    val list = inputvalue.split("-")
                    var value1 = list[0]
                    var value2 = list[1]
                    if (value1.contains("%"))
                    {
                        value1=(value1.dropLast(1).toDouble()/100).toString()
                    }
                    if (value2.contains("%"))
                    {
                        value2=(value2.dropLast(1).toDouble()/100).toString()
                    }
                    val res = value1.toDouble() - value2.toDouble()
                    inputfield?.text = res.toString()
                    lastdot=true
                }
                if (inputvalue.contains("x")) {
                    val list = inputvalue.split("x")
                    var value1 = list[0]
                    var value2 = list[1]
                    if (value1.contains("%"))
                    {
                        value1=(value1.dropLast(1).toDouble()/100).toString()
                    }
                    if (value2.contains("%"))
                    {
                        value2=(value2.dropLast(1).toDouble()/100).toString()
                    }
                    val res = value1.toDouble() * value2.toDouble()
                    inputfield?.text = res.toString()
                    lastdot=true
                }
                if (inputvalue.contains("/")) {
                    val list = inputvalue.split("/")
                    var value1 = list[0]
                    var value2 = list[1]
                    if (value1.contains("%"))
                    {
                        value1=(value1.dropLast(1).toDouble()/100).toString()
                    }
                    if (value2.contains("%"))
                    {
                        value2=(value2.dropLast(1).toDouble()/100).toString()
                    }
                    val res = value1.toDouble() / value2.toDouble()
                    inputfield?.text = res.toString()
                    lastdot=true
                }
            }
        }
        catch (e:ArithmeticException)
        {
            e.printStackTrace()
        }
    }
    fun onBackSpace(view: View)
    {
        var inputvalue=inputfield?.text.toString().dropLast(1)
        inputfield?.text=inputvalue
        if (inputvalue=="")
        {
            lastdigit=false
        }
    }
    private fun isOperationAdd(string: String):Boolean{
       if(
            string.contains("+") ||
                    string.contains("-") ||
                    string.contains("x") ||
                    string.contains("/")){
           return true
       }
        return false
    }


}