package com.cloudon.couple.ui.anniversay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudon.couple.ui.anniversay.data.AnniversaryRepository
import kotlinx.coroutines.launch

class AnniversaryAddViewModel : ViewModel() {

    private val _anniversaryAdd = MutableLiveData<Boolean>()

    val anniversaryAdd: LiveData<Boolean> = _anniversaryAdd

    fun saveAnniversary(anniversaryBean: AnniversaryBean) {
        viewModelScope.launch {
            AnniversaryRepository.getInstance().saveAnniversary(anniversaryBean)
            _anniversaryAdd.postValue(true)
        }
    }
}