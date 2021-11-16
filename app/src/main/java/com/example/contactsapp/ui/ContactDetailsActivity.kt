package com.example.contactsapp.ui

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.contactsapp.databinding.ActivityContactDetailsBinding
import com.example.contactsapp.model.Contact
import com.example.contactsapp.viewmodel.ContactsViewModel
import java.io.File

class ContactDetailsActivity : AppCompatActivity() {
  var contactId = 0
  val contactsViewModel: ContactsViewModel by viewModels()
  lateinit var photoFile: File
  lateinit var myContact: Contact
  lateinit var binding: ActivityContactDetailsBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityContactDetailsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    contactId = intent.getIntExtra("CONTACT_ID", 0)
    contactsViewModel.getContactById(contactId)
  }
  
  override fun onResume() {
    super.onResume()
    contactsViewModel.contactLiveData.observe(this, { contact ->
      binding.tvContactName.text = contact.name
      binding.tvContactEmail.text = contact.email
      binding.tvContactPhoneNumber.text = contact.phoneNumber
      myContact = contact
      if(contact.imageUrl.isNotEmpty()){
        binding.ivContactPhoto.setImageBitmap(BitmapFactory.decodeFile(contact.imageUrl))
      }
    })
    
    binding.ivCamera.setOnClickListener {
      val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
      photoFile = getPhotoFile("photo_${System.currentTimeMillis()}")
      val fileProvider = FileProvider
        .getUriForFile(this, "com.example.contactsapp.provider", photoFile)
      takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
      cameraLauncher.launch(takePictureIntent)
    }
  }
  
  fun getPhotoFile(fileName: String): File {
    var storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(fileName, ".jpg", storageDir)
  }
  
  var cameraLauncher = registerForActivityResult(StartActivityForResult()){result->
    if (result.resultCode == Activity.RESULT_OK){
      val takenPhoto = BitmapFactory.decodeFile(photoFile.absolutePath)
      binding.ivContactPhoto.setImageBitmap(takenPhoto)
      myContact.imageUrl = photoFile.absolutePath
      contactsViewModel.saveContact(myContact)
    }
  }
}
