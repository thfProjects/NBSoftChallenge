package com.thf.nbsoftchallenge.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thf.nbsoftchallenge.application.MainApplication
import com.thf.nbsoftchallenge.R
import com.thf.nbsoftchallenge.adapter.ProductRecylerAdapter
import com.thf.nbsoftchallenge.databinding.FragmentListBinding
import com.thf.nbsoftchallenge.viewmodel.ListViewModel
import com.thf.nbsoftchallenge.viewmodel.factory.ListViewModelFactory

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels {
        ListViewModelFactory(requireActivity().application as MainApplication)
    }
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ProductRecylerAdapter(this)
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.addItemDecoration(divider)

        adapter.onProductClick {
            val bundle = bundleOf("product" to it)
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
        }

        viewModel.products.observe(viewLifecycleOwner) {
            adapter.dataset = it
        }
    }
}