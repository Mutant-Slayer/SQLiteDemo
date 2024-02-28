package com.example.databasehelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDBHelper = MyDBHelper(this)
//        myDBHelper.addContact("John", "1234567890")
//        myDBHelper.addContact("Wick", "1234567890")
//        myDBHelper.addContact("Anas", "1234561240")
//        myDBHelper.addContact("Rahul", "1234743590")

//        myDBHelper.updateContacts(Contact(1, "John", "9576337866"))

//        myDBHelper.deleteContact()

        myDBHelper.deleteContact(2)

        val contacts = myDBHelper.getContacts()
        for (contact in contacts) {
            println(contact)
        }
    }
}