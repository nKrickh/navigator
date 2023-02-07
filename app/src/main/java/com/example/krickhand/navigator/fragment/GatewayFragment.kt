package com.example.krickhand.navigator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.krickhand.navigator.R
import com.example.krickhand.navigator.databinding.FragmentDaytaskListBinding
import com.example.krickhand.navigator.databinding.FragmentGatewayBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [GatewayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GatewayFragment : Fragment() {

    private var _binding: FragmentGatewayBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGatewayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toDay.setOnClickListener {
            gatewayClickHandler(it)
        }
        binding.toJournal.setOnClickListener {
            gatewayClickHandler(it)
        }
        binding.toNotes.setOnClickListener{
            gatewayClickHandler(it)
        }
    }
      fun gatewayClickHandler(view: View) {
        when (view.tag) {
            "toDay" -> {
                val action = GatewayFragmentDirections.actionGatewayToDayView()
                this.findNavController().navigate(action)
            }
            "toNotes" -> {
                val action = GatewayFragmentDirections.actionGatewayToNotes()
                this.findNavController().navigate(action)
            }
            "toJournal" -> {
                val action = GatewayFragmentDirections.actionGatewayToJournal()
                this.findNavController().navigate(action)
            }
        }
    }
}