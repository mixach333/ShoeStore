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
        //TODO in such cases 'with()' kotlin scope function could be useful in order to:
        // - make code cleaner/ more readable
        // - one logic in one place
        // - easier migration from kotlin synthetics. Imagine you have a lot of kotlin synthetics
        // titleTextView, subtitleTextView, bodyTextView etc. so with bindings probably you will add
        // some binding variable everywhere binding.titleTextView, binding.subtitleTextView, binding.bodyTextView etc.
        // but you can easily simplify that code just by wrapping all bindings using with() scope function
        // with(binding) {
        //  titleTextView.text = ...
        //  subtitleTextView.text = ...
        //  bodyTextView.text = ...
        // }

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
    //TODO I've noticed you are cleaning up your binding variable in WelcomeFragment#onDestroyView, so keep the same logic everywhere
}