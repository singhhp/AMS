package com.example.ams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddSubjectActivity : AppCompatActivity() {
    private lateinit var subjectNameEditText: EditText
    private lateinit var totalClassesEditText: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subject)

        subjectNameEditText = findViewById(R.id.subjectNameEditText)
        totalClassesEditText = findViewById(R.id.totalClassesEditText)
        addButton = findViewById(R.id.addButton)

        addButton.setOnClickListener {
            val subjectName = subjectNameEditText.text.toString()
            val totalClasses = totalClassesEditText.text.toString().toIntOrNull() ?: 0

            if (subjectName.isNotBlank() && totalClasses > 0) {
                
                val intent = Intent()
                intent.putExtra("subjectName", subjectName)
                intent.putExtra("totalClasses", totalClasses)


                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid input.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
