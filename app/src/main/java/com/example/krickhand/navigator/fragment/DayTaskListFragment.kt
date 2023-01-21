package com.example.krickhand.navigator.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krickhand.navigator.adapter.DayTaskListAdapter
import com.example.krickhand.navigator.databinding.FragmentDaytaskListBinding
import com.example.krickhand.navigator.viewmodel.DayViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DayTaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DayTaskListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentDaytaskListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DayTaskListAdapter
    private val dayViewModel: DayViewModel by viewModels {DayViewModel.Factory}
    //private val dayViewModel: DayViewModel by activityViewModels()
//    private val dayViewModel: DayViewModel by viewModels {
//        DayViewModel.DayViewModelFactory((application as NaviGatorApplication).dayRepository)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaytaskListBinding.inflate(inflater, container, false)
        //val dayViewModel: DayViewModel by activityViewModels()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = DayTaskListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        dayViewModel.today.observe(viewLifecycleOwner) { day ->
            binding.dayTitle.text = day.dayLong
        }

        dayViewModel.daytasklist.observe(viewLifecycleOwner) { items ->
            items.let { adapter.submitList(it)}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DayTaskListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DayTaskListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}