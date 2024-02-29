package com.example.sendme.core.authentication.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sendme.R
import com.example.sendme.databinding.FragmentSignUpBinding

class SingUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var loginCallBack: LoginCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoginCallback) {
            loginCallBack = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        binding.toolbar.setOnClickListener {
            loginCallBack.onLoginClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}