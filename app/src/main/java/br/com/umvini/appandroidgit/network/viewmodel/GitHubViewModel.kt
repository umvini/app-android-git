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

    companion object {
        const val PARAM_MIN_STARS = "stars:>=10000"
        const val PARAM_SORTS = "stars"
        const val PARAM_ORDER = "desc"
    }

    val retrofitClient: Retrofit = NetworkUtils
        .getRetrofitInstance("https://api.github.com/search/")
    val api = retrofitClient.create(GitHubAPI::class.java)

    val repo = GitHubRepository(api)

    private val event = MutableLiveData<GitHubEvent>()
    private val state = MutableLiveData<GitHubState>()

    val viewEvent: LiveData<GitHubEvent>
        get() = event
    val viewState: LiveData<GitHubState>
        get() = state

    var page = 1

    public fun interpret(interpret: GitHubInterpret){
        when (interpret) {
            is GitHubInterpret.GetRepositories -> {
                getGitHubRepositories()
            }

            is GitHubInterpret.GetMoreRepositories -> {
                getGitHubMoreRepositories()
            }
        }
    }

    private fun getGitHubRepositories() {
        state.value = GitHubState.ShowLoading

        viewModelScope.launch(Dispatchers.IO) {
            val resultApi = repo.getRepositories(PARAM_MIN_STARS, PARAM_SORTS, PARAM_ORDER, page.toString())

            afterGetGitHubRepositories(resultApi)
        }
    }

    private fun afterGetGitHubRepositories(resultApi: Response<GitHubItemRepository>){
        when {
            resultApi.isSuccessful -> {
                event.postValue(resultApi.body()?.let {
                    GitHubEvent.GetRepositoriesSuccessfully(
                        it
                    ) } )
            }

            else -> {
                event.postValue(GitHubEvent.GetRepositoriesError)
            }
        }
        state.postValue(GitHubState.EndLoading)
    }

    private fun getGitHubMoreRepositories() {
        state.value = GitHubState.ShowLoading
        page += 1
        viewModelScope.launch(Dispatchers.IO) {
            val resultApi = repo.getRepositories(PARAM_MIN_STARS, PARAM_SORTS, PARAM_ORDER, page.toString())

            afterGetGitHubMoreRepositories(resultApi)
        }
    }

    private fun afterGetGitHubMoreRepositories(resultApi: Response<GitHubItemRepository>){
        when {
            resultApi.isSuccessful -> {
                event.postValue(resultApi.body()?.let {
                    GitHubEvent.GetMoreRepositoriesSuccessfully(
                        it
                    ) } )
            }

            else -> {
                event.postValue(GitHubEvent.GetMoreRepositoriesError)
            }
        }
        state.postValue(GitHubState.EndLoading)
    }
}