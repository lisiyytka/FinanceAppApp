package com.example.financeapp

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import de.hdodenhof.circleimageview.CircleImageView


class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    @SuppressLint("ClickableViewAccessibility")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val profile = findViewById<CircleImageView>(R.id.profile)
        val listView: ListView = findViewById(R.id.operations_list)
        val addOperation = findViewById<Button>(R.id.add_btn)
        setOnClick(profile,addOperation)

        val arrayList: ArrayList<HashMap<String, String>> = ArrayList()
        var map: HashMap<String, String>
        //fgh
        map = HashMap()
        map["date"] = "07.06.2020"
        map["category"] = "Food"
        map["operation_sum"] = "-220"
        map["comment"] = "-220"
        arrayList.add(map)

        map = HashMap()
        map["date"] = "08.06.2020"
        map["category"] = "Food"
        map["operation_sum"] = "-220"
        map["comment"] = "-220"
        arrayList.add(map)
        //allthesmallthings
        map = HashMap()
        map["date"] = "08.06.2020"
        map["category"] = "Food"
        map["operation_sum"] = "-220"
        map["comment"] = "-220"
        arrayList.add(map)

        map = HashMap()
        map["date"] = "08.06.2020"
        map["category"] = "Food"
        map["operation_sum"] = "-220"
        map["comment"] = "-220"
        arrayList.add(map)

        map = HashMap()
        map["date"] = "08.06.2020"
        map["category"] = "Food"
        map["operation_sum"] = "-220"
        map["comment"] = "-220"
        arrayList.add(map)

        map = HashMap()
        map["date"] = "08.06.2020"
        map["category"] = "Food"
        map["operation_sum"] = "-220"
        map["comment"] = "-220"
        arrayList.add(map)

        map = HashMap()
        map["date"] = "08.06.2020"
        map["category"] = "Food"
        map["operation_sum"] = "-220"
        map["comment"] = "-220"
        arrayList.add(map)
        val adapter = SimpleAdapter(
            this,
            arrayList,
            R.layout.fragment_operations_list,
            arrayOf("date", "category", "operation_sum", "comment"),
            intArrayOf(R.id.date, R.id.category, R.id.operation_sum, R.id.comment)
        )
        listView.adapter = adapter
    }

    fun setOnClick(profile: View ,addOperation: View){
        profile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        addOperation.setOnClickListener {
            startActivity(Intent(this, CategoryActivity::class.java))
        }
    }
}