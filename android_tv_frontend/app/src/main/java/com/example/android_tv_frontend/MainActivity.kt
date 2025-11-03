package com.example.android_tv_frontend

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.example.android_tv_frontend.ui.browse.HomeFragment

/**
 * PUBLIC_INTERFACE
 * Root activity that hosts all TV fragments.
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load home screen on first launch
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, HomeFragment())
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Delegate BACK key to fragment manager stack
        return when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                } else {
                    finish()
                }
                true
            }

            else -> super.onKeyDown(keyCode, event)
        }
    }
}
