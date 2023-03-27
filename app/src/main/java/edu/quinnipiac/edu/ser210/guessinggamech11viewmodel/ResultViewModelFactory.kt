package edu.quinnipiac.edu.ser210.guessinggamech11viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

//the string is needed to create the resultViewModel object
class ResultViewModelFactory(private val finalResult: String) : ViewModelProvider.Factory{
    //the view model provider uses the method to create view model objects
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResultViewModel::class.java))
            return ResultViewModel(finalResult) as T
        throw IllegalArgumentException("Unknown ViewModel")
    }
}