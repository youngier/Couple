package com.cloudon.couple.ui.anniversay.data

import com.cloudon.couple.ui.anniversay.AnniversaryBean

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

    suspend fun saveAnniversary(bean: AnniversaryBean) {
        anniversaryLocalSource.saveAnniversary(bean)
    }

    suspend fun getAllAnniversary(): MutableList<AnniversaryBean> {
        return anniversaryLocalSource.getAllAnniversary()
    }

}