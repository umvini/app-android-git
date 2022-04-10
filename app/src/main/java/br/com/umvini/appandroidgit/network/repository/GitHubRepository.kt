package br.com.umvini.appandroidgit.network.repository

import br.com.umvini.appandroidgit.network.models.GitHubItemRepository
import retrofit2.Response

class GitHubRepository(val api: GitHubAPI) {
    suspend fun getRepositories(q: String, sort: String, order: String, page: String) : Response<GitHubItemRepository> =
        api.getRepositories(q, sort, order, page)
}