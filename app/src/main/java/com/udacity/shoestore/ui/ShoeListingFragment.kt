package com.udacity.shoestore.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.domain.SharedViewModel


class ShoeListingFragment : Fragment() {
    private var _binding: FragmentShoeListingBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(
                inflater,
                com.udacity.shoestore.R.layout.fragment_shoe_listing,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val supportActionBar = (activity as AppCompatActivity?)!!.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(false)

        }
        binding.fabAddShoe.setOnClickListener {
            findNavController().navigate(com.udacity.shoestore.R.id.action_shoeListingFragment_to_shoeDetailFragment)
            supportActionBar?.apply{
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
            }
        }
        val cards = sharedViewModel.initializeListOfShoes(binding)
        cards.forEach {
            binding.linearLayout.addView(it)
        }

        sharedViewModel.shoeList.observe(viewLifecycleOwner){
            //binding.linearLayout.addView(sharedViewModel.createCardView(it.last(), requireContext()))
        }
    }


}