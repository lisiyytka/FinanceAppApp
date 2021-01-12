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
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.coroutines.CoroutineContext


//import com.github.mikephil.charting.charts.PieChart
//import com.github.mikephil.charting.data.PieData
//import com.github.mikephil.charting.data.PieDataSet
//import com.github.mikephil.charting.utils.ColorTemplate
//import kotlinx.android.synthetic.main.fragment_diagram_circle.*

var OperationList: java.util.ArrayList<HashMap<String, Any>> = ArrayList()
class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    @SuppressLint("ClickableViewAccessibility")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFirebase()
        val profile = findViewById<CircleImageView>(R.id.profile)
        val listView: ListView = findViewById(R.id.operations_list)
        val addOperation = findViewById<CircleImageView>(R.id.add_btn)
        val income = findViewById<TextView>(R.id.income_sum)
        val loss = findViewById<TextView>(R.id.expenses_sum)
        setOnClick(profile,addOperation)
        getDrawableId()
        val prov = findViewById<TextView>(R.id.budget)
        val addd = LocalDataBaseHandler(this)
        val user = addd.getUser()
        prov.text = user.balance+ "\n" +"руб"
        getIncomeAndLosses(user, income, loss)
        val adapter = SimpleAdapter(
            this,
            OperationList,
            R.layout.fragment_operations_list,
            arrayOf("date", "category", "operation_sum", "comment", "image"),
            intArrayOf(R.id.date, R.id.category, R.id.operation_sum, R.id.comment, R.id.img)
        )
        listView.adapter = adapter
    }

    fun setOnClick(profile: View ,addOperation: View) {
        profile.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        addOperation.setOnClickListener {
            startActivity(Intent(this, CategoryActivity::class.java))
        }
    }

}
