package com.example.testmobile.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmobile.R
import com.example.testmobile.data.database.adapters.RepositoryAdapter
import com.example.testmobile.data.database.adapters.RepositoryAdapterListener
import com.example.testmobile.data.dto.RepositoryDTO
import com.example.testmobile.data.network.adapters.asDto
import com.example.testmobile.data.network.bodies.results.Repository
import com.example.testmobile.databinding.FragmentHomeBinding
import com.example.testmobile.utils.Failure
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val viewModel: HomeViewModel by viewModels()

    // For RecyclerView
    private val listener = object : RepositoryAdapterListener {
        override fun onRepositorySelected(repository: RepositoryDTO) {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(repository))
        }

    }
    private lateinit var adapter: RepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show load Progress
        binding.progressEmpty.visibility = View.VISIBLE

        // Initialise adapter
        adapter = RepositoryAdapter(listener)

        // Fill recyclerView
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.setLayoutManager(LinearLayoutManager(getActivity()))
        binding.recyclerview.adapter = adapter

        // Get repositories List
        viewModel.getRepositoryList()

        // Response viewModels
        initViewModels()
    }

    private fun initViewModels() {
        // Listen mode choice
        viewModel.repositories.observe(viewLifecycleOwner){
            if (it != null) {
                binding.progressEmpty.visibility = View.GONE
                adapter.setRepositories(it)
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