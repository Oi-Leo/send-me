package com.example.sendme.core.inbox.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.sendme.R
import com.example.sendme.databinding.ActivityInboxBinding

class InboxActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInboxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInboxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Chats"
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.inbox_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_action -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}