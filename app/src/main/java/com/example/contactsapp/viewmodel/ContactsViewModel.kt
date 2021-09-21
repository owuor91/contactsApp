package com.example.contactsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.model.Contact
import com.example.contactsapp.repository.ContactsRepository
import kotlinx.coroutines.launch

class ContactsViewModel: ViewModel() {
  var contactsRepository = ContactsRepository()
  lateinit var contactsLiveData: LiveData<List<Contact>>
  lateinit var contactLiveData: LiveData<Contact>
  
  fun saveContact(contact: Contact){
    viewModelScope.launch {
      contactsRepository.saveContact(contact)
    }
  }
  
  fun getAllContacts(){
    contactsLiveData = contactsRepository.fetchContacts()
  }
  
  fun getContactById(contactId: Int){
    contactLiveData = contactsRepository.getContactById(contactId)
  }
  
  fun deleteContact(contact: Contact){
    viewModelScope.launch {
      contactsRepository.deleteContact(contact)
    }
  }
}