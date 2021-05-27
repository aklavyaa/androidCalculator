package com.tmsfalcon.device.calculatorbasic

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var textExp: EditText
    lateinit var textResult: TextView

    var strExp = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textExp = findViewById<EditText>(R.id.text_exp)
        textResult = findViewById<TextView>(R.id.text_result)


    }

    fun setExpression(str: String) {
        strExp.append(str)
        if (str.equals("*", ignoreCase = true)
            || str.equals("/", ignoreCase = true)
            || str.equals("+", ignoreCase = true)
            || str.equals("-", ignoreCase = true)
            || str.equals("%", ignoreCase = true)

        ) {
            var a: CharArray = noTwoArithemetterComes(strExp.toString(), str)
            strExp.clear()
            strExp.append(a)
        }
        textExp.setText(strExp.toString())
    }

    fun plusMinusClick(view: View) {
        plusMinusOp()
    }

    fun cClick(view: View) {
        strExp.clear()
        setExpression("")
        textResult.text = ""
    }

    fun bracketClick(view: View) {putBrackets()}
    fun percentageClick(view: View) {
    }

    fun divisionClick(view: View) {
        setExpression("/")
    }

    fun sevenClick(view: View) {
        setExpression("7")
    }

    fun eightClick(view: View) {
        setExpression("8")
    }

    fun nineClick(view: View) {
        setExpression("9")
    }

    fun multiplyClick(view: View) {
        setExpression("*")
    }

    fun fourClick(view: View) {
        setExpression("4")
    }

    fun fiveClick(view: View) {
        setExpression("5")
    }

    fun sixClick(view: View) {
        setExpression("6")
    }

    fun minusClick(view: View) {
        setExpression("-")
    }

    fun oneClick(view: View) {
        setExpression("1")
    }

    fun twoClick(view: View) {
        setExpression("2")
    }

    fun threeClick(view: View) {
        setExpression("3")
    }

    fun plusClick(view: View) {
        setExpression("+")
    }

    fun zeroClick(view: View) {
        setExpression("0")
    }

    fun dotClick(view: View) {
        setExpression(".")
    }

    fun equalsClick(view: View) {
        if (checkBracketCloseInExp()) {
            var result = Evaluation().eval(strExp.toString())
            textResult.text = result.toString()
        }else {
            Toast.makeText(this@MainActivity,"Brackets are opened",Toast.LENGTH_SHORT).show()
        }
    }

    var specialCharList = arrayOf('*', '/', '+', '-', '%')

    fun noTwoArithemetterComes(str: String, charStr: String): CharArray {
        var charArr = str.toCharArray()

        charArr.forEachIndexed { index, c ->
                if (specialCharList.contains(c)) {
                    if (index + 1 == charArr.size) {
                    return charArr
                }


                if (charArr[index + 1].equals(charStr.toCharArray()[0], ignoreCase = true)) {


                    var result = charArr.toMutableList()
                    result.removeAt(index)
                    return result.toCharArray()
                } else if (specialCharList.contains(charArr[index + 1])) {
                    var result = charArr.toMutableList()
                    result.removeAt(index+1)
                    return result.toCharArray()

                }
            }
        }

        return charArr
    }

    fun clear(view: View) {
        strExp.deleteAt(strExp.length-1)
        textExp.setText(strExp.toString())
    }

    var toggleAddSub = "-"

    fun plusMinusOp(){
        toggleAddSub = if (toggleAddSub.equals("-")) "+" else "-"
        for (i in strExp.withIndex().reversed()){
            if (specialCharList.contains(i.value)){
                strExp.replace(i.index,i.index+1,toggleAddSub)
                textExp.setText(strExp.toString())
                return
            }
        }
    }

    var toggleBrackets = ")"

    var listBrackets = mutableListOf<String>()

    fun putBrackets(){
        toggleBrackets = if (toggleBrackets.equals(")")) "(" else ")"
        listBrackets.add(toggleBrackets)
        setExpression(toggleBrackets)
    }

    fun checkBracketCloseInExp():Boolean{
        var noOfOpenBrack = 0
        var noOfCloseBrack = 0

        listBrackets.forEach {
            if (it.equals("(")){
                noOfOpenBrack++
            }else {
                noOfCloseBrack++
            }
        }
        return noOfCloseBrack == noOfOpenBrack
    }

}