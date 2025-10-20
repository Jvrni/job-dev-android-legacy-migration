package com.goomer.data

import com.google.gson.Gson
import com.goomer.data.local.JsonReader
import com.goomer.data.local.MenuLocalDataSource
import com.goomer.data.models.MenuResponse
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class MenuLocalDataSourceTest {

    private val gson = Gson()

    @MockK
    private val jsonReader = mockk<JsonReader>()

    private val localDataSource = MenuLocalDataSource(jsonReader, gson)

    @Test
    fun `getList should emit list of menus`() = runTest {
        // GIVEN
        val json = """
            [
                {"id":1,"name":"Café","description":"Café preto","price":8.5,"imageUrl":""},
                {"id":2,"name":"Bolo","description":"Bolo de chocolate","price":12.0,"imageUrl":""}
            ]
        """.trimIndent()

        every { jsonReader.readAsset("menu.json") } returns json

        // WHEN
        val list = mutableListOf<List<MenuResponse>>()
        localDataSource.getMenuList().collect { list.add(it) }

        // THEN
        assertEquals(1, list.size)

        val menu = list.first()
        assertEquals(2, menu.size)
        assertEquals("Café", menu[0].name)
        assertEquals("Bolo", menu[1].name)
    }

    @Test
    fun `getList should throw exception when jsonReader fails`() = runTest {
        // GIVEN
        every { jsonReader.readAsset("menu.json") } throws IOException("File not found")

        // WHEN
        val flow = localDataSource.getMenuList()

        // THEN
        try {
            flow.collect()
            fail("IOException")
        } catch (e: IOException) {
            assertTrue(e.message!!.contains("File not found"))
        }
    }
}