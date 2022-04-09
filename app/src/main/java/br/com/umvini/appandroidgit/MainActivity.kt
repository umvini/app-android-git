package br.com.umvini.appandroidgit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.umvini.appandroidgit.network.viewmodel.GitHubViewModel
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubInterpret

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: GitHubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(GitHubViewModel::class.java)


        viewModel.interpret(GitHubInterpret.GetRepositories)
    }
}