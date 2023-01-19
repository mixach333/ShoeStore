package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.domain.SharedViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()

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
        binding.actionLogin.setOnClickListener {
            if (sharedViewModel.performLogin(
                    binding.inputEmail.text.toString().trim(),
                    binding.inputPassword.text.toString().trim(),
                    it.context
                )
            ) {
                findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
            }
        }
        binding.actionRegister.setOnClickListener {
            sharedViewModel.performRegistration(
                binding.inputEmail.text.toString().trim(),
                binding.inputPassword.text.toString().trim(), it.context
            )
            binding.inputPassword.text.clear()
        }
    }
}