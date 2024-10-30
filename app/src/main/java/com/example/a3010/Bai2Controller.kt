package com.example.a3010

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Bai2Controller : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b2)


        val studentList = listOf(
            Student("Le Ba Trong", "20215153"),
            Student("Nguyen Trung Hieu", "20211007"),
            Student("Hoang Ngoc Tran", "20212123"),
            Student("Tran Van An", "20212345"),
            Student("Nguyen Thi Linh", "20217890"),
            Student("Le Van Hoang Cuong", "20211234"),
            Student("Pham Van Dung", "20215678"),
            Student("Hoang Thi Anh", "20213456"),
            Student("Tran Van F", "20219876"),
            Student("Nguyen Thi G", "20217654"),
            Student("Le Van H", "20214567"),
            Student("Pham Van I", "20214321"),
            Student("Hoang Thi J", "20216789"),
            Student("Tran Van K", "20213210"),
            Student("Nguyen Thi L", "20215432"),
            Student("Le Van M", "20218765"),
            Student("Pham Van N", "20216543"),
            Student("Hoang Thi O", "20210987"),
            Student("Tran Van P", "20212110"),
            Student("Nguyen Thi Q", "20212321"),
            Student("Le Van R", "20217678"),
            Student("Pham Van S", "20215467"),
            Student("Hoang Thi T", "20216754"),
            Student("Tran Van U", "20214356"),
            Student("Nguyen Thi V", "20219854")
        )

        studentAdapter = StudentAdapter(studentList)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter



        val etSearch = findViewById<EditText>(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val keyword = s.toString().trim()
                if (keyword.length > 2) {
                    val filteredList = studentList.filter {
                        it.name.contains(keyword, ignoreCase = true) || it.mssv.contains(keyword)
                    }
                    studentAdapter.updateList(filteredList)
                } else {
                    studentAdapter.updateList(studentList)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
