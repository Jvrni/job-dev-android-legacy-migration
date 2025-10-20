package com.goomer.ps

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.goomer.data.MenuRepository
import com.goomer.data.models.Menu
import com.goomer.ps.feature.menu.list.MenuListViewModel
import com.goomer.ps.feature.menu.list.state.MenuListState
import com.goomer.ps.navigation.MenuNavigator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import kotlin.getValue

@ExperimentalCoroutinesApi
class MenuListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    private val repository = mockk<MenuRepository>()

    @MockK
    private val navigator = mockk<MenuNavigator>()

    private val viewModel by lazy { spyk(MenuListViewModel(repository, navigator)) }

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `initial state should be Idle`() = runTest {
        // GIVEN
        coEvery { repository.getList() } returns emptyFlow()

        // WHEN
        viewModel.loadData()

        // THEN
        assertEquals(UiState.Idle, viewModel.uiState.value)
    }

    @Test
    fun `GIVEN a fake information WHEN getting it THEN return success value`() = runTest {
        // GIVEN
        coEvery { repository.getList() } returns flowOf(list)

        // WHEN
        viewModel.loadData()
        advanceUntilIdle()

        // THEN
        assertEquals(
            (viewModel.uiState.value as UiState.Success<MenuListState>).data.list.first().name,
            list[0].name
        )
    }

    @Test
    fun `GIVEN a fake information WHEN getting it THEN return failure value`() = runTest {
        // GIVEN
        coEvery { repository.getList() } returns flow { throw IOException("Failed to read JSON") }

        // WHEN
        viewModel.loadData()
        advanceUntilIdle()

        // THEN
        assertEquals(UiState.Error, viewModel.uiState.value)
    }

    companion object {
        val list = listOf(
            Menu(
                id = 0,
                name = "Sorvete",
                description = "2 bolas – sabores do dia.",
                price = 10.00,
                imageUrl = "",
            ),
            Menu(
                id = 1,
                name = "Brownie",
                description = "Com calda de chocolate.",
                price = 14.00,
                imageUrl = "",
            )
        )
    }
}
