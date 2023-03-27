package edu.quinnipiac.edu.ser210.guessinggamech11viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import edu.quinnipiac.edu.ser210.guessinggamech11viewmodel.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding:FragmentResultBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel:ResultViewModel
    lateinit var viewModelFactory: ResultViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater,container,false)
        val view = binding.root
        val result = ResultFragmentArgs.fromBundle(requireArguments()).result
        //create a view model factory object
        viewModelFactory = ResultViewModelFactory(result)
        //pass the viewModel factory to the view model provider
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ResultViewModel::class.java)
        //set the text view to the result String thats passed from
        //GameFragment
        binding.wonLost.text = viewModel.result
        binding.newGameButton.setOnClickListener{
            view.findNavController()
                .navigate(R.id.action_resultFragment_to_gameFragment)
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }


}