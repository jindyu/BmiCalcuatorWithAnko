package com.heaven.bmicalcuatorwithanko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.heaven.bmicalcuatorwithanko.databinding.ActivityMainBinding
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadData()

        binding.resultButton.setOnClickListener {
            saveData(binding.heightEditText.text.toString().toInt(), binding.weightEditText.text.toString().toInt())

            startActivity<ResultActivity>(
                "weight" to binding.weightEditText.text.toString(),
                "height" to binding.heightEditText.text.toString()
            )
        }
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if(height != 0 && weight != 0){
            binding.heightEditText.setText(height.toString())
            binding.weightEditText.setText(weight.toString())
        }
    }

    private fun saveData(height: Int, weight: Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
    }
}