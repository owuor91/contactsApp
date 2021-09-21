package com.example.contactsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.model.Contact
import com.example.contactsapp.ContactsAdapter
import com.example.contactsapp.R.id
import com.example.contactsapp.R.layout
import com.example.contactsapp.viewmodel.ContactsViewModel

class MainActivity : AppCompatActivity() {
  val contactsViewModel: ContactsViewModel by viewModels()
  lateinit var rvContacts: RecyclerView
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
    contactsViewModel.getAllContacts()
  }
  
  override fun onResume() {
    super.onResume()
    contactsViewModel.contactsLiveData.observe(this, {contacts->
      displayContacts(contacts)
    })
  }
  
  fun displayContacts(contactList: List<Contact>){
    rvContacts = findViewById(id.rvContacts)
    rvContacts.layoutManager = LinearLayoutManager(baseContext)
    var contactsAdapter = ContactsAdapter(contactList, baseContext)
    rvContacts.adapter = contactsAdapter
  }
}