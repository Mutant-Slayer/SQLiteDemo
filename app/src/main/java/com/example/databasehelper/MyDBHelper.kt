package com.example.databasehelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DATABASE_NAME = "ContactsDb"
private const val DATABASE_VERSION = 1
private const val TABLE_NAME = "Contacts"
private const val ID = "id"
private const val NAME = "name"
private const val PHONE_NUMBER = "phone_number"

class MyDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME TEXT, $PHONE_NUMBER TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addContact(name: String, phoneNumber: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, name)
        values.put(PHONE_NUMBER, phoneNumber)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getContacts(): ArrayList<Contact> {
        val list = ArrayList<Contact>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val phoneNumber = cursor.getString(2)
            list.add(Contact(id, name, phoneNumber))
        }
        cursor.close()
        return list
    }

    fun updateContacts(contact: Contact) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(PHONE_NUMBER, contact.phoneNumber)
        db.update(TABLE_NAME, values, "$ID=${contact.id}", null)
    }

    fun deleteContact(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$ID = ?", arrayOf(id.toString()))
    }
}