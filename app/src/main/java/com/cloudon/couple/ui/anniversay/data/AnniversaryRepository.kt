package com.cloudon.couple.ui.anniversay.data

import com.cloudon.couple.ui.anniversay.AnniversaryBean
import com.cloudon.couple.ui.anniversay.type.TypeBean
import com.cloudon.couple.ui.anniversay.type.TypeLocalSource

class AnniversaryRepository {

    companion object {
        private var instance: AnniversaryRepository? = null

        fun getInstance(): AnniversaryRepository {
            if (instance == null) {
                instance = AnniversaryRepository()
            }
            return instance!!
        }

    }

    private var anniversaryLocalSource = AnniversaryLocalSource()
    private var typeLocalSource = TypeLocalSource()

    suspend fun saveAnniversary(bean: AnniversaryBean) {
        anniversaryLocalSource.saveAnniversary(bean)
    }

    suspend fun getAllAnniversary(): MutableList<AnniversaryBean> {
        return anniversaryLocalSource.getAllAnniversary()
    }

    suspend fun saveType(type: String) {
        typeLocalSource.saveType(type)
    }

    suspend fun removeType(type: String) {
        typeLocalSource.removeType(type)
    }

    suspend fun getAllType(): MutableList<TypeBean> {
        return typeLocalSource.getAllType()
    }

}