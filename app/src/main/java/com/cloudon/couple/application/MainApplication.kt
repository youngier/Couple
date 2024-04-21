package com.cloudon.couple.application

import android.app.Application
import android.content.Context
import com.cloudon.couple.ui.anniversay.type.TypeBean

class MainApplication : Application() {

    companion object {
        lateinit var context: Context

        var selectedType = mutableListOf<TypeBean>()
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}