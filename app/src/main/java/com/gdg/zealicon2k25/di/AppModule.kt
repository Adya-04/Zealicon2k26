package com.gdg.zealicon2k25.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.gdg.zealicon2k25.data.remote.AuthApi
import com.gdg.zealicon2k25.data.remote.EventsApi
import com.gdg.zealicon2k25.data.remote.ImageUploadApi
import com.gdg.zealicon2k25.data.remote.PaymentAPI
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
import okhttp3.OkHttpClient
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)  // Connection timeout
        .readTimeout(30, TimeUnit.SECONDS)     // Read timeout
        .writeTimeout(30, TimeUnit.SECONDS)    // Write timeout
        .build()

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
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    @Named("ImageRetrofit")
    fun providesRetrofitBuilderImage(): Builder {
        return Builder()
            .baseUrl(IMAGE_URL)
            .client(okHttpClient)
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

    @Provides
    @Singleton
    fun providesPaymentApi(retrofitBuilder: Builder): PaymentAPI {
        return retrofitBuilder.build().create(PaymentAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesEventsApi(retrofitBuilder: Builder): EventsApi {
        return retrofitBuilder.build().create(EventsApi::class.java)
    }
}