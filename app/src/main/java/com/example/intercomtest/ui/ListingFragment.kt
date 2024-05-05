package com.example.intercomtest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intercomtest.R
import com.example.intercomtest.adapter.UniversityListAdapter
import com.example.intercomtest.base.BaseFragment
import com.example.intercomtest.databinding.FragmentListingBinding
import com.example.intercomtest.model.UniversityListResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListingFragment : BaseFragment(R.layout.fragment_listing) {

    private var _binding: FragmentListingBinding? = null
    private val binding: FragmentListingBinding
        get() = _binding as FragmentListingBinding

    private val viewModel: ListingViewModel by viewModels()

    private lateinit var listAdapter: UniversityListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getList()
    }

    override fun initializeView() {
        listAdapter = UniversityListAdapter(mutableListOf()) {
            val bundle = bundleOf(
                "name" to it.name,
                "country" to it.country,
                "state" to it.stateProvince,
            )
            findNavController().navigate(
                R.id.action_listingFragment_to_detailFragment, bundle
            )
        }
        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = listAdapter
        }
    }

    override fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {
                setUIState(it.list)
            }
        }
    }

    private fun setUIState(list: List<UniversityListResponse>) {
        listAdapter.update(list)
    }
}