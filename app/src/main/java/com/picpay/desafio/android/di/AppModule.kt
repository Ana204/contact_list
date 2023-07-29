package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.remote.UserApi
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.useCase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserApi(): UserApi{
        return Retrofit.Builder()
            .baseUrl("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
            .build()
            .create(UserApi::class.java)
    }


    @Provides
    @Singleton
    fun provideUserRepository(api: UserApi) : UserRepository{
        return UserRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUserUseCase(repository: UserRepository) : UserUseCase {
        return UserUseCase(repository)
    }
}