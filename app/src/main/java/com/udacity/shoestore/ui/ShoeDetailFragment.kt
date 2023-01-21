package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.domain.SharedViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {
    private var _binding: FragmentShoeDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newShoe = Shoe()
        binding.shoe = newShoe
        binding.cancelButton.setOnClickListener {
            navigateBackToListing()
        }
        binding.confirmButton.setOnClickListener {
            if ((viewModel.addShoe(newShoe))) {
                navigateBackToListing()
            } else {
                Toast.makeText(
                    context,
                    "Some properties of the new shoe are incorrect or blank",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun navigateBackToListing() {
        findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListingFragment)
    }
    //TODO I've noticed you are cleaning up your binding variable in WelcomeFragment#onDestroyView, so keep the same logic everywhere
}