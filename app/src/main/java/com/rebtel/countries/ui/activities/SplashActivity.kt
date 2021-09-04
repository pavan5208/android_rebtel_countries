package com.rebtel.countries.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rebtel.countries.R
import kotlinx.coroutines.*
import java.lang.ref.WeakReference


class SplashActivity : AppCompatActivity() {

    val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupViews()
    }

    fun setupViews() {
        activityScope.launch {
            delay(SPLASH_WAIT_TIME)
            navigateToHome()
        }
    }


    private fun navigateToHome() {
        CountriesListActivity.startActivity(WeakReference(this))
        finish()
    }

    override fun onPause() {
        super.onPause()
        activityScope.cancel()
    }
    companion object{
        const val  SPLASH_WAIT_TIME = 1000L
    }
}