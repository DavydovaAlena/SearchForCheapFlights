package ru.adavydova.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.adavydova.navigation.databinding.FragmentSelectCountryBinding
import ru.adavydova.navigation.utils.Constant
import ru.adavydova.selectcountry_feature.SelectCountryEvent
import ru.adavydova.selectcountry_feature.SelectCountryViewModel
import ru.adavydova.selectcountry_feature.adapter_delegate.CountrySelectAdapter


class SelectCountryFragment : Fragment() {

    private val viewModel by hiltNavGraphViewModels<SelectCountryViewModel>(R.id.selectCountryFragment)

    private var _binding: FragmentSelectCountryBinding? = null
    private val binding get() = _binding!!

    private var countrySelectAdapter: CountrySelectAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        val cityFrom = arguments?.getString(Constant.CITY_FROM_TO_SELECT_COUNTRY)
        viewModel.onEvent(SelectCountryEvent.ChangeCityFromState(cityFrom))
        val cityTo = arguments?.getString(Constant.TO_THE_CITY_TO_SELECT_COUNTRY)
        viewModel.onEvent(SelectCountryEvent.ChangeCityToState(cityTo))

        binding.selectCountryRv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = countrySelectAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                findNavController().currentBackStackEntry?.savedStateHandle?.getStateFlow(
                    Constant.CALENDAR_DATE,
                    initialValue = ""
                )?.collectLatest { date ->
                    if (date != "") {
                        viewModel.onEvent(SelectCountryEvent.ChangeTheDepartureTime(date))
                    }
                }
            }
        }

//        update straightRailsState
//         first case: update list items
//         second case: update show all value
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.straightRailsState.collectLatest {
                    countrySelectAdapter?.items = viewModel.countrySelectAdapterItemsState.value
                    binding.selectCountryRv.adapter?.notifyItemChanged(2)
                }
            }
        }


        //updateChipsState
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.chips.collectLatest {
                    countrySelectAdapter?.items = viewModel.countrySelectAdapterItemsState.value
                    binding.selectCountryRv.adapter?.notifyItemChanged(1)
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.straightRailsState.collectLatest {
                    viewModel.countrySelectAdapterItemsState.collectLatest {
                        countrySelectAdapter?.items = it
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.subscribeToPriceState.collectLatest {
                    countrySelectAdapter?.items = viewModel.countrySelectAdapterItemsState.value
                    binding.selectCountryRv.adapter?.notifyItemChanged(3)
                }
            }
        }

    }

    private fun setupAdapter() {
        countrySelectAdapter = CountrySelectAdapter(
            goToTheBack = { findNavController().popBackStack() },
            showAllStraightRails = { viewModel.onEvent(SelectCountryEvent.ShowAllStraightRails) },
            selectStraightRailsItem = {},
            viewAllTickets = {},
            subscribeToThePrice = { viewModel.onEvent(SelectCountryEvent.SubscribeToThePrice(it)) },
            updateCityTo = { viewModel.onEvent(SelectCountryEvent.ChangeCityToState(it)) },
            updateCityFrom = { viewModel.onEvent(SelectCountryEvent.ChangeCityFromState(it)) },
            openCalendar = { findNavController().navigate(R.id.action_selectCountryFragment_to_calendarFragment) },

        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}