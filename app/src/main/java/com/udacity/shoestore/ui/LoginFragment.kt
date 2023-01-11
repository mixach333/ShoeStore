package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.domain.LoginViewModel

class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel : LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.actionLogin.setOnClickListener{
            if(viewModel.performLogin(binding.inputEmail.text.toString().trim(),
                    binding.inputPassword.text.toString().trim(), it.context)) {
                it.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
            }
        }
        binding.actionRegister.setOnClickListener {
            viewModel.performRegistration(binding.inputEmail.text.toString().trim(),
                binding.inputPassword.text.toString().trim(), it.context
            )
        }
    }
}