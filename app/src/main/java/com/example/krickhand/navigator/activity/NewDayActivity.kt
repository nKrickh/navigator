package com.example.krickhand.navigator.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.krickhand.navigator.databinding.ActivityNewDayBinding

class NewDayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewDayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewDayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val editDayView = binding.editDay
        val button = binding.buttonSave
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editDayView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val day = editDayView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, day)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "DAY_TEST_REPLY"
    }
}


