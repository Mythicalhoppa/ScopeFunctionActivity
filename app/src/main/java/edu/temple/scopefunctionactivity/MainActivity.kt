package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("ScopeFunction", getTestDataArray().toString())
        Log.d("ScopeFunction", averageLessThanMedian(listOf(1.0, 8.0, 9.0)).toString())
        Log.d("ScopeFunction", averageLessThanMedian(listOf(1.0, 2.0, 3.0, 10.0)).toString())
        Log.d("ScopeFunction", (getView(0, null, listOf(42), this) as TextView).text.toString())

        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())

    }


    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
    private fun getTestDataArray() : List<Int> = MutableList(10) {
        Random.nextInt()
    }.apply() {
        sort()
    }

    // Return true if average value in list is greater than median value, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean = listOfNumbers.sorted().let{
        sortedList -> val avg = listOfNumbers.average()
        val middle = sortedList.size / 2

        val median = if (sortedList.size % 2 == 0)
            (sortedList[middle] + sortedList[middle - 1]) / 2
        else
            sortedList[middle]

        avg < median
    }

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View = run {
        val textView = if (recycledView != null) {
            recycledView as TextView
        } else {
            TextView(context).apply {
                setPadding(5, 10, 10, 0)
                textSize = 22f
            }
        }

        textView.text = collection[position].toString()
        textView
    }

}