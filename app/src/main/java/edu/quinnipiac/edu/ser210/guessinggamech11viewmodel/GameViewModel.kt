package edu.quinnipiac.edu.ser210.guessinggamech11viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import edu.quinnipiac.edu.ser210.guessinggamech11viewmodel.databinding.FragmentGameBinding

class GameViewModel: ViewModel() {
        val words = listOf("Android", "Activity", "Fragment")
        //the words the user has to goes
        val secretWord = words.random().uppercase()
        //how thw word is displayed
        var secretWordDisplay = ""
        //correct and incorrect Guesses
        var correctGuesses = ""
        var incorrectGuesses = ""
        //livesLeft
        var livesLeft = 0

    init {
        secretWordDisplay = deriveSecretWordDisplay()
    }

        //this builds a string for how the secret word should be
        //displayed on the screen
        fun deriveSecretWordDisplay(): String{
            var display = ""
            secretWord.forEach {
                //call checkletter for each letter in secretword, and
                //add its return value to the end of the display variable
                display += checkLetter(it.toString())
            }
            return display
        }

        //this checks whether the secret word contains the letter
        //the user has guessed. If so, it returns the letter.
        //If not, it returns ""
        fun checkLetter(str:String) = when (correctGuesses.contains(str)){
            true -> str
            false -> "_"
        }

        //this gets called each time the user makes a guess
        fun makeGuess(guess:String){
            if(guess.length == 1){
                if (secretWord.contains(guess)){
                    //for each correct guess, update correctGuesses
                    //and secretWordDisplay
                    correctGuesses += guess
                    secretWordDisplay = deriveSecretWordDisplay()
                }
                //for each wrong guess, update incorrectGuess and livesLeft
                else {
                    incorrectGuesses += "$guess"
                    livesLeft--

                }
            }
        }

        //the game is won if the secret word matches secretWordDisplay
        fun isWon() = secretWord.equals(secretWordDisplay, true)

        //the game is lost when the user runs out of lives
        fun isLost() = livesLeft <= 0

        fun wonLostMessage(): String {
            var message = ""
            if(isWon()) message = "You won!"
            else if (isLost()) message = "You lost!"
            message += "The word was $secretWord."
            //wonLostMessage() returns a string indicating whether
            //the user has won or lost, and what the secret word was.
            return message
        }

    }
