package com.example.krickhand.navigator.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.krickhand.navigator.databinding.FragmentDaytaskDetailBinding
import com.example.krickhand.navigator.dto.DayTaskDetail
import com.example.krickhand.navigator.viewmodel.DayViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DayTaskDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DayTaskDetailFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    private var _binding: FragmentDaytaskDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val dayViewModel: DayViewModel by viewModels { DayViewModel.Factory}
    private val args: DayTaskDetailFragmentArgs by navArgs()

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
        //dayViewModel.setDayTask(args.daytaskId)

        //        binding.detailDay.text = args.daytaskId.toString()
        //dayViewModel.setDayTask(args.daytaskId)
//        dayViewModel.dayTask.observe(viewLifecycleOwner) { dt ->
//            if (dt != null) {
//                binding.detailDay.text = dt.dayShort
//                binding.detailTask.text = dt.taskName
//                binding.detailTaskDesc.text = dt.taskDesc
//                binding.detailDesc.text = dt.desc
//                binding.detailScheduledStart.text = dt.scheduledStart
//                binding.detailScheduledEnd.text = dt.scheduledEnd
//            }
//        }
        // create observer
        val daytaskObserver = Observer<DayTaskDetail> {
            binding.detailDay.text = it.dayShort
            binding.detailTask.text = it.taskName
            binding.detailTaskDesc.text = it.taskDesc
            binding.detailDesc.text = it.desc
            binding.detailScheduledStart.text = it.scheduledStart
            binding.detailScheduledEnd.text = it.scheduledEnd
        }

        // observe selected daytask_detail field in viewmodel
        dayViewModel.dayTask.observe(viewLifecycleOwner, daytaskObserver)
        dayViewModel.setDayTask(1L)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_task, container, false)
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DayTaskDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DayTaskDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}