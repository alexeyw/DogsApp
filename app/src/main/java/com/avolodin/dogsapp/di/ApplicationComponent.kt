package com.avolodin.dogsapp.di

import android.content.Context
import com.avolodin.dogsapp.DogsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        PicassoModule::class,
        DataModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<DogsApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}