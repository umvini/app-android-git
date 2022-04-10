package br.com.umvini.appandroidgit.network.viewmodel.auxiliaries

import br.com.umvini.appandroidgit.network.models.GitHubItemRepository

sealed class GitHubInterpret {
    object GetRepositories: GitHubInterpret()

    object GetMoreRepositories: GitHubInterpret()
}

sealed class GitHubEvent {
    data class GetRepositoriesSuccessfully(
        val response: GitHubItemRepository
    ): GitHubEvent()

    object GetRepositoriesError: GitHubEvent()

    data class GetMoreRepositoriesSuccessfully(
        val response: GitHubItemRepository
    ): GitHubEvent()

    object GetMoreRepositoriesError: GitHubEvent()
}

sealed class GitHubState {
    object ShowLoading: GitHubState()
    object EndLoading: GitHubState()
}