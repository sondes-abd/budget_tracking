package com.example.expenses

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class ExpenseAdapter(private var expenses: List<Expense>,context: Context) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {
    private val db: ExpensesDatabaseHelper = ExpensesDatabaseHelper(context)

    class ExpenseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val expenseName: TextView = itemView.findViewById(R.id.expname)
        val expenseAmount: TextView = itemView.findViewById(R.id.expam)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseAdapter.ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        holder.expenseName.text = expense.name
        holder.expenseAmount.text = expense.amount.toString()
    }

    override fun getItemCount(): Int = expenses.size

    fun updateExpenses(newExpenses: List<Expense>) {
        expenses = newExpenses
        notifyDataSetChanged()

    }

}