package com.picpay.desafio.android

import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.data.repository.SharedPreferencesRepositoryImpl
import com.picpay.desafio.android.data.repository.UserRepositoryCacheImpl
import com.picpay.desafio.android.data.repository.UserRepositoryRemoteImpl
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.SharedPreferencesRepository
import com.picpay.desafio.android.domain.repository.UserRepositoryCache
import com.picpay.desafio.android.domain.repository.UserRepositoryRemote
import com.picpay.desafio.android.domain.useCase.UserUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.Calendar

class GetUsersTest {

    //pegar usuario remoto, fazer cache
    //retornar a lista de usuarios remoto
    @Test
    fun devePegarUsuariosDoRepositorioRemotoFazerCacheERetornarListaUsuarios() = runBlocking {
        //given
        //criando mockks para as interfaces de Repository
        val repositoryRemote = mockk<UserRepositoryRemote>()
        val repositoryCache = mockk<UserRepositoryCache>()
        val sharedPreferences = mockk<SharedPreferencesRepository>()

        //criando mockk para o objet calendar
        val calendar = mockk<Calendar>()

        val listUsers = emptyList<UserEntity>()
        val listUsersRemote = UserEntity(1, "image", "ana", "ana204")
        val listCache = listOf(listUsersRemote)

        //criando os comportamentos do mockk
        coEvery { calendar.timeInMillis } returns 0
        coEvery { repositoryCache.upsertAll(listCache) } returns Unit
        coEvery {repositoryCache.getAllUser() } returns listUsers
        coEvery { repositoryRemote.getUserFromApi() } returns listOf(listUsersRemote)
        coEvery { sharedPreferences.saveTime(300000) } returns Unit

        //when - chamando o metodo mockado
        val useCase = UserUseCase(repositoryRemote, sharedPreferences, repositoryCache, calendar)
        val result = useCase.getUsers()

        //then - verificando resultado
        assertThat(result).isNotEmpty()
        assertThat(result[0].name).isEqualTo("ana")
        assertThat(result.count()).isEqualTo(1)

    }

    //existe usario em cache - e o tempo e valido retornar a lista
    //garatir que esta retornando a lista local
    @Test
    fun deveVerificarSeExisteUsuarioEmCacheESeOTempoEValidoERetornarAListaDoCache() = runBlocking {
        //given
        val repositoryRemote = mockk<UserRepositoryRemote>()
        val repositoryCache = mockk<UserRepositoryCache>()
        val sharedPreferences = mockk<SharedPreferencesRepository>()
        val calendar = mockk<Calendar>()

        val listCache = mutableListOf<UserEntity>()
        val user2 = UserEntity(2, "image", "name", "userName")
        val user3 = UserEntity(3, "image", "philipp", "philipp20")
        listCache.add(user2)
        listCache.add(user3)

        coEvery { calendar.timeInMillis } returns (System.currentTimeMillis())
        coEvery {repositoryCache.getAllUser() } returns listCache
        coEvery { sharedPreferences.getTime() } returns (calendar.timeInMillis + 300000)

        //when
        val useCase = UserUseCase(repositoryRemote, sharedPreferences, repositoryCache, calendar)
        val result = useCase.getUsers()

        //then
        assertThat(result).isNotEmpty()
        assertThat(result.count()).isEqualTo(2)
    }
}