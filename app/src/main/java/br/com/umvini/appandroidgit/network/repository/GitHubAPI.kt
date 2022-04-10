package br.com.umvini.appandroidgit.network.repository

import br.com.umvini.appandroidgit.network.models.GitHubItemRepository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubAPI {
    @GET("repositories")
    suspend fun getRepositories(
        @Query("q") q: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: String
    ) : Response<GitHubItemRepository>
}