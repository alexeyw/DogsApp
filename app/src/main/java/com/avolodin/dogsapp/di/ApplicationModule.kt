package com.avolodin.dogsapp.di

import com.avolodin.dogsapp.ui.adapter.BreedImageListAdapter
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
object ApplicationModule {

    @JvmStatic
    @Provides
    fun provideImageListAdapter(picasso: Picasso): BreedImageListAdapter {
        return BreedImageListAdapter(picasso)
    }

}

