package com.example.contactsapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.model.Contact
import com.example.contactsapp.ui.ContactDetailsActivity
import com.example.contactsapp.ui.MainActivity.ContactClickListener
import com.squareup.picasso.Picasso

class ContactsAdapter(var contactsList: List<Contact>, var context: Context, var contactClickListener: ContactClickListener):
  RecyclerView.Adapter<ContactsViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
    var itemView = LayoutInflater.from(parent.context)
      .inflate(R.layout.contact_list_item, parent, false)
    return ContactsViewHolder(itemView)
  }
  
  override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
    var currentContact = contactsList.get(position)
    holder.tvName.text = currentContact.name
    holder.tvPhoneNumber.text = currentContact.phoneNumber
    holder.tvEmail.text = currentContact.email
    if (currentContact.imageUrl.isNotEmpty()){
      Picasso.get()
        .load(currentContact.imageUrl)
        .resize(80, 80)
        .centerCrop()
        .into(holder.ivContact)
    }
    
    holder.cvContact.setOnClickListener {
      var intent = Intent(context, ContactDetailsActivity::class.java)
      intent.putExtra("NAME", currentContact.name)
      context.startActivity(intent)
    }
    
    holder.ivContact.setOnClickListener {
      Toast.makeText(context, "You have clicked on my face", Toast.LENGTH_LONG).show()
    }
    
    holder.ivDeleteContact.setOnClickListener {
      contactClickListener.onClickDeleteContact(currentContact)
    }
  }
  
  override fun getItemCount(): Int {
    return contactsList.size
  }
}

class ContactsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
  var tvName = itemView.findViewById<TextView>(R.id.tvName)
  var tvPhoneNumber = itemView.findViewById<TextView>(R.id.tvPhoneNumber)
  var tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
  var ivContact = itemView.findViewById<ImageView>(R.id.ivContact)
  var cvContact = itemView.findViewById<CardView>(R.id.cvContact)
  var ivDeleteContact = itemView.findViewById<ImageView>(R.id.ivDeleteContact)
}