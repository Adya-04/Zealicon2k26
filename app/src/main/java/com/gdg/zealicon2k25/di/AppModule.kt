package com.gdg.zealicon2k25.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.gdg.zealicon2k25.data.remote.AuthApi
import com.gdg.zealicon2k25.data.remote.ImageUploadApi
import com.gdg.zealicon2k25.pref.PrefDatastore
import com.gdg.zealicon2k25.pref.PrefDatastoreImpl
import com.gdg.zealicon2k25.utils.Constants.BASE_URL
import com.gdg.zealicon2k25.utils.Constants.IMAGE_URL
import com.gdg.zealicon2k25.utils.Constants.ZEALICON_TOKENS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun providesAuthApi(retrofitBuilder: Builder): AuthApi {
        return retrofitBuilder.build().create(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Builder {
        return Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    @Named("ImageRetrofit")
    fun providesRetrofitBuilderImage(): Builder {
        return Builder()
            .baseUrl(IMAGE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun providesDatastore(@ApplicationContext context: Context): DataStore<Preferences>{
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData ={
                    emptyPreferences()
                }
            ), produceFile = {
                context.preferencesDataStoreFile(ZEALICON_TOKENS)
            }
        )
    }

    @Provides
    @Singleton
    fun providesDataPref(datastore: DataStore<Preferences>): PrefDatastore= PrefDatastoreImpl(datastore)

    @Provides
    @Singleton
    fun providesImageApi(@Named("ImageRetrofit")retrofitBuilder: Builder): ImageUploadApi{
        return retrofitBuilder.build().create(ImageUploadApi::class.java)
    }

}