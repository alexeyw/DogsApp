package com.avolodin.dogsapp.di

import androidx.lifecycle.ViewModel
import com.avolodin.dogsapp.presentation.BreedImageListViewModel
import com.avolodin.dogsapp.presentation.BreedListViewModel
import com.avolodin.dogsapp.ui.fragment.BreedImageListFragment
import com.avolodin.dogsapp.ui.fragment.BreedListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun breedListFragment(): BreedListFragment

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun breedImageListFragment(): BreedImageListFragment

    @Binds
    @IntoMap
    @ViewModelKey(BreedListViewModel::class)
    abstract fun bindBreedListViewModel(viewModel: BreedListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BreedImageListViewModel::class)
    abstract fun bindBreedImageViewModel(viewModel: BreedImageListViewModel): ViewModel
}