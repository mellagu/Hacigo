package com.mellagusty.hacigo_mobileapp.intro

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.adapter.IntroSliderAdapter
import com.mellagusty.hacigo_mobileapp.databinding.ActivityIntroSlideBinding
import com.mellagusty.hacigo_mobileapp.ui.MainActivity

class IntroSlideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroSlideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroSlideBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.introSliderViewPager2.adapter = introSliderAdapter

        //calling function
        setupIndicators()
        setCurrentIndicator(0)
        binding.introSliderViewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        binding.btnNext.setOnClickListener {
            if (binding.introSliderViewPager2.currentItem + 1 < introSliderAdapter.itemCount) {
                binding.introSliderViewPager2.currentItem += 1
            } else {
                Intent(applicationContext, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        binding.tvSkipIntro.setOnClickListener {
            Intent(applicationContext, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlideEntity(
                "Learner",
                "Siap menjadi Ibu pembelajar setiap hari",
                R.drawable.boarding_1
            ),
            IntroSlideEntity(
                "Watcher",
                "Pantau tumbuh kembang anak lebih menyenangkan",
                R.drawable.boarding_2
            ),
            IntroSlideEntity(
                "Giver",
                "Berikan nutrisi dan pengajaran terbaik",
                R.drawable.boarding_3
            )
        )
    )

    private fun setCurrentIndicator(index: Int) {
        val childCount = binding.indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView2 = binding.indicatorsContainer[i] as ImageView
            if (i == index) {
                imageView2.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView2.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }

    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorsContainer.addView(indicators[i])
        }
    }
}