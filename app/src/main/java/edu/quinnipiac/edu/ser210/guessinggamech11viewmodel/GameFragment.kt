package edu.quinnipiac.edu.ser210.guessinggamech11viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import edu.quinnipiac.edu.ser210.guessinggamech11viewmodel.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    //properties for view binding
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel:GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val view = binding.root
        //makes a viewModel provider and only makes a fresh view model if theres not an existing one in the UI controller
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        updateScreen()

        binding.guessButton.setOnClickListener() {
            //call makeGuess to deal with the users guess
            viewModel.makeGuess(binding.guess.text.toString().uppercase())
            //reset the edit text and update the screen
            binding.guess.text = null
            updateScreen()
            //if the user has won or lost, navigate to ResultFragment,
            // passing it the wonLostMessage() return value
            if(viewModel.isWon()||viewModel.isLost()) {
                val action = GameFragmentDirections
                    .actionGameFragmentToResultFragment(viewModel.wonLostMessage())
                view.findNavController().navigate(action)
            }
        }
        return view
    }

    //when the fragment no longer has access to its layout,
    //set the _binding property to null
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //set the layout's text views
    fun updateScreen() {
        binding.word.text = viewModel.secretWordDisplay
        binding.lives.text= "You have ${viewModel.livesLeft} lives left."
        binding.incorrectGuesses.text = "Incorrect guess: ${viewModel.incorrectGuesses}"
    }
}