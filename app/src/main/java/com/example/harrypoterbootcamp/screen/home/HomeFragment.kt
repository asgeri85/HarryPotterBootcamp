package com.example.harrypoterbootcamp.screen.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.harrypoterbootcamp.R
import com.example.harrypoterbootcamp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel by lazy { HomeViewModel() }
    private val characterAdapter by lazy { CharacterAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel

        binding.rvHome.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = characterAdapter
            characterAdapter.onClick={
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        homeViewModel.getFilterCharacter(
            when (item.itemId) {
                R.id.action_grffindor -> ApiFilter.SHOW_GRYFFINDOR
                R.id.action_hufflepuff -> ApiFilter.SHOW_HUFFLEPUFF
                R.id.action_ravenclaw -> ApiFilter.SHOW_RAVENCLAW
                else -> ApiFilter.SHOW_SLYTHERIN
            }
        )
        return true
    }

}