package es.babel.cdm.utils.extensions

import es.babel.cdm.utils.constants.Date.Pattern.DAY
import es.babel.cdm.utils.constants.Date.Pattern.DAY_OF_THE_WEEK
import es.babel.cdm.utils.constants.Date.Pattern.FULL_DATE
import es.babel.cdm.utils.constants.Date.Pattern.FULL_DATE_AND_HOUR
import es.babel.cdm.utils.constants.Date.Pattern.FULL_HOUR
import es.babel.cdm.utils.constants.Date.Pattern.HOUR
import es.babel.cdm.utils.constants.Date.Pattern.HOUR_AND_MINUTE
import es.babel.cdm.utils.constants.Date.Pattern.MINUTE
import es.babel.cdm.utils.constants.Date.Pattern.MONTH
import es.babel.cdm.utils.constants.Date.Pattern.MONTH_AND_DAY
import es.babel.cdm.utils.constants.Date.Pattern.MONTH_OF_THE_YEAR
import es.babel.cdm.utils.constants.Date.Pattern.SECOND
import es.babel.cdm.utils.constants.Date.Pattern.YEAR
import es.babel.cdm.utils.constants.Date.TimeZone.UTC
import java.util.Date
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DateTest() {
    private val date = Date(624288645000)

    @Test
    fun toStringIsRight() {
        assertEquals("1989", date.toString(YEAR))
        assertEquals("10", date.toString(MONTH))
        assertEquals("October", date.toString(MONTH_OF_THE_YEAR))
        assertEquals("13", date.toString(DAY))
        assertEquals("Friday", date.toString(DAY_OF_THE_WEEK))
        assertEquals("14", date.toString(HOUR))
        assertEquals("30", date.toString(MINUTE))
        assertEquals("45", date.toString(SECOND))
        assertEquals("13/10/1989 14:30:45", date.toString(FULL_DATE_AND_HOUR))
        assertEquals("13/10/1989", date.toString(FULL_DATE))
        assertEquals("13/10", date.toString(MONTH_AND_DAY))
        assertEquals("14:30:45", date.toString(FULL_HOUR))
        assertEquals("14:30", date.toString(HOUR_AND_MINUTE))

        assertEquals("14:30", date.toString(HOUR_AND_MINUTE))
        assertEquals("13:30", date.toString(HOUR_AND_MINUTE, UTC))
    }

    @Test
    fun toStringIsWrong() {
        assertNotEquals("2000", date.toString(YEAR))
        assertNotEquals("05", date.toString(MONTH))
        assertNotEquals("May", date.toString(MONTH_OF_THE_YEAR))
        assertNotEquals("25", date.toString(DAY))
        assertNotEquals("Thursday", date.toString(DAY_OF_THE_WEEK))
        assertNotEquals("20", date.toString(HOUR))
        assertNotEquals("15", date.toString(MINUTE))
        assertNotEquals("00", date.toString(SECOND))
        assertNotEquals("25/05/2000 20:15:00", date.toString(FULL_DATE_AND_HOUR))
        assertNotEquals("25/05/2000", date.toString(FULL_DATE))
        assertNotEquals("25/05", date.toString(MONTH_AND_DAY))
        assertNotEquals("20:15:00", date.toString(FULL_HOUR))
        assertNotEquals("20:15", date.toString(HOUR_AND_MINUTE))

        assertNotEquals("13:30", date.toString(HOUR_AND_MINUTE))
        assertNotEquals("14:30", date.toString(HOUR_AND_MINUTE, UTC))
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
        assertEquals("October", date.monthOfTheYear())
    }

    @Test
    fun monthOfTheYearIsWrong() {
        assertNotEquals("May", date.monthOfTheYear())
    }

    @Test
    fun shortMonthOfTheYearIsRight() {
        assertEquals("Oct", date.shortMonthOfTheYear())
    }

    @Test
    fun shortMonthOfTheYearIsWrong() {
        assertNotEquals("April", date.shortMonthOfTheYear())
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
        assertEquals("Friday", date.dayOfTheWeek())
    }

    @Test
    fun dayOfTheWeekIsWrong() {
        assertNotEquals("Thursday", date.dayOfTheWeek())
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

    @Test
    fun monthAndYearToDateIsRight() {
        assertEquals(Date(1606777200000), monthAndYearToDate(month = 11, year = 2020))
        assertEquals(Date(-2177452800000), monthAndYearToDate(month = 0, year = 1901))
        assertEquals(Date(970351200000), monthAndYearToDate(month = 9, year = 2000))
    }

    @Test
    fun monthAndYearToDateIsWrong() {
        assertNotEquals(Date(1606777000000), monthAndYearToDate(month = 11, year = 2020))
        assertNotEquals(Date(-2177452000000), monthAndYearToDate(month = 0, year = 1901))
        assertNotEquals(Date(970351280000), monthAndYearToDate(month = 9, year = 2000))
    }
}
