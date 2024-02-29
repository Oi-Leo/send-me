package com.example.sendme.core.authentication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.sendme.R

interface LoginCallback {
    fun onCreateAccountClicked()
    fun onLoginClicked()
}

class LoginActivity : AppCompatActivity(), LoginCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onCreateAccountClicked() {
        val fragment = SingUpFragment()
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, fragment)
            addToBackStack(null)
        }
    }

    override fun onLoginClicked() {
        supportFragmentManager.popBackStack()
    }
}