package com.netanel.hometest.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.netanel.hometest.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        getCharacters()
    }

    private fun getCharacters() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCharacters()
            viewModel.dataResult.collect {
                when (it) {
                    DataState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is DataState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.textView.text = it.data.toString()
                    }

                    is DataState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.textView.text = it.message
                    }
                }
            }
        }
    }

    companion object {
        val TAG = this::class.java.simpleName.toString()

        fun newInstance() = HomeFragment()
    }
}
