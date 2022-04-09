package br.com.umvini.appandroidgit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.umvini.appandroidgit.network.viewmodel.GitHubViewModel
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubEvent
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubInterpret
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubState

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: GitHubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setObservableState()
        setObservableEvent()


        viewModel = ViewModelProvider(this).get(GitHubViewModel::class.java)
        viewModel.interpret(GitHubInterpret.GetRepositories)
    }

    private fun setObservableState() {
        viewModel.viewState.observe(this, { state ->
            when (state) {
                is GitHubState.ShowLoading -> {

                }
                is GitHubState.EndLoading -> {

                }
            }
        })
    }

    private fun setObservableEvent() {
        viewModel.viewEvent.observe(this, { event ->
            when (event) {
                is GitHubEvent.GetRepositoriesSuccessfully -> {

                }
                is GitHubEvent.GetRepositoriesError -> {

                }
            }
        })
    }
}