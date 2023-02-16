package com.example.krickhand.navigator.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
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
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

        }
        //binding.dayViewModel = vm
        //binding.lifecycleOwner = viewLifecycleOwner

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment DayTaskListFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            DayTaskListFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }

}