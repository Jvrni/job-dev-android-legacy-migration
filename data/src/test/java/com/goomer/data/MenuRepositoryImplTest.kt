package com.goomer.data

import com.goomer.data.local.MenuLocalDataSource
import com.goomer.data.models.MenuResponse
import com.goomer.data.repository.MenuRepositoryImpl
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class MenuRepositoryImplTest {

    private val testDispatcher = StandardTestDispatcher()

    @MockK
    private val localDataSource = mockk<MenuLocalDataSource>()

    private val repository = MenuRepositoryImpl(localDataSource)


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getList should emit menus from localDataSource`() = runTest {
        // GIVEN
        val menus = listOf(
            MenuResponse(1, "Café", "Café preto", 8.5, ""),
            MenuResponse(2, "Bolo", "Bolo de chocolate", 12.0, "")
        )
        every { localDataSource.getMenuList() } returns flowOf(menus)

        // WHEN
        val list = repository.getList().toList()

        // THEN
        assertEquals(1, list.size)

        val result = list.first()
        assertEquals(2, result.size)
        assertEquals("Café", result[0].name)
    }

    @Test
    fun `getList should propagate errors from localDataSource`() = runTest {
        // GIVEN
        val exception = IOException("file not found")
        every { localDataSource.getMenuList() } returns flow { throw exception }

        // WHEN
        val flow = repository.getList()

        // THEN
        try {
            flow.collect()
            fail("IOException")
        } catch (e: IOException) {
            assertTrue(e.message!!.contains("file not found"))
        }
    }
}