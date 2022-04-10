package br.com.umvini.appandroidgit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.umvini.appandroidgit.adapter.GitHubItemRepositoryAdapter
import br.com.umvini.appandroidgit.network.viewmodel.GitHubViewModel
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubEvent
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubInterpret
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubState
import com.airbnb.lottie.LottieAnimationView
import androidx.annotation.NonNull




class MainActivity : AppCompatActivity() {
    lateinit var animationView: LottieAnimationView
    lateinit var rvListRepositories: RecyclerView

    lateinit var viewModel: GitHubViewModel

    lateinit var adapter: GitHubItemRepositoryAdapter

    var isLoading = false

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
        rvListRepositories = findViewById(R.id.rv_list_repositories)
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
                    rvListRepositories.visibility = View.GONE
                }
                is GitHubState.EndLoading -> {
                    animationView.visibility = View.GONE
                    rvListRepositories.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setObservableEvent() {
        viewModel.viewEvent.observe(this, { event ->
            when (event) {
                is GitHubEvent.GetRepositoriesSuccessfully -> {
                    adapter = GitHubItemRepositoryAdapter(event.response.items)
                    setDataInRecyclerView(adapter)
                }
                is GitHubEvent.GetRepositoriesError -> {

                }

                is GitHubEvent.GetMoreRepositoriesSuccessfully -> {
                    adapter.addRepositories(event.response.items)
                    isLoading = false
                }
                is GitHubEvent.GetMoreRepositoriesError -> {

                }
            }
        })
    }

    private fun setDataInRecyclerView(adapter: GitHubItemRepositoryAdapter) {
        rvListRepositories.layoutManager = LinearLayoutManager(applicationContext)
        rvListRepositories.adapter = adapter
        setEndlessScrollRecyclerView()
    }

    private fun setEndlessScrollRecyclerView(){
        rvListRepositories.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == rvListRepositories.adapter!!.getItemCount() - 1) {
                        loadMoreRepositories()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMoreRepositories() {
        viewModel.interpret(GitHubInterpret.GetMoreRepositories)
    }
}