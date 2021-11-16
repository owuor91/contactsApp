package com.example.contactsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactsapp.R
import com.example.contactsapp.model.Contact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken




class CheckoutActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_checkout)
    
    var selectedContactsStr = intent.getStringExtra("SELECTED_CONTACTS")
    var gson = Gson()
    val contactListType = object : TypeToken<List<Contact>>() {}.type
    var contacts: List<Contact> = gson.fromJson(selectedContactsStr, contactListType)
    print(contacts)
  }
}