package es.babel.cdm.utils.extensions

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ArrayTest {

    @Test
    fun previousIndexIsRight() {
        val index = 3
        assertEquals(2, index.previousIndex())
    }

    @Test
    fun previousIndexIsWrong() {
        val index = 3
        assertNotEquals(3, index.previousIndex())
        assertNotEquals(4, index.previousIndex())
    }

    @Test
    fun nextIndexIsRight() {
        val index = 3
        assertEquals(4, index.nextIndex())
    }

    @Test
    fun nextIndexIsWrong() {
        val index = 3
        assertNotEquals(3, index.nextIndex())
        assertNotEquals(2, index.nextIndex())
    }
}
