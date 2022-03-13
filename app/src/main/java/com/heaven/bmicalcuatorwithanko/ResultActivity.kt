package com.heaven.bmicalcuatorwithanko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heaven.bmicalcuatorwithanko.databinding.ActivityResultBinding
import org.jetbrains.anko.toast

class ResultActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val height = intent.getStringExtra("height")!!.toInt()
        val weight = intent.getStringExtra("weight")!!.toInt()

        val bmi = weight / Math.pow(height / 100.0, 2.0)

        binding.resultTextView.text = when {
            bmi >= 35 -> "고도 비만"
            bmi >= 30 -> "2단계 비만"
            bmi >= 25 -> "1단계 비만"
            bmi >= 23 -> "과체중 비만"
            bmi >= 18.5 -> "정상"
            else -> "저체중"
        }

        binding.imageView.setImageResource(when {
            bmi >= 23 -> R.drawable.ic_baseline_sentiment_very_dissatisfied_24
            bmi >= 18.5 -> R.drawable.ic_baseline_sentiment_satisfied_alt_24
            else -> R.drawable.ic_baseline_sentiment_dissatisfied_24
        })

        toast("$bmi")
    }
}