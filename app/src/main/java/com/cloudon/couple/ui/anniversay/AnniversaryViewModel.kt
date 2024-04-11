package com.cloudon.couple.ui.anniversay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudon.couple.ui.anniversay.data.AnniversaryRepository
import kotlinx.coroutines.launch

class AnniversaryViewModel : ViewModel() {

    private val _anniversary = MutableLiveData<MutableList<AnniversaryBean>>()

    val anniversary: LiveData<MutableList<AnniversaryBean>> = _anniversary

    fun getAllAnniversary() {
        viewModelScope.launch {
            _anniversary.postValue(AnniversaryRepository.getInstance().getAllAnniversary())
        }
    }
}