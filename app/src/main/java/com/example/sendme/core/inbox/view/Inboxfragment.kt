package com.example.sendme.core.inbox.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.example.sendme.R
import com.example.sendme.databinding.FragmentInboxBinding

class InboxFragment : Fragment(), MenuProvider {

    private lateinit var toolbarCallback: ToolbarCallback
    private var _binding: FragmentInboxBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInboxBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.title = "Chats"
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_person_black_24)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ToolbarCallback) {
            toolbarCallback = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inboxAdapter = InboxAdapter()
        binding.rvInbox.adapter = inboxAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        val menuHost: MenuHost = requireActivity()
        menuHost.removeMenuProvider(this)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.inbox_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            android.R.id.home -> {
                toolbarCallback.goToProfile()
                true
            }

            R.id.new_action -> {
                toolbarCallback.goToNewMessage()
                true
            }

            else -> false
        }
    }
}