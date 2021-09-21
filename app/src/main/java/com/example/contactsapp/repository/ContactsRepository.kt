package com.example.contactsapp.repository

import androidx.lifecycle.LiveData
import com.example.contactsapp.ContactsApp
import com.example.contactsapp.database.ContactsDatabase
import com.example.contactsapp.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository {
  val database = ContactsDatabase.getDatabase(ContactsApp.appContext)
  
  suspend fun saveContact(contact: Contact) {
    withContext(Dispatchers.IO) {
      database.getContactDao().insertContact(contact)
    }
  }
  
  fun fetchContacts(): LiveData<List<Contact>> {
    return database.getContactDao().getAllContacts()
  }
  
  fun getContactById(contactId: Int): LiveData<Contact>{
    return database.getContactDao().getContactById(contactId)
  }
  
  suspend fun deleteContact(contact: Contact){
    withContext(Dispatchers.IO){
      database.getContactDao().deleteContact(contact)
    }
  }
}