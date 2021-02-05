package es.babel.cdm.utils.extensions

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class IntegerTest {

    @Test
    fun inHalfIsRight() {
        assertEquals(50, 100.inHalf())
        assertEquals(100, 200.inHalf())
        assertEquals(-5, (-10).inHalf())
        assertEquals(0, 0.inHalf())
    }

    @Test
    fun inHalfIsWrong() {
        assertNotEquals(100, 100.inHalf())
        assertNotEquals(20, 200.inHalf())
        assertNotEquals(10, (-10).inHalf())
        assertNotEquals(2, 0.inHalf())
    }

    @Test
    fun toBooleanIsRight() {
        assertTrue(1.toBoolean())
    }

    @Test
    fun toBooleanIsWrong() {
        assertFalse(0.toBoolean())
        assertFalse(10.toBoolean())
        assertFalse(10000.toBoolean())
    }

    @Test
    fun checkNull() {
        assertNotNull(1.checkNull())
        assertNotNull(10.checkNull())
    }
}
