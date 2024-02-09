package com.netanel.hometest.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.netanel.hometest.databinding.FragmentHomeBinding
import com.netanel.hometest.home.view.adapter.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var characterAdapter: CharactersAdapter

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
        initRecyclerView()
        getCharacters()
    }

    private fun initRecyclerView() {
        characterAdapter = CharactersAdapter()
        binding.characterRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
        }
    }

    private fun getCharacters() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCharacters()
            viewModel.charactersResult.collect {
                when (it) {
                    DataState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is DataState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        characterAdapter.submitList(it.data?.results)
                    }

                    is DataState.Error -> {
                        binding.progressBar.visibility = View.GONE
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
