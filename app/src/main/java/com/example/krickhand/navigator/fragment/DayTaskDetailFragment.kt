package com.example.krickhand.navigator.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krickhand.navigator.adapter.DayTaskListAdapter
import com.example.krickhand.navigator.adapter.TagListAdapter
import com.example.krickhand.navigator.databinding.FragmentDaytaskDetailBinding
import com.example.krickhand.navigator.entity.Tag
import com.example.krickhand.navigator.viewmodel.DayViewModel

class DayTaskDetailFragment : Fragment() {

    private var _binding: FragmentDaytaskDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: TagListAdapter
    private val vm: DayViewModel by activityViewModels { DayViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaytaskDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            dayViewModel = vm
            lifecycleOwner = viewLifecycleOwner
            adapter = TagListAdapter(vm)
            tagRecyclerView.adapter = adapter
            tagRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            dtaskNewFAB.setOnClickListener {
                addDaytask(it)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun addDaytask(view: View) {
        val action = DayTaskDetailFragmentDirections.navFromDaytaskAdd()
        view.findNavController().navigate(action)
    }
}