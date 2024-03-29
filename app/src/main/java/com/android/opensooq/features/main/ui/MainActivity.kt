package com.android.opensooq.features.main.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.opensooq.R
import com.android.opensooq.features.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(HomeFragment())
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager?.backStackEntryCount > 1){
            supportFragmentManager.popBackStack()
        } else {
            val setIntent = Intent(Intent.ACTION_MAIN)
            setIntent.addCategory(Intent.CATEGORY_HOME)
            startActivity(setIntent)
        }
    }
}
