package com.example.krickhand.navigator.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.krickhand.navigator.databinding.FragmentDaytaskDetailBinding
import com.example.krickhand.navigator.viewmodel.DayViewModel

class DayTaskDetailFragment : Fragment() {

    private var _binding: FragmentDaytaskDetailBinding? = null

    // This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!
    private val viewmodel: DayViewModel by activityViewModels { DayViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaytaskDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            dayViewModel = viewmodel
            lifecycleOwner = viewLifecycleOwner
        }
    }
}