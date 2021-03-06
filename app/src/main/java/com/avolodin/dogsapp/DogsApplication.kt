package com.avolodin.dogsapp

import com.avolodin.dogsapp.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class DogsApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(applicationContext)
    }
}