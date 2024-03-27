package com.example.sendme.core.inbox.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.commit
import com.example.sendme.R
import com.example.sendme.core.newmessage.view.NewMessageFragment
import com.example.sendme.core.profile.view.ProfileActivity
import com.example.sendme.databinding.ActivityInboxBinding

interface ToolbarCallback {
    fun goToNewMessage()

    fun goToProfile()
}

class InboxActivity : AppCompatActivity(), ToolbarCallback {

    private lateinit var binding: ActivityInboxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInboxBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun goToNewMessage() {
        val fragment = NewMessageFragment()
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(R.id.fragment_container_view, fragment)
            addToBackStack(null)
        }
    }

    override fun goToProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
}