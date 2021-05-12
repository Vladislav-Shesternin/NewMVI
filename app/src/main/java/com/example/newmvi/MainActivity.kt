package com.example.newmvi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newmvi.databinding.ActivityMainBinding
import com.example.newmvi.navigation.BaseRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var router: BaseRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        router.attach(this)
    }

}