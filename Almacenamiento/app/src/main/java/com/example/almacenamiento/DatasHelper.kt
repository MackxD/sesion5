package com.example.almacenamiento

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DatasHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 1

        //Define el nombre de la tabla y las columnas

        private const val TABLE_NAME = "contacts"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PHONE = "phone"

    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crea la tabla cuando se crea la base de datos
        val createTable =
            "CREATE TABLE $TABLE_NAME" + "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," + "$COLUMN_NAME TEXT ," + "$COLUMN_PHONE TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //Maneja la actualizacion de la base de datos si es necesario
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addContact(name: String, phone: String){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_PHONE, phone)
        }
        db.insert(TABLE_NAME,null,values)
        db.close()
    }


    fun getAllContacts(): List<Contact>{
        val contactlist = mutableListOf<Contact>()
        val db = readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery,null)

        if(cursor.moveToFirst()){
            do{
                val id = cursor?.getColumnIndex(COLUMN_ID)?.let { cursor.getInt(it) }
                val name = cursor?.getColumnIndex(COLUMN_NAME)?.let { cursor.getString(it) }
                val phone =  cursor?.getColumnIndex(COLUMN_PHONE)?.let { cursor.getString(it) }
                val contact = Contact(id!!, name!!, phone!!)
                contactlist.add(contact)

            }while (cursor.moveToNext())
        }
        cursor.close()
        return  contactlist
    }
}

data class Contact(val id: Int, val name: String, val phone: String)
