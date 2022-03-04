package com.ber.exam2android

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

class MainActivity : AppCompatActivity(), Navigation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    override fun onItemClicked(id: Long) {
        val infoFragment = InfoFragment()
        val bundle = Bundle()
        bundle.putLong("id", id)
        infoFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, InfoFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, MainFragment())
            .commit()
    }
}