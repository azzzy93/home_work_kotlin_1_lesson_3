package kg.geektech.home_work_kotlin_2_lesson_3.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kg.geektech.home_work_kotlin_2_lesson_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}