package com.mellagusty.hacigo_mobileapp.ui.home.util

import android.content.Context
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis

annotation class Inject
class SparkLineStyleInject @Inject constructor(private val context: Context){

    fun styleChart(lineChart: LineChart) = lineChart.apply {
        axisRight.isEnabled = false

        axisLeft.apply {
            isEnabled = false
            axisMinimum = 0f
            axisMaximum = 20f
        }

        xAxis.apply {
            axisMinimum = 0f
            axisMaximum = 24f
            isGranularityEnabled = true
            granularity = 4f
            setDrawGridLines(true)
            position = XAxis.XAxisPosition.BOTTOM
        }

    }

}


