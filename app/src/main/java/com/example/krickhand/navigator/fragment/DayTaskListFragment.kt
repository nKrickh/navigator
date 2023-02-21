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
import com.example.krickhand.navigator.databinding.FragmentDaytaskListBinding
import com.example.krickhand.navigator.viewmodel.DayViewModel

class DayTaskListFragment : Fragment() {

    private var _binding: FragmentDaytaskListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: DayTaskListAdapter
    private val vm: DayViewModel by activityViewModels { DayViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaytaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            dayViewModel = vm
            lifecycleOwner = viewLifecycleOwner
            adapter = DayTaskListAdapter(vm)
            dtRecyclerView.adapter = adapter
            dtRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            dtaskNewFAB.setOnClickListener {
                addDaytask(it)
            }
        }
    }

    fun addDaytask(view: View) {
        val action = DayTaskListFragmentDirections.navFromDaylistToAdd()
        view.findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}