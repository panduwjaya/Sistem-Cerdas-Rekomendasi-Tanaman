package com.example.sistemcerdasrekomendasitanaman.ui.primary.other

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.sistemcerdasrekomendasitanaman.R
import com.example.sistemcerdasrekomendasitanaman.databinding.FragmentAppInfoBinding
import com.example.sistemcerdasrekomendasitanaman.databinding.FragmentHelpBinding

class AppInfoFragment : Fragment() {

    private var _binding: FragmentAppInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAppInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClose.setOnClickListener {
            view.findNavController().navigate(R.id.action_appInfoFragment_to_navigation_profile)
            view.findNavController().popBackStack(R.id.appInfoFragment, true)
        }
    }

}