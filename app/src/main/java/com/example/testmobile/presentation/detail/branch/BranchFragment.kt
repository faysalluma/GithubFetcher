package com.example.testmobile.presentation.detail.branch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmobile.R
import com.example.testmobile.data.database.adapters.BranchAdapter
import com.example.testmobile.data.database.adapters.RepositoryAdapter
import com.example.testmobile.databinding.FragmentBranchBinding
import com.example.testmobile.databinding.FragmentDetailBinding
import com.example.testmobile.presentation.detail.DetailFragmentArgs
import com.example.testmobile.utils.Failure
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BranchFragment : Fragment() {

    private var _binding: FragmentBranchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val viewModel: BranchViewModel by viewModels()
    private lateinit var adapter: BranchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBranchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show load Progress
        binding.progressEmpty.visibility = View.VISIBLE

        // Initialise adapter
        adapter = BranchAdapter()

        // Fill recyclerView
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.setLayoutManager(LinearLayoutManager(getActivity()))
        binding.recyclerview.adapter = adapter

        // Get repositories List
        viewModel.getRepositoryBranchList()

        // Response viewModels
        initViewModels()
    }

    private fun initViewModels() {
        // Listen mode choice
        viewModel.branches.observe(viewLifecycleOwner){
            if (it != null) {
                binding.progressEmpty.visibility = View.GONE
                adapter.setBranches(it)
            }
        }

        viewModel.error.observe(viewLifecycleOwner){
            if (it != null) {
                when(it) {
                    is Failure.NetworkFailure -> Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()
                    is Failure.FeatureFailure -> getString(R.string.feature_failure)
                    else -> ""
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}