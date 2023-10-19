package com.vsple.productdetailapplication.ui.activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vsple.productdetailapplication.databinding.MainBinding

class MainActivity  : AppCompatActivity() {

    private lateinit var binding: MainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)





    }
}