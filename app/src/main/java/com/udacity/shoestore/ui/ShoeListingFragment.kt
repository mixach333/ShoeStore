package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
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
                R.layout.fragment_shoe_listing,
                container,
                false
            )
        setHasOptionsMenu(true)
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
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
            }
        }


        sharedViewModel.shoeList.observe(viewLifecycleOwner) {
            val cards = sharedViewModel.initializeListOfShoes(binding)
            cards.forEach {
                binding.linearLayout.addView(it)
            }
        }

        sharedViewModel.isUserLoggedIn.observe(viewLifecycleOwner) { loggedIn ->
            if (!loggedIn) findNavController().navigate(R.id.action_shoeListingFragment_to_loginFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.logout -> sharedViewModel.isUserLoggedIn.value = false
//        }
        if (item.itemId == R.id.logout) sharedViewModel.isUserLoggedIn.value = false
        return super.onOptionsItemSelected(item)
    }
}