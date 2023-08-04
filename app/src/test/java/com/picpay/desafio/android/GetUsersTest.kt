package com.picpay.desafio.android

import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.data.repository.SharedPreferencesRepositoryImpl
import com.picpay.desafio.android.data.repository.UserRepositoryCacheImpl
import com.picpay.desafio.android.data.repository.UserRepositoryRemoteImpl
import com.picpay.desafio.android.domain.useCase.UserUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.Calendar

class GetUsersTest {

    @Test
    fun devePegarUsuariosDoRepositorioRemotoFazerCacheERetornarListaUsuarios() = runBlocking {
        //given
        val repositoryRemote = mockk<UserRepositoryRemoteImpl>()
        val repositoryCache = mockk<UserRepositoryCacheImpl>()
        val sharedPreferences = mockk<SharedPreferencesRepositoryImpl>()
        val calendar = mockk<Calendar>()
        val listUsers = emptyList<UserEntity>()
        val listUsersRemote = UserEntity(1, "image", "ana", "ana204")

        val listCache = listOf(listUsersRemote)

        coEvery { calendar.timeInMillis } returns 0
        coEvery { repositoryCache.upsertAll(listCache) } returns Unit
        coEvery {repositoryCache.getAllUser() } returns listUsers
        coEvery { repositoryRemote.getUserFromApi() } returns listOf(listUsersRemote)
        coEvery { sharedPreferences.saveTime(300000) } returns Unit

        //when
        val useCase = UserUseCase(repositoryRemote, sharedPreferences, repositoryCache, calendar)
        val result = useCase.getUsers()

        //then
        assertThat(result).isNotEmpty()
        assertThat(result[0].name).isEqualTo("ana")
        assertThat(result.count()).isEqualTo(1)

    }
}