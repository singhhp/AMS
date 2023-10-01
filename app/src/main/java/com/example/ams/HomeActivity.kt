package com.example.ams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class HomeActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var addSubjectButton: Button
    private lateinit var subjectsList: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        listView = findViewById(R.id.subjectListView)
        addSubjectButton = findViewById(R.id.addSubjectButton)

        subjectsList = mutableListOf()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, subjectsList)
        listView.adapter = adapter


        val subjectsList = mutableListOf(
            Subject("Operating System", 30, 15),
            Subject("Computer Networks", 28, 14),
            Subject("English", 25, 10)
        )

        adapter.notifyDataSetChanged()


        addSubjectButton.setOnClickListener {

            val intent = Intent(this, AddSubjectActivity::class.java)

           startActivityForResult(intent, ADD_SUBJECT_REQUEST_CODE,null)
        }


        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedSubject = subjectsList[position]
            val totalClassesAttended = selectedSubject.classesAttended

            val intent = Intent(this, SubjectDetailsActivity::class.java)
            intent.putExtra("subjectName", selectedSubject.name)
            intent.putExtra("totalClasses", selectedSubject.totalClasses)
            intent.putExtra("classesAttended", totalClassesAttended)
            startActivity(intent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_SUBJECT_REQUEST_CODE && resultCode == RESULT_OK) {
            val subjectName = data?.getStringExtra("subjectName")
            val totalClasses = data?.getIntExtra("totalClasses", 0)

            if (subjectName != null && totalClasses != null && totalClasses > 0) {
                subjectsList.add(subjectName)
                adapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        const val ADD_SUBJECT_REQUEST_CODE = 1
    }
}
