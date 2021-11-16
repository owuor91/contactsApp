package com.example.contactsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.model.Contact
import com.example.contactsapp.ContactsAdapter
import com.example.contactsapp.R.id
import com.example.contactsapp.R.layout
import com.example.contactsapp.databinding.ActivityMainBinding
import com.example.contactsapp.viewmodel.ContactsViewModel
import com.google.gson.Gson
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
  val contactsViewModel: ContactsViewModel by viewModels()
  lateinit var binding: ActivityMainBinding
  
  var selectedContacts = mutableListOf<Contact>()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    contactsViewModel.getAllContacts()
  }
  
  override fun onResume() {
    super.onResume()
    contactsViewModel.contactsLiveData.observe(this, {contacts->
      displayContacts(contacts)
    })
    
    binding.fabAddCoontact.setOnClickListener {
      startActivity(Intent(this, AddContactActivity::class.java))
    }
    
    binding.btnCheckout.setOnClickListener {
      var intent = Intent(this, CheckoutActivity::class.java)
      var gson = Gson()
      intent.putExtra("SELECTED_CONTACTS", gson.toJson(selectedContacts))
      startActivity(intent)
    }
  }
  
  fun displayContacts(contactList: List<Contact>){
    binding.rvContacts.layoutManager = LinearLayoutManager(baseContext)
    var contactsAdapter = ContactsAdapter(contactList, baseContext, object : ContactClickListener {
      override fun onClickDeleteContact(contact: Contact) {
        contactsViewModel.deleteContact(contact)
      }
  
      override fun onSelectContact(contact: Contact) {
        selectedContacts.add(contact)
      }
    })
    binding.rvContacts.adapter = contactsAdapter
  }
  
  interface ContactClickListener{
    fun onClickDeleteContact(contact: Contact)
    
    fun onSelectContact(contact: Contact)
  }
}