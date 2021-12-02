package com.krp.zipcodeapi.zipcodebyradius.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.krp.zipcodeapi.zipcodebyradius.R
import com.krp.zipcodeapi.zipcodebyradius.databinding.FragmentZipcodeByRadiusBinding
import com.krp.zipcodeapi.zipcodebyradius.viewmodel.ZipcodeByRadiusViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ZipcodeByRadiusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ZipcodeByRadiusFragment : Fragment() {

    private lateinit var binding: FragmentZipcodeByRadiusBinding
    private val zipcodeByRadiusViewModel = ZipcodeByRadiusViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentZipcodeByRadiusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            viewModel = zipcodeByRadiusViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    companion object {
        /**
         * TAG to use for fragment.
         */
        const val TAG = "ZipcodeByRadiusFragment"
        /**
         * @return A new instance of fragment [ZipcodeByRadiusFragment].
         */
        @JvmStatic
        fun newInstance() = ZipcodeByRadiusFragment()
    }
}