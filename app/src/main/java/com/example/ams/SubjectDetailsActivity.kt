package com.example.ams

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SubjectDetailsActivity : AppCompatActivity() {

    private lateinit var subjectNameTextView: TextView
    private lateinit var totalClassesTextView: TextView
    private lateinit var classesAttendedTextView: TextView
    private lateinit var attendancePercentageTextView: TextView
    private lateinit var presentButton: Button
    private lateinit var absentButton: Button

    private var totalClasses = 0
    private var classesAttended = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_details)

        subjectNameTextView = findViewById(R.id.subjectNameTextView)
        totalClassesTextView = findViewById(R.id.totalClassesTextView)
        classesAttendedTextView = findViewById(R.id.classesAttendedTextView)
        attendancePercentageTextView = findViewById(R.id.attendancePercentageTextView)
        presentButton = findViewById(R.id.presentButton)
        absentButton = findViewById(R.id.absentButton)

        val subjectName = intent.getStringExtra("subjectName")
        totalClasses = intent.getIntExtra("totalClasses", 0)


        subjectNameTextView.text = subjectName
        totalClassesTextView.text = "Total Classes: $totalClasses"


        presentButton.setOnClickListener {
            if (classesAttended < totalClasses) {
                classesAttended++
                updateAttendanceDetails()
            } else {

            }
        }

        absentButton.setOnClickListener {

            if (classesAttended > 0) {
                classesAttended--
                updateAttendanceDetails()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateAttendanceDetails() {

        classesAttendedTextView.text = "Classes Attended: $classesAttended"
        val attendancePercentage = if (totalClasses > 0) {
            (classesAttended.toDouble() / totalClasses.toDouble()) * 100
        } else {
            0.0
        }
        attendancePercentageTextView.text = "Attendance Percentage: %.2f%%".format(attendancePercentage)
    }
}
