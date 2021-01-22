package es.babel.cdm.utils.extensions

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FloatTest {

    @Test
    fun getMinIsRight() {
        assertEquals(1f, getMin(listOf(1f, 3f, 4f, 5f)))
        assertEquals(5f, getMin(listOf(10f, 15f, 11f, 5f)))
        assertEquals(3f, getMin(listOf(6f, 3f, 7f, 9f)))
    }

    @Test
    fun getMinIsWrong() {
        assertNotEquals(3f, getMin(listOf(1f, 3f, 4f, 5f)))
        assertNotEquals(11f, getMin(listOf(10f, 15f, 11f, 5f)))
        assertNotEquals(9f, getMin(listOf(6f, 3f, 7f, 9f)))
    }

    @Test
    fun getMaxIsRight() {
        assertEquals(5f, getMax(listOf(1f, 3f, 4f, 5f)))
        assertEquals(15f, getMax(listOf(10f, 15f, 11f, 5f)))
        assertEquals(9f, getMax(listOf(6f, 3f, 7f, 9f)))
    }

    @Test
    fun getMaxIsWrong() {
        assertNotEquals(1f, getMax(listOf(1f, 3f, 4f, 5f)))
        assertNotEquals(11f, getMax(listOf(10f, 15f, 11f, 5f)))
        assertNotEquals(7f, getMax(listOf(6f, 3f, 7f, 9f)))
    }
}
