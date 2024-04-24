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
import ru.adavydova.allticket_feature.AllTicketViewModel
import ru.adavydova.allticket_feature.TicketAdapter
import ru.adavydova.navigation.databinding.FragmentAllTicketsBinding
import ru.adavydova.navigation.utils.Constant
import java.text.SimpleDateFormat
import java.util.Locale

class AllTicketsFragment : Fragment() {

    private val viewModel by hiltNavGraphViewModels<AllTicketViewModel>(R.id.allTicketsFragment)

    private var _binding: FragmentAllTicketsBinding? = null;
    private val binding get() = _binding!!;

    private var cityFrom: String? = null
    private var cityTo: String? = null
    private var date: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.apply {
            cityFrom = getString(Constant.CITY_FROM)
            cityTo = getString(Constant.CITY_TO)
            date = getString(Constant.CALENDAR_DATE)
        }

        _binding = FragmentAllTicketsBinding.inflate(inflater, container, false)
        val cityValue = "$cityFrom-$cityTo"
        binding.city.text = cityValue
        parseDate()?.let {
            val newDate = it + ", 1 пассажир"
            binding.date.text = newDate
        }
        return binding.root
    }

    private fun parseDate(): String? {
        val oldFormat = SimpleDateFormat("dd LLL, E", Locale("ru"))
        val newFormat = SimpleDateFormat("dd LLLL", Locale("ru"))
        return date?.let {
            val newDate = oldFormat.parse(it)
            newDate?.let { it1 -> newFormat.format(it1) }
        } ?: date
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tickets.collectLatest {
                    if (it.isNotEmpty()) {
                        binding.recyclerView.apply {
                            this.adapter = TicketAdapter(it)
                            layoutManager =
                                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        }
                    }
                }
            }
        }

        binding.arrow.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}