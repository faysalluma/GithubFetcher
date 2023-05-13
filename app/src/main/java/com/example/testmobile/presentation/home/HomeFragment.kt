package com.example.testmobile.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmobile.R
import com.example.testmobile.data.database.adapters.RepositoryAdapter
import com.example.testmobile.data.database.adapters.RepositoryAdapterListener
import com.example.testmobile.data.dto.RepositoryDTO
import com.example.testmobile.data.dto.asBody
import com.example.testmobile.data.network.adapters.asDto
import com.example.testmobile.data.network.bodies.results.Repository
import com.example.testmobile.databinding.FragmentHomeBinding
import com.example.testmobile.utils.Failure
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val viewModel: HomeViewModel by viewModels()
    private var listRepositories : List<Repository> ? = null

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

        // Filter Artisan List
        binding.searchRepository.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                // This method will be called when the user submits the query.
                // Perform any actions you want to perform here.
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // This method will be called when the user changes the query text.
                // Perform any actions you want to perform here.
                filter(newText)
                return false
            }
        })


        // Response viewModels
        initViewModels()
    }

    private fun initViewModels() {
        // Listen mode choice
        viewModel.repositories.observe(viewLifecycleOwner){
            if (it != null) {
                binding.progressEmpty.visibility = View.GONE
                listRepositories = it.map { it.asBody() }
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

    private fun filter(text: String) {
        //new array list that will hold the filtered data
        // val filterdRepositories: MutableList<Repository> = ArrayList()
        val filterdRepositories = arrayListOf<RepositoryDTO>() // create a new ArrayList of type String

        //looping through existing elements
        listRepositories?.let { repositories ->
            for (repository in repositories) {
                //if the existing elements contains the search input
                if (repository.full_name.lowercase(Locale.getDefault())
                        .contains(text.lowercase(Locale.getDefault()))
                ) {
                    //adding the element to filtered list
                    filterdRepositories.add(repository.asDto())
                }
            }

            //calling a method of the adapter class and passing the filtered list
            adapter.filterList(filterdRepositories)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}