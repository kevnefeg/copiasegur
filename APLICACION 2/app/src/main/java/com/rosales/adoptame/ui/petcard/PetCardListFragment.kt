package com.rosales.adoptame.ui.petcard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.rosales.adoptame.AdoptameApplication
import com.rosales.adoptame.R
import com.rosales.adoptame.databinding.FragmentPetCardListBinding
import com.rosales.adoptame.databinding.ItemPetcardBinding
import com.rosales.adoptame.ui.ViewModelFactory


class PetCardListFragment : Fragment() {
    private val viewModelFactory by lazy {
        val application = requireActivity().application as AdoptameApplication
        ViewModelFactory(application.getPetCardRepository())
    }
    private val viewModel: PetCardViewModel by viewModels {
        viewModelFactory
    }
    private lateinit var binding: FragmentPetCardListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pet_card_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wordListRecyclerView = binding.petlistrecyclerview
        val petCardAdapter = PetCardAdapter()
        wordListRecyclerView.apply {
            adapter = petCardAdapter
        }

        viewModel.getAllPets()

        viewModel.status.observe(viewLifecycleOwner) {status ->
            when (status){
                is PetCardUiState.Error -> Log.d("Word List Status", "Error",status.exception)
                    PetCardUiState.Loading -> Log.d("Word List Status", "Loading")
                is PetCardUiState.Success -> handleSuccess(status, petCardAdapter)
            }
        }
    }

    private fun handleSuccess(status: PetCardUiState.Success, wordAdapter: PetCardAdapter) {
        status.pet.observe(viewLifecycleOwner){data ->
            wordAdapter.setData(data)
        }
    }
}