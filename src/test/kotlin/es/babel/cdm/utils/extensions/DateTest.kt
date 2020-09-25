package es.babel.cdm.utils.extensions

import es.babel.cdm.utils.constants.Pattern.DAY
import es.babel.cdm.utils.constants.Pattern.DAY_OF_THE_WEEK
import es.babel.cdm.utils.constants.Pattern.FULL_DATE
import es.babel.cdm.utils.constants.Pattern.FULL_DATE_AND_HOUR
import es.babel.cdm.utils.constants.Pattern.FULL_HOUR
import es.babel.cdm.utils.constants.Pattern.HOUR
import es.babel.cdm.utils.constants.Pattern.HOUR_AND_MINUTE
import es.babel.cdm.utils.constants.Pattern.MINUTE
import es.babel.cdm.utils.constants.Pattern.MONTH
import es.babel.cdm.utils.constants.Pattern.MONTH_AND_DAY
import es.babel.cdm.utils.constants.Pattern.MONTH_OF_THE_YEAR
import es.babel.cdm.utils.constants.Pattern.SECOND
import es.babel.cdm.utils.constants.Pattern.YEAR
import es.babel.cdm.utils.constants.TimeZone.UTC
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import java.util.Date
import java.util.TimeZone

class DateTest() {
    private val date = Date(624288645000)

    @Test
    fun toStringIsRight() {
        assertEquals("1989", date.toString(YEAR))
        assertEquals("10", date.toString(MONTH))
        assertEquals("octubre", date.toString(MONTH_OF_THE_YEAR))
        assertEquals("13", date.toString(DAY))
        assertEquals("viernes", date.toString(DAY_OF_THE_WEEK))
        assertEquals("14", date.toString(HOUR))
        assertEquals("30", date.toString(MINUTE))
        assertEquals("45", date.toString(SECOND))
        assertEquals("13/10/1989 14:30:45", date.toString(FULL_DATE_AND_HOUR))
        assertEquals("13/10/1989", date.toString(FULL_DATE))
        assertEquals("13/10", date.toString(MONTH_AND_DAY))
        assertEquals("14:30:45", date.toString(FULL_HOUR))
        assertEquals("14:30", date.toString(HOUR_AND_MINUTE))

        assertEquals("14:30", date.toString(HOUR_AND_MINUTE, TimeZone.getDefault()))
        assertEquals("13:30", date.toString(HOUR_AND_MINUTE, TimeZone.getTimeZone(UTC)))
    }

    @Test
    fun toStringIsWrong() {
        assertNotEquals("2000", date.toString(YEAR))
        assertNotEquals("05", date.toString(MONTH))
        assertNotEquals("mayo", date.toString(MONTH_OF_THE_YEAR))
        assertNotEquals("25", date.toString(DAY))
        assertNotEquals("jueves", date.toString(DAY_OF_THE_WEEK))
        assertNotEquals("20", date.toString(HOUR))
        assertNotEquals("15", date.toString(MINUTE))
        assertNotEquals("00", date.toString(SECOND))
        assertNotEquals("25/05/2000 20:15:00", date.toString(FULL_DATE_AND_HOUR))
        assertNotEquals("25/05/2000", date.toString(FULL_DATE))
        assertNotEquals("25/05", date.toString(MONTH_AND_DAY))
        assertNotEquals("20:15:00", date.toString(FULL_HOUR))
        assertNotEquals("20:15", date.toString(HOUR_AND_MINUTE))

        assertNotEquals("13:30", date.toString(HOUR_AND_MINUTE, TimeZone.getDefault()))
        assertNotEquals("14:30", date.toString(HOUR_AND_MINUTE, TimeZone.getTimeZone(UTC)))
    }

    @Test
    fun yearIsRight() {
        assertEquals("1989", date.year())
    }

    @Test
    fun yearIsWrong() {
        assertNotEquals("2000", date.year())
    }

    @Test
    fun monthIsRight() {
        assertEquals("10", date.month())
    }

    @Test
    fun monthIsWrong() {
        assertNotEquals("05", date.month())
    }

    @Test
    fun monthOfTheYearIsRight() {
        assertEquals("octubre", date.monthOfTheYear())
    }

    @Test
    fun monthOfTheYearIsWrong() {
        assertNotEquals("mayo", date.monthOfTheYear())
    }

    @Test
    fun dayIsRight() {
        assertEquals("13", date.day())
    }

    @Test
    fun dayIsWrong() {
        assertNotEquals("25", date.day())
    }

    @Test
    fun dayOfTheWeekIsRight() {
        assertEquals("viernes", date.dayOfTheWeek())
    }

    @Test
    fun dayOfTheWeekIsWrong() {
        assertNotEquals("jueves", date.dayOfTheWeek())
    }

    @Test
    fun hourIsRight() {
        assertEquals("14", date.hour())
    }

    @Test
    fun hourIsWrong() {
        assertNotEquals("20", date.hour())
    }

    @Test
    fun minuteIsRight() {
        assertEquals("30", date.minute())
    }

    @Test
    fun minuteIsWrong() {
        assertNotEquals("15", date.minute())
    }

    @Test
    fun secondIsRight() {
        assertEquals("45", date.second())
    }

    @Test
    fun secondIsWrong() {
        assertNotEquals("00", date.second())
    }
}
