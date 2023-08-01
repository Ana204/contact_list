package com.picpay.desafio.android.di

import androidx.room.Room
import android.app.Application
import com.picpay.desafio.android.data.local.UserDataBase
import com.picpay.desafio.android.data.remote.UserApi
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.useCase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }


    @Provides
    @Singleton
    fun provideUserRepository(
        api: UserApi,
        db: UserDataBase
    ): UserRepository {
        return UserRepositoryImpl(
            userApi = api,
            userDao = db.dao
        )
    }

    @Provides
    @Singleton
    fun provideUserUseCase(repository: UserRepository): UserUseCase {
        return UserUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAppDataBase(application: Application): UserDataBase {
        return Room.databaseBuilder(
            application,
            UserDataBase::class.java,
            "users.db"
        ).build()
    }
}