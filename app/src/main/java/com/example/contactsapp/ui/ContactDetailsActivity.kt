package com.example.contactsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactsapp.R.layout

class ContactDetailsActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_contact_details)
    var name = intent.getStringExtra("NAME")
    Toast.makeText(baseContext, name, Toast.LENGTH_LONG).show()
  }
}