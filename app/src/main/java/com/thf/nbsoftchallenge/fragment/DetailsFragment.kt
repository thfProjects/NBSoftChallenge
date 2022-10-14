package com.thf.nbsoftchallenge.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.thf.nbsoftchallenge.databinding.FragmentDetailsBinding
import com.thf.nbsoftchallenge.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.product?.let {
            Glide.with(this).load(it.imageLink).into(binding.image)
            binding.name.text = it.name
            binding.brand.text = it.brand
            binding.price.text = it.price
        }
    }
}