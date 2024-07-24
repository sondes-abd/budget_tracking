package com.example.expenses
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

 class ExpensesDatabaseHelper (context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        companion object {
            private const val DATABASE_NAME = "expenses.db"
            private const val DATABASE_VERSION = 1
            private const val TABLE_NAME = "expenses"
            private const val COLUMN_ID = "id"
            private const val COLUMN_NAME = "name"
            private const val COLUMN_AMOUNT = "amount"
        }
     override fun onCreate(db: SQLiteDatabase?) {
         val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_AMOUNT REAL)"
         db?.execSQL(createTableQuery)
     }
     override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
         db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
         onCreate(db)
     }
     fun insertExpenses(name: String, amount: Double) : Long {
         val db = this.writableDatabase
         val values = ContentValues().apply {
             put(COLUMN_NAME, name)
             put(COLUMN_AMOUNT, amount)

         }
         val result = db.insert(TABLE_NAME, null, values)
         db.close()  // Ensure database is closed after insertion
         return result
     }
     fun getAll(): List<Expense> {
         val expenses = mutableListOf<Expense>()
         val db = this.readableDatabase
         val selectQuery = "SELECT * FROM $TABLE_NAME"
         val cursor = db.rawQuery(selectQuery, null)
         if (cursor.moveToFirst()) {
         do {
             val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
             val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
             var amount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT))
             val expense = Expense(id, name, amount)
             expenses.add(expense)
         }while (cursor.moveToNext())
         }
         return expenses}

         fun clearExpenses() {
             val db = this.writableDatabase
             db.execSQL("DELETE FROM expenses")
             db.close()
         }
}