package com.example.harrypoterbootcamp.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypoterbootcamp.api.HarryPotterRetrofit
import com.example.harrypoterbootcamp.model.CharacterModel
import kotlinx.coroutines.launch

enum class ApiStatus { DONE, LOADING, ERROR }

enum class ApiFilter(val filterWord:String){
    SHOW_GRYFFINDOR("Gryffindor"),
    SHOW_HUFFLEPUFF("Hufflepuff"),
    SHOW_RAVENCLAW("Ravenclaw"),
    SHOW_SLYTHERIN("Slytherin")
}

class HomeViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status:LiveData<ApiStatus>
        get() = _status

    private val _characters=MutableLiveData<List<CharacterModel>>()
    val character:LiveData<List<CharacterModel>>
        get() = _characters

    init {
        getData()
    }

    private fun getData(){
        viewModelScope.launch {
            _status.value=ApiStatus.LOADING

            try {
                _characters.value=HarryPotterRetrofit.retrofitService.getAllCharacter().harrypotter
                _status.value=ApiStatus.DONE
            }catch (e:Exception){
                _status.value=ApiStatus.ERROR
                _characters.value=ArrayList()
            }
        }
    }

    fun getFilterCharacter(character:ApiFilter){
        viewModelScope.launch {
            _status.value=ApiStatus.LOADING

            try {
                _characters.value=HarryPotterRetrofit.retrofitService.getFilterData(character.filterWord).harrypotter
                _status.value=ApiStatus.DONE
            }catch (e:Exception){
                _status.value=ApiStatus.ERROR
                _characters.value=ArrayList()
            }
        }
    }
}