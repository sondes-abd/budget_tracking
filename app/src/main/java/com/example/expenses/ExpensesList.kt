package com.example.expenses

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class ExpensesList : AppCompatActivity() {
    private lateinit var db: ExpensesDatabaseHelper
    private lateinit var adapter: ExpenseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses_list)
        db = ExpensesDatabaseHelper(this)
        val rec: RecyclerView = findViewById(R.id.list)
        rec.layoutManager = LinearLayoutManager(this)
        adapter = ExpenseAdapter(db.getAll(),this)
        rec.adapter = adapter
        var bb : Button = findViewById(R.id.btn2)
        bb.setOnClickListener {
            var i = Intent(this,AddExpense::class.java)
            startActivity(i)
    }
        val clr: Button = findViewById(R.id.btn4)
        clr.setOnClickListener {
            db.clearExpenses()
            adapter.updateExpenses(emptyList())
            updateTotalExpenses(emptyList())
            Toast.makeText(this,"Total is cleared",Toast.LENGTH_LONG).show()
        }
    }
    override fun onResume() {
        super.onResume()
        val expenses = db.getAll()
        adapter.updateExpenses(expenses)
        updateTotalExpenses(expenses)
    }
    private fun updateTotalExpenses(expenses: List<Expense>) {
        val total = expenses.sumOf { it.amount }
        val totalExpenses: TextView = findViewById(R.id.total)
        totalExpenses.text = "Total: $total"
    }
}