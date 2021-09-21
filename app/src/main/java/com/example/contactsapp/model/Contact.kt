package com.example.contactsapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacts")
data class Contact(
  @PrimaryKey(autoGenerate = true) @NonNull var contactId: Int,
  var name: String,
  var phoneNumber: String,
  var email: String,
  var imageUrl: String
)
