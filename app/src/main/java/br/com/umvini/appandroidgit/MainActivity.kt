package br.com.umvini.appandroidgit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.umvini.appandroidgit.network.viewmodel.GitHubViewModel
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubEvent
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubInterpret
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubState
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {
    lateinit var animationView: LottieAnimationView

    lateinit var viewModel: GitHubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        initViewModel()

        setObservableState()
        setObservableEvent()
    }

    private fun initViews() {
        animationView = findViewById(R.id.animationView)
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(GitHubViewModel::class.java)
        viewModel.interpret(GitHubInterpret.GetRepositories)
    }

    private fun setObservableState() {
        viewModel.viewState.observe(this, { state ->
            when (state) {
                is GitHubState.ShowLoading -> {
                    animationView.visibility = View.VISIBLE
                }
                is GitHubState.EndLoading -> {
                    animationView.visibility = View.GONE
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