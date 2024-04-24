package ru.adavydova.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.adavydova.main_screen.MainScreenEvent
import ru.adavydova.main_screen.MainScreenViewModel
import ru.adavydova.main_screen.adapter_delegate_item.MainScreenAdapter
import ru.adavydova.navigation.databinding.FragmentMainScreenBinding
import ru.adavydova.navigation.utils.Constant


class MainScreenFragment : Fragment() {

    private val viewModel by hiltNavGraphViewModels<MainScreenViewModel>(R.id.main_nav_graph)

    private var _binding: FragmentMainScreenBinding? = null;
    private val binding get() = _binding!!;

    private var mainScreenAdapter: MainScreenAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupAdapter()
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        binding.recyclerMain.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = mainScreenAdapter
            itemAnimator = DefaultItemAnimator()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.displayedItemsState.collectLatest {
                        mainScreenAdapter?.items = it
                    }
                }
            }

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cityFromState.collectLatest { cityFromState ->
                    if (cityFromState != null && viewModel.firstOpening.value) {
                        mainScreenAdapter?.items = viewModel.displayedItemsState.value
                        binding.recyclerMain.adapter?.notifyItemChanged(0)
                        viewModel.onEvent(MainScreenEvent.ChangeStateCityFromInitial)
                    }
                }
            }
        }



        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.offers.collectLatest {
                    mainScreenAdapter?.items = viewModel.displayedItemsState.value
                    binding.recyclerMain.adapter?.notifyItemChanged(1)
                }
            }
        }
    }

    private fun setupAdapter() {
        mainScreenAdapter = MainScreenAdapter(
            changeCountryFrom = { viewModel.onEvent(MainScreenEvent.UpdateFromTheCityValue(it)) },
            selectCountryTo = {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_searchDialogFragment,
                    args = bundleOf(Constant.CITY_FROM to viewModel.cityFromState.value))
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


