package br.com.umvini.appandroidgit.network.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.umvini.appandroidgit.network.models.GitHubItemRepository
import br.com.umvini.appandroidgit.network.repository.GitHubAPI
import br.com.umvini.appandroidgit.network.repository.GitHubRepository
import br.com.umvini.appandroidgit.network.utils.NetworkUtils
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubEvent
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubInterpret
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit



class GitHubViewModel: ViewModel() {
    private val event = MutableLiveData<GitHubEvent>()
    private val state = MutableLiveData<GitHubState>()

    val viewEvent: LiveData<GitHubEvent>
        get() = event
    val viewState: LiveData<GitHubState>
        get() = state

    public fun interpret(interpret: GitHubInterpret){
        when (interpret) {
            is GitHubInterpret.GetRepositories -> {
                getGitHubRepositories()
            }
        }
    }

    private fun getGitHubRepositories() {
        state.value = GitHubState.ShowLoading

        val retrofitClient: Retrofit = NetworkUtils
            .getRetrofitInstance("https://api.github.com/search/")
        val api = retrofitClient.create(GitHubAPI::class.java)

        val repo = GitHubRepository(api)
        viewModelScope.launch(Dispatchers.IO) {
            val resultApi = repo.getRepositories("stars:>=10000", "stars", "desc")

            afterGetGitHubRepositories(resultApi)
        }
    }

    private fun afterGetGitHubRepositories(resultApi: Response<GitHubItemRepository>){
        when {
            resultApi.isSuccessful -> {
                event.value = resultApi.body()?.let {
                    GitHubEvent.GetRepositoriesSuccessfully(
                        it
                    )
                }
            }

            else -> {
                event.value = GitHubEvent.GetRepositoriesError
            }
        }
        state.value = GitHubState.EndLoading
    }
}