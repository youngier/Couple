package com.cloudon.couple.ui.anniversay.type

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudon.couple.ui.anniversay.data.AnniversaryRepository
import kotlinx.coroutines.launch

class TypeViewModel : ViewModel() {

    private val _type = MutableLiveData<MutableList<TypeBean>>()

    val type: LiveData<MutableList<TypeBean>> = _type

    fun getAllType() {
        viewModelScope.launch {
            var data = AnniversaryRepository.getInstance().getAllType()
            data.add(0, TypeBean(0, "默认分类"))
            _type.postValue(data)
        }
    }

    fun insertType(type: String) {
        viewModelScope.launch {
            AnniversaryRepository.getInstance().saveType(type)
            getAllType()
        }
    }

    fun removeType(type: String) {
        viewModelScope.launch {
            AnniversaryRepository.getInstance().removeType(type)
        }
    }

}