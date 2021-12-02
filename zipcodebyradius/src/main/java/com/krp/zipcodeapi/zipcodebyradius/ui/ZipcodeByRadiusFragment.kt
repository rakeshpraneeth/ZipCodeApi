package com.krp.zipcodeapi.zipcodebyradius.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.krp.zipcodeapi.api.ApiConsumer
import com.krp.zipcodeapi.zipcodebyradius.R
import com.krp.zipcodeapi.zipcodebyradius.adapter.CustomListAdapter
import com.krp.zipcodeapi.zipcodebyradius.databinding.FragmentZipcodeByRadiusBinding
import com.krp.zipcodeapi.zipcodebyradius.repository.ZipcodeByRadiusRepositoryImpl
import com.krp.zipcodeapi.zipcodebyradius.viewmodel.ZipcodeByRadiusViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ZipcodeByRadiusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ZipcodeByRadiusFragment : Fragment() {

    private lateinit var binding: FragmentZipcodeByRadiusBinding

    //Enhancement: Can use Dagger to inject the viewModel.
    private val repository =
        ZipcodeByRadiusRepositoryImpl(ApiConsumer.getInstance().getApiService())
    private val zipcodeByRadiusViewModel = ZipcodeByRadiusViewModel(repository)
    private val zipcodeListAdapter = CustomListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentZipcodeByRadiusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel = zipcodeByRadiusViewModel
            lifecycleOwner = viewLifecycleOwner
            initializeRv()
            observeData()
        }
    }

    private fun initializeRv() {
        with(binding.zipcodeList) {
            val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            ContextCompat.getDrawable(context, R.drawable.divider)
                ?.let { itemDecoration.setDrawable(it) }
            setHasFixedSize(true)
            adapter = zipcodeListAdapter
            addItemDecoration(itemDecoration)
        }
    }

    /**
     * Observes for the LiveData in the view model.
     *
     * Sets the data to the adapter if the data is not empty.
     * Shows the snackbar with error message.
     */
    private fun observeData() {
        with(zipcodeByRadiusViewModel) {
            listItems.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) { zipcodeListAdapter.setList(it) }
            }
            message.observe(viewLifecycleOwner) { resourceId ->
                resourceId?.let {
                    Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
                }
            }
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