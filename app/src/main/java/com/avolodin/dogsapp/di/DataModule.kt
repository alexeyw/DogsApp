package com.avolodin.dogsapp.di

import android.content.Context
import com.avolodin.dogsapp.BuildConfig
import com.avolodin.dogsapp.data.remote.DogsApiService
import com.avolodin.dogsapp.data.remote.DogsRepositoryImpl
import com.avolodin.dogsapp.data.repository.Dogs
import com.avolodin.dogsapp.data.repository.DogsDataRepository
import com.avolodin.dogsapp.domain.DogsRepository

import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


@Module(includes = [DataModuleBinds::class])
class DataModule {

    @Provides
    fun provideCache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000)
    }

    @Provides
    fun provideFile(context: Context): File {
        val file = File(context.cacheDir, "HttpCache")
        file.mkdirs()
        return file
    }


    @Provides
    fun provideDogsApiService(cache: Cache): DogsApiService {
        val okHttpBuilder = OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .cache(cache)

        return Retrofit.Builder()
            .client(okHttpBuilder.build())
            .baseUrl(BuildConfig.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(DogsApiService::class.java)
    }


}

@Module
abstract class DataModuleBinds {

    @Binds
    abstract fun bindDogsDataRepository(dataRepository: DogsDataRepository): DogsRepository

    @Binds
    abstract fun bindDogs(breeds: DogsRepositoryImpl): Dogs

}