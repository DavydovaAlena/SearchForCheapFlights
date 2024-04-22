package ru.adavydova.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.adavydova.navigation.databinding.FragmentSearchDialogBinding
import ru.adavydova.search_feature.PopularDestinationAdapter
import ru.adavydova.search_feature.SearchEvent
import ru.adavydova.search_feature.SearchViewModel
import ru.adavydova.ui_component.adapter_delegate.utils.Constant


class SearchDialogFragment : BottomSheetDialogFragment(R.layout.fragment_search_dialog) {

    private val viewModel by hiltNavGraphViewModels<SearchViewModel>(R.id.searchDialogFragment)

    private var _binding: FragmentSearchDialogBinding? = null
    private val binding get() = _binding!!;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchDialogBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val offsetTop = 100
        val metrics = context?.resources?.displayMetrics
        val height = metrics?.heightPixels

        (dialog as? BottomSheetDialog)?.behavior?.apply {

            setStyle( ru.adavydova.ui_component.R.style.BottomDialogSheetStyle, ru.adavydova.ui_component.R.style.AppBottomSheetDialogTheme )
            isGestureInsetBottomIgnored = true
            height?.let {
                peekHeight = (it*0.90).toInt()}
            expandedOffset = offsetTop
            state = BottomSheetBehavior.STATE_EXPANDED
        }

        val cityFrom = arguments?.getString(Constant.CITY_FROM)
        viewModel.onEvent(SearchEvent.UpdateCityFrom(city = cityFrom?:""))


        binding.apply {

            fromTheCountry.text = cityFrom

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.offersTickets.collectLatest {offerTickets->
                        offerTicketsRV.adapter = PopularDestinationAdapter(
                            popularDistinctions = offerTickets,
                            goToTheSelectCity = {

                            })
                        offerTicketsRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    }

                }
            }

            difficultRouteBtn.setOnClickListener {
                findNavController().navigate(R.id.action_searchDialogFragment_to_stubFragment)
            }

            theWeekendsBtn.setOnClickListener {
                findNavController().navigate(R.id.action_searchDialogFragment_to_stubFragment)
            }

            hotTicketsBtn.setOnClickListener {
                findNavController().navigate(R.id.action_searchDialogFragment_to_stubFragment)
            }

            anywhereBtn.setOnClickListener {
//                val countrySize = viewModel.offersTickets.value.lastIndex
//                val index =  (0..countrySize).random()
//                val countryTo = viewModel.offersTickets.value[index].title
                toTheCountry.setText("Кострома")
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
