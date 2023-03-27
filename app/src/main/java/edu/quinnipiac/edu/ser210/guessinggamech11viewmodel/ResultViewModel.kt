package edu.quinnipiac.edu.ser210.guessinggamech11viewmodel

import androidx.lifecycle.ViewModel

//we gave it a constructor because we have to pass in an argument for the game's logic
class ResultViewModel(finalResult:String): ViewModel() {
   //we'll use it to set the result property
    val result = finalResult

}