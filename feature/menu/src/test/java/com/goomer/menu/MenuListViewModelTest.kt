package com.goomer.menu

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.goomer.data.MenuRepository
import com.goomer.data.models.Menu
import com.goomer.menu.list.MenuListViewModel
import com.goomer.menu.list.contract.MenuListContract
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

    private val viewModel by lazy { spyk(MenuListViewModel(repository)) }

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `initial state onStart`() = runTest {
        // GIVEN
        coEvery { repository.getList() } returns emptyFlow()

        // WHEN
        viewModel.event(MenuListContract.Event.OnStart)

        // THEN
        assertEquals(emptyList<Menu>(), viewModel.state.value.list)
    }

    @Test
    fun `GIVEN a fake information WHEN getting it THEN return success value`() = runTest {
        // GIVEN
        coEvery { repository.getList() } returns flowOf(list)

        // WHEN
        viewModel.event(MenuListContract.Event.OnStart)
        advanceUntilIdle()

        // THEN
        assertEquals(list.first().name, viewModel.state.value.list.first().name)
    }

    @Test
    fun `GIVEN a fake information WHEN getting it THEN return failure value`() = runTest {
        // GIVEN
        coEvery { repository.getList() } returns flow { throw IOException("Failed to read JSON") }

        // WHEN
        viewModel.event(MenuListContract.Event.OnStart)
        advanceUntilIdle()
        // THEN
        assertEquals(true, viewModel.state.value.showError)
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
