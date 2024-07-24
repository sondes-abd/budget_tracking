package com.example.expenses

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddExpense : AppCompatActivity() {
    private lateinit var db: ExpensesDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)
        db=ExpensesDatabaseHelper(this)
        var ad : Button = findViewById(R.id.add)
        var n : EditText =findViewById(R.id.expname)
        var a : EditText =findViewById(R.id.expam)
        ad.setOnClickListener {
            val name = n.text.toString()
            val amount = a.text.toString().toDouble()
            db.insertExpenses(name, amount)
            finish()
            Toast.makeText(this,"Expens Added", Toast.LENGTH_LONG).show()

        }
    }
}