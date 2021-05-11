package com.sun.moviedb_54.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sun.moviedb_54.R
import com.sun.moviedb_54.screen.homepage.HomePageFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.mainFrame, HomePageFragment.newInstance())
            .commit()
    }
}
