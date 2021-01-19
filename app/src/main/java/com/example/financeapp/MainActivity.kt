package com.example.financeapp

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.mikephil.charting.charts.PieChart
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.coroutines.CoroutineContext
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

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
        getDrawableId()
        getColorId()
        setUpPieChartData()
        setOnClick(profile,addOperation)
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
    ///sssss
    fun setOnClick(profile: View ,addOperation: View) {
        profile.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        addOperation.setOnClickListener {
            startActivity(Intent(this, CategoryActivity::class.java))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setUpPieChartData() {
        val valuesAndColors = getCategoryValuesAndColors()
        val yVals = valuesAndColors.first

        val dataSet = PieDataSet(yVals, "")
        dataSet.valueTextSize=0f
        val colors = valuesAndColors.second
//        colors.clear()
//        colors.add(Color.RED)
//        colors.add(Color.GREEN)
//        colors.add(Color.MAGENTA)
//        colors.add(getColor(R.color.color_clothes_category))
//        colors.add(getColor(R.color.color_car_category))

        val pieChart = findViewById<PieChart>(R.id.chart1)
        dataSet.setColors(colors)

        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.centerTextRadiusPercent = 0f
        pieChart.isDrawHoleEnabled = true
        pieChart.legend.isEnabled = false
        pieChart.description.isEnabled = false
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getCategoryValuesAndColors():Pair<ArrayList<PieEntry>, ArrayList<Int>> {
        val yVals = ArrayList<PieEntry>()
        val colors = ArrayList<Int>()
        val operationList: HashMap<String, Int> = HashMap()
        for (operation in OperationList){
            if (operationList.keys.contains(operation["category"]))
                operationList[operation["category"].toString()] = operationList[operation["category"].toString()]!!.plus(
                    operation["operation_sum"].toString().toInt()
                )
            else
                operationList[operation["category"].toString()] = operation["operation_sum"].toString().toInt()
        }
        for (operation in operationList) {
            yVals.add(PieEntry(operation.value.toFloat()))
            colors.add(getColor(colorMap[operation.key]!!))
//            colors.add(colorMap.values.random())
        }
        return Pair(yVals, colors)
    }

}
