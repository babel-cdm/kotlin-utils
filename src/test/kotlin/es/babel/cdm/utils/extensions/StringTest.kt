package es.babel.cdm.utils.extensions

import es.babel.cdm.utils.constants.Pattern.FULL_DATE
import es.babel.cdm.utils.constants.Pattern.FULL_DATE_AND_HOUR
import es.babel.cdm.utils.constants.TimeZone.UTC
import java.util.Date
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class StringTest {

    @Test
    fun toDateIsRight() {
        assertEquals(Date(624236400000), "13/10/1989".toDate(FULL_DATE))
        assertEquals(Date(732063600000), "14/03/1993".toDate(FULL_DATE))
        assertEquals(Date(-477190800000), "18/11/1954".toDate(FULL_DATE))
        assertEquals(Date(-389494800000), "29/08/1957".toDate(FULL_DATE))

        assertEquals(Date(624288645000), "13/10/1989 13:30:45".toDate(FULL_DATE_AND_HOUR, UTC))
        assertEquals(Date(624236400000), "12/10/1989 23:00:00".toDate(FULL_DATE_AND_HOUR, UTC))
        assertEquals(Date(946681200000), "31/12/1999 23:00:00".toDate(FULL_DATE_AND_HOUR, UTC))
    }

    @Test
    fun toDateIsWrong() {
        assertNotEquals(Date(1601050197090), "13/10/1989".toDate(FULL_DATE))
        assertNotEquals(Date(1601050197090), "14/03/1993".toDate(FULL_DATE))
        assertNotEquals(Date(1601050197090), "18/11/1954".toDate(FULL_DATE))
        assertNotEquals(Date(1601050197090), "29/08/1957".toDate(FULL_DATE))

        assertNotEquals(Date(624236400000), "13/10/1989 13:30:45".toDate(FULL_DATE_AND_HOUR, UTC))
        assertNotEquals(Date(624150000000), "12/10/1989 23:00:00".toDate(FULL_DATE_AND_HOUR, UTC))
        assertNotEquals(Date(946594800000), "31/12/1999 23:00:00".toDate(FULL_DATE_AND_HOUR, UTC))
    }

    @Test
    fun localeStringDateToUtcStringDateIsRight() {
        assertEquals(
            "13/10/1989 13:30:45",
            "13/10/1989 14:30:45"
                .localeStringDateToUtcStringDate(
                    FULL_DATE_AND_HOUR,
                    FULL_DATE_AND_HOUR
                )
        )

        assertEquals(
            "12/10/1989 23:00:00",
            "13/10/1989"
                .localeStringDateToUtcStringDate(
                    FULL_DATE,
                    FULL_DATE_AND_HOUR
                )
        )

        assertEquals(
            "31/12/1999 23:00:00",
            "01/01/2000"
                .localeStringDateToUtcStringDate(
                    FULL_DATE,
                    FULL_DATE_AND_HOUR
                )
        )
    }

    @Test
    fun localeStringDateToUtcStringDateIsWrong() {
        assertNotEquals(
            "13/10/1989 14:30:45",
            "13/10/1989 14:30:45"
                .localeStringDateToUtcStringDate(
                    FULL_DATE_AND_HOUR,
                    FULL_DATE_AND_HOUR
                )
        )

        assertNotEquals(
            "13/10/1989 00:00:00",
            "13/10/1989"
                .localeStringDateToUtcStringDate(
                    FULL_DATE,
                    FULL_DATE_AND_HOUR
                )
        )

        assertNotEquals(
            "01/01/2000 00:00:00",
            "01/01/2000"
                .localeStringDateToUtcStringDate(
                    FULL_DATE,
                    FULL_DATE_AND_HOUR
                )
        )
    }

    @Test
    fun utcStringDateToLocaleStringDateIsRight() {
        assertEquals(
            "13/10/1989 14:30:45",
            "13/10/1989 13:30:45"
                .utcStringDateToLocaleStringDate(
                    FULL_DATE_AND_HOUR,
                    FULL_DATE_AND_HOUR
                )
        )

        assertEquals(
            "13/10/1989",
            "12/10/1989 23:00:00"
                .utcStringDateToLocaleStringDate(
                    FULL_DATE_AND_HOUR,
                    FULL_DATE
                )
        )

        assertEquals(
            "01/01/2000",
            "31/12/1999 23:00:00"
                .utcStringDateToLocaleStringDate(
                    FULL_DATE_AND_HOUR,
                    FULL_DATE
                )
        )
    }
}
