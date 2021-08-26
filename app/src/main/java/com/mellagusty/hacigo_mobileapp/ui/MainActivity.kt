package com.mellagusty.hacigo_mobileapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.local.journal.KiddoJournalEntity
import com.mellagusty.hacigo_mobileapp.databinding.ActivityMainBinding
import com.mellagusty.hacigo_mobileapp.ui._kiddojournal.CreateJournalViewModel
import com.mellagusty.hacigo_mobileapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CreateJournalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.MyToolbar)
        supportActionBar?.hide()
//        val bar = binding.ToolbarImage
//        //Glide Toolbar's photo
//        val photo = R.drawable.hacigo_launcher
//        Glide.with(this)
//            .load(photo)
//            .into(bar)

        getKiddoInfo()



        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_parenthood,
                R.id.navigation_recipe,
                R.id.navigation_account
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    private fun getKiddoInfo() {
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(CreateJournalViewModel::class.java)

//      yang akan ditampilkan : bb terakhir, tb terakhir, keterangan sudah ideal atau belum, rekomendasi berat ideal

        CoroutineScope(Dispatchers.IO).launch {
            val kiddoInfo = viewModel.getLastJournal()
            if ( kiddoInfo != null ) {
                Log.d("kiddo", kiddoInfo.toString() )

                val resultIdeal: ArrayList<String>
                resultIdeal = dataIdeal(kiddoInfo)
                Log.d( "kiddo", "hasilnya adalah $resultIdeal" )
            }
            else {
                Log.d( "kiddo", "belum ada data, silahkan input data" )
            }
        }
    }

    private fun dataIdeal( kiddoInfo: KiddoJournalEntity): ArrayList<String> {
        var resultIdeal = arrayListOf<String>()
        val gender: String? = "P"
//        var gender: String? = kiddoInfo.gender
        val tb: Int? = kiddoInfo.height?.toInt()
        val bb: Int? = kiddoInfo.weight?.toInt()
        val usia: Int? = kiddoInfo.ageInMonth?.toInt()

        var idealStatus: String? = "belum ideal"
        var rentang: String? = "rentang"

        var idx: Int? = 0
        var tbIdealMin: Double? = 0.0
        var tbIdealMax: Double? = 0.0
        var bbIdealMin: Double? = 0.0
        var bbIdealMax: Double? = 0.0

        val dataIdealP: Array<DoubleArray> = arrayOf(
            doubleArrayOf( 0.0, 2.3, 4.2, 45.6, 52.6 ),
            doubleArrayOf( 1.0, 3.2, 5.4, 50.0, 57.2 ),
            doubleArrayOf( 2.0, 4.0, 6.4, 53.2, 60.8 ),
            doubleArrayOf( 3.0, 4.5, 7.4, 55.8, 63.7 ),
            doubleArrayOf( 4.0, 5.1, 8.0, 57.9, 66.2 ),
            doubleArrayOf( 5.0, 5.4, 8.6, 59.9, 68.3 ),
            doubleArrayOf( 6.0, 5.6, 9.2, 61.3, 69.8 ),
            doubleArrayOf( 7.0, 6.1, 9.6, 62.8, 71.5 ),
            doubleArrayOf( 8.0, 6.2, 10.0, 64.3, 73.1 ),
            doubleArrayOf( 9.0, 6.5, 10.4, 65.5, 74.5 ),
            doubleArrayOf( 10.0, 6.7, 10.6, 66.7, 76.0 ),
            doubleArrayOf( 11.0, 7.0, 11.0, 68.0, 77.3 ),
            doubleArrayOf( 12.0, 7.1, 11.3, 69.2, 79.0 ),
            doubleArrayOf( 13.0, 7.0, 11.5, 68.9, 79.2 ),
            doubleArrayOf( 14.0, 9.0, 14.8, 80.0, 92.9 ),
            doubleArrayOf( 15.0, 10.0, 18.1, 87.4, 101.7 ),
            doubleArrayOf( 16.0, 12.3, 21.5, 94.1, 111.3 ),
            doubleArrayOf( 17.0, 13.7, 24.9, 99.9, 118.9 )
        )

        val dataIdealL: Array<DoubleArray> = arrayOf(
            doubleArrayOf( 0.0, 2.4, 4.2, 46.2, 53.3 ),
            doubleArrayOf( 1.0, 3.4, 5.6, 51.0, 58.4 ),
            doubleArrayOf( 2.0, 4.3, 7.0, 54.5, 62.1 ),
            doubleArrayOf( 3.0, 5.1, 7.8, 57.5, 65.3 ),
            doubleArrayOf( 4.0, 5.5, 8.5, 59.9, 67.9 ),
            doubleArrayOf( 5.0, 6.1, 9.2, 61.7, 69.7 ),
            doubleArrayOf( 6.0, 6.3, 9.6, 63.5, 71.5 ),
            doubleArrayOf( 7.0, 6.7, 10.2, 65.0, 73.1 ),
            doubleArrayOf( 8.0, 7.0, 10.4, 66.4, 74.6 ),
            doubleArrayOf( 9.0, 7.3, 10.8, 67.7, 76.0 ),
            doubleArrayOf( 10.0, 7.4, 11.2, 68.9, 77.5 ),
            doubleArrayOf( 11.0, 7.5, 11.4, 70.1, 78.8 ),
            doubleArrayOf( 12.0, 7.8, 11.8, 71.2, 80.0 ),
            doubleArrayOf( 13.0, 7.7, 12.0, 71.0, 80.5 ),
            doubleArrayOf( 14.0, 9.7, 15.3, 81.7, 93.9 ),
            doubleArrayOf( 15.0, 11.3, 18.3, 88.7, 103.5 ),
            doubleArrayOf( 16.0, 12.7, 21.2, 94.9, 111.7 ),
            doubleArrayOf( 17.0, 14.1, 24.2, 100.7, 119.2 )
        )

        if ( usia!! <= 12 ) {
            idx = usia
        }
        else if ( usia!! >= 13 && usia!! <= 24 ) { // usia >1 tahun
            idx = 13
        }
        else if ( usia!! >= 25 && usia!! <= 36 ) { // usia >2 tahun
            idx = 14
        }
        else if ( usia!! >= 37 && usia!! <= 48 ) { // usia >3 tahun
            idx = 15
        }
        else if ( usia!! >= 49 && usia!! <= 59 ){ // usia >4 tahun
            idx = 16
        }
        else { // usia >5 tahun
            idx = 17
        }

        if ( gender!! == "P" ) {
            bbIdealMin = dataIdealP[idx][1]
            bbIdealMax = dataIdealP[idx][2]
            tbIdealMin = dataIdealP[idx][3]
            tbIdealMax = dataIdealP[idx][4]
        }
        else {
            bbIdealMin = dataIdealL[idx][1]
            bbIdealMax = dataIdealL[idx][2]
            tbIdealMin = dataIdealL[idx][3]
            tbIdealMax = dataIdealL[idx][4]
        }

        rentang = bbIdealMin.toString() + " - " + bbIdealMax.toString()
        if ( tb!!.toDouble() >= tbIdealMin && tb!!.toDouble() <= tbIdealMax ) {
            if ( bb!!.toDouble() >= bbIdealMin && bb!!.toDouble() <= bbIdealMax ) {
                idealStatus = "sudah ideal"
                rentang = "pertahankan karena sudah ideal"
            }
        }

        idealStatus?.let { resultIdeal.add(it) }
        rentang?. let { resultIdeal.add(it) }

        return resultIdeal
    }
}