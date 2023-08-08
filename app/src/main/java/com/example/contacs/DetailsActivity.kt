package com.example.contacs

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contacs.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActivityContent()
        binding.callImg.setOnClickListener{
            val phoneNum : String? = intent.getStringExtra("phone")
            var dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:"+ phoneNum)
            startActivity(dialIntent)
        }
    }

    private fun setActivityContent() {
        val name: String? = intent.getStringExtra("name")
        val phoneNum: String? = intent.getStringExtra("phone")
        val description: String? = intent.getStringExtra("description")
        binding.nameTv.text = name
        binding.phoneNumTv.text = phoneNum
        binding.descriptionTv.text = description
    }
}