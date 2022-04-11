package br.com.umvini.appandroidgit.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.umvini.appandroidgit.mock.gitHubItemRepository
import br.com.umvini.appandroidgit.network.repository.GitHubAPI
import br.com.umvini.appandroidgit.network.repository.GitHubRepository
import br.com.umvini.appandroidgit.network.viewmodel.GitHubViewModel
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubEvent
import br.com.umvini.appandroidgit.network.viewmodel.auxiliaries.GitHubInterpret
import br.com.umvini.appandroidgit.testrule.CoroutineTestRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class GitHubViewModelUnitTest {

    lateinit var gitHubViewModel: GitHubViewModel
    lateinit var gitHubRepository: GitHubRepository

    @Mock
    lateinit var gitHubApi: GitHubAPI

    @get:Rule
    val instantTaskExecutionRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gitHubRepository = GitHubRepository(gitHubApi)
        gitHubViewModel = GitHubViewModel()
    }

    @Test
    fun getRepositories() {
        runBlocking {
            Mockito.`when`(gitHubRepository.getRepositories(
                GitHubViewModel.PARAM_MIN_STARS,
                GitHubViewModel.PARAM_SORTS,
                GitHubViewModel.PARAM_ORDER,
                "1"))
                .thenReturn(Response.success(gitHubItemRepository))

            gitHubViewModel.interpret(GitHubInterpret.GetRepositories)

            assertEquals(
                GitHubEvent.GetRepositoriesSuccessfully(gitHubItemRepository),
                gitHubViewModel.viewEvent.value
            )
        }
    }
}