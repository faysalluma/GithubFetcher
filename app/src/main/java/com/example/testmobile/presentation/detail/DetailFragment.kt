package com.example.testmobile.presentation.detail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.testmobile.R
import com.example.testmobile.data.database.adapters.ViewPagerAdapter
import com.example.testmobile.databinding.FragmentDetailBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs() // Get arguments navigation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Save args into datastore to get into TabLayout part
        val fullName = args.repositories.full_name.split("/")
        viewModel.saveFullName(fullName[0], fullName[1])

        // Set Title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.repositories.full_name

        initViewModels()
    }

    override fun onResume() {
        super.onResume()
        // Create TabLayout with Viewpager
        setupTabLayout()
        setupViewPager()
        viewModel.setRefreshTabLayout()
    }

    private fun initViewModels() {

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        }

        // To fix error duplicate create Tablayout and ViewPager
        viewModel.createViewPager.observe(viewLifecycleOwner) {
            // Create TabLayout with Viewpager
            binding.tabLayout.removeAllTabs()
            setupTabLayout()
            setupViewPager()
        }

    }

    private fun setupViewPager() {
        // Fix Transations error because of thread conncurence
        val uiHandler = Handler(Looper.getMainLooper())
        uiHandler.post {
            binding.viewPager.apply {
                adapter = ViewPagerAdapter(
                    (activity as FragmentActivity).supportFragmentManager,
                    binding.tabLayout.tabCount
                )
                addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
            }
        }
    }

    private fun setupTabLayout() {
        binding.tabLayout.apply {
            addTab(this.newTab().setText(getString(R.string.branches)))
            addTab(this.newTab().setText(getString(R.string.contributors)))
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.position?.let {
                        binding.viewPager.currentItem = it
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}