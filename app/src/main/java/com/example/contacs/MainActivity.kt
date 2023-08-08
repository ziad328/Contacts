package com.example.contacs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.contacs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ContactsAdapter
    private var contactsList = mutableListOf<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        onSaveButtonClicked()

    }

    private fun isPhoneNumberValid(phone: String): Boolean {
        return phone.length == 11
    }

    private fun onSaveButtonClicked() {
        binding.btAddContacts.setOnClickListener {
            val name = binding.edName.text.toString().trim()
            val phone = binding.edNumber.text.toString().trim()
            val description = binding.edDescription.text.toString().trim()

            if (!isNameValid(name)) {
                Toast.makeText(this, "Name should be more than 3 characters", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (!isPhoneNumberValid(phone)) {
                Toast.makeText(this, "Phone number should be 11", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            addContactToRV(name, phone, description)
            clearData()
        }
    }
    private fun addContactToRV(name: String, phone: String, description: String) {
        val newContact = Person(name, phone, description)
        contactsList.add(newContact)
        adapter.notifyItemChanged(contactsList.size - 1)
    }
    private fun isNameValid(name: String): Boolean {
        return name.length >= 3
    }
    private fun initRecyclerView() {
        adapter = ContactsAdapter(contactsList)
        binding.rvContacts.adapter = adapter
        adapter.mListener = ContactsAdapter.OnItemClickListener { person ->
            var intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("name", person.name)
            intent.putExtra("phone", person.number)
            intent.putExtra("description", person.description)
            startActivity(intent)
        }

    }
    private fun clearData() {
        binding.edName.text?.clear()
        binding.edNumber.text?.clear()
        binding.edDescription.text?.clear()
    }


}