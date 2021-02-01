package es.babel.cdm.utils.extensions

import es.babel.cdm.utils.constants.Date.Pattern.FULL_DATE
import es.babel.cdm.utils.constants.Date.Pattern.FULL_DATE_AND_HOUR
import es.babel.cdm.utils.constants.Date.TimeZone.UTC
import java.util.Date
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
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

    @Test
    fun isValidEmailIsRight() {
        assertTrue("test@test.com".isValidEmail())
        assertTrue("a@a.aa".isValidEmail())
        assertTrue("bb@b.bb".isValidEmail())
        assertTrue("ccc@cc.ccc".isValidEmail())
    }

    @Test
    fun isValidEmailIsWrong() {
        assertFalse("".isValidEmail())
        assertFalse("a".isValidEmail())
        assertFalse("a@a.a".isValidEmail())
        assertFalse("bb@b.b".isValidEmail())
        assertFalse("b@b".isValidEmail())
        assertFalse("cc.ccc".isValidEmail())
    }

    @Test
    fun isValidPasswordIsRight() {
        assertTrue("Test1234".isValidPassword())
        assertTrue("@Test432".isValidPassword())
        assertTrue("!*Test99".isValidPassword())
        assertTrue("\$Test1234".isValidPassword())
    }

    @Test
    fun isValidPasswordIsWrong() {
        assertFalse("".isValidPassword())
        assertFalse("test".isValidPassword())
        assertFalse("TEST".isValidPassword())
        assertFalse("TEST1234".isValidPassword())
        assertFalse("test1234".isValidPassword())
        assertFalse("Test1234$1234!1234".isValidPassword())
        assertFalse("Test1234)(".isValidPassword())
        assertFalse("//*Test!".isValidPassword())
    }

    @Test
    fun isValidPhoneIsRight() {
        assertTrue("1234".isValidPhone())
        assertTrue("4444466666".isValidPhone())
        assertTrue("12344568880".isValidPhone())
        assertTrue("1234567891234".isValidPhone())
    }

    @Test
    fun isValidPhoneIsWrong() {
        assertFalse("".isValidPhone())
        assertFalse("test".isValidPhone())
        assertFalse("123".isValidPhone())
        assertFalse("test123".isValidPhone())
        assertFalse("1234567890123456".isValidPhone())
    }

    @Test
    fun isValidSpanishPhoneIsRight() {
        assertTrue("623456789".isValidSpanishPhone())
        assertTrue("700000000".isValidSpanishPhone())
        assertTrue("666666666".isValidSpanishPhone())
    }

    @Test
    fun isValidSpanishPhoneIsWrong() {
        assertFalse("123456789".isValidSpanishPhone())
        assertFalse("000000000".isValidSpanishPhone())
        assertFalse("".isValidSpanishPhone())
        assertFalse("4444466666".isValidSpanishPhone())
        assertFalse("12344568880".isValidSpanishPhone())
        assertFalse("1234567891234".isValidSpanishPhone())
        assertFalse("1234".isValidSpanishPhone())
        assertFalse("test".isValidSpanishPhone())
        assertFalse("test1234".isValidSpanishPhone())
    }

    @Test
    fun isValidDNIRight() {
        assertTrue("54566045V".isValidDNI())
        assertTrue("54566045v".isValidDNI())
    }

    @Test
    fun isValidDNIWrong() {
        assertFalse("".isValidDNI())
        assertFalse("11111111 a".isValidDNI())
        assertFalse("11111 A".isValidDNI())
        assertFalse("a1111111A".isValidDNI())
        assertFalse("a1111¨111A".isValidDNI())
    }

    @Test
    fun isValidNIERight() {
        assertTrue("X7454868Q".isValidNIE())
        assertTrue("x7454868Q".isValidNIE())
    }

    @Test
    fun isValidNIEWrong() {
        assertFalse("".isValidNIE())
        assertFalse("a1111111b".isValidNIE())
        assertFalse("X11111111ssss".isValidNIE())
        assertFalse("z11111d".isValidNIE())
        assertFalse("x111111Aa".isValidNIE())
        assertFalse("x111!111Aa".isValidNIE())
    }

    @Test
    fun capitalizeWordsIsRight() {
        assertEquals("Test", "test".capitalizeWords())
        assertEquals("Test", "TEST".capitalizeWords())
        assertEquals("Test Test", "TEST Test".capitalizeWords())
        assertEquals("Test Test", "Test TEST".capitalizeWords())
        assertEquals("Test Test", "TEST TEST".capitalizeWords())
        assertEquals("@test", "@test".capitalizeWords())
        assertEquals("@test Test", "@test Test".capitalizeWords())
    }

    @Test
    fun capitalizeWordsIsWrong() {
        assertNotEquals("test", "test".capitalizeWords())
        assertNotEquals("test", "TEST".capitalizeWords())
        assertNotEquals("TEST Test", "TEST Test".capitalizeWords())
        assertNotEquals("Test TEST", "Test TEST".capitalizeWords())
        assertNotEquals("TEST Test", "TEST TEST".capitalizeWords())
        assertNotEquals("@Test", "@test".capitalizeWords())
        assertNotEquals("@Test TEST", "@test Test".capitalizeWords())
    }

    @Test
    fun toPriceStringIsRight() {
        assertEquals("0 €", "0".toPriceString())
        assertEquals("50 €", "50".toPriceString())
        assertEquals("1000 €", "1000".toPriceString())
        assertEquals("999999 €", "999999".toPriceString())
    }

    @Test
    fun toPriceStringIsWrong() {
        assertNotEquals("50", "50".toPriceString())
        assertNotEquals("50€", "50".toPriceString())
        assertNotEquals("50,00€", "50".toPriceString())
        assertNotEquals("50.00", "50".toPriceString())
    }

    @Test
    fun toNormalizedStrings() {
        assertEquals("a", "á".normalizeText())
        assertEquals("e", "é".normalizeText())
        assertEquals("i", "í".normalizeText())
        assertEquals("o", "ó".normalizeText())
        assertEquals("u", "ú".normalizeText())
        assertEquals("n", "ñ".normalizeText())
    }

    @Test
    fun replaceDotsByColon() {
        assertEquals("11:11", "11.11".replaceDotsByColon())
        assertEquals("11:11", "11:11".replaceDotsByColon())
        assertEquals("Test:test", "Test.test".replaceDotsByColon())
    }

    @Test
    fun toNumberWithDots() {
        assertEquals("123", "123".toNumberWithDots())
        assertEquals("123", "123,00".toNumberWithDots())
        assertEquals("12.312.313", "12312313,000".toNumberWithDots())
        assertEquals("12.300", "12300".toNumberWithDots())
        assertEquals("0", "a".toNumberWithDots())
    }

    @Test
    fun isDniRight() {
        assertTrue("54566045V".isDni())
        assertTrue("54566045v".isDni())
    }

    @Test
    fun isDniWrong() {
        assertFalse("".isDni())
        assertFalse("11111111 a".isDni())
        assertFalse("11111 A".isDni())
        assertFalse("a1111111A".isDni())
        assertFalse("a1111¨111A".isDni())
    }

    @Test
    fun isNieRight() {
        assertTrue("X7454868Q".isNie())
        assertTrue("x7454868Q".isNie())
    }

    @Test
    fun isNieWrong() {
        assertFalse("".isNie())
        assertFalse("a1111111b".isNie())
        assertFalse("X11111111ssss".isNie())
        assertFalse("z11111d".isNie())
        assertFalse("x111111Aa".isNie())
        assertFalse("x111!111Aa".isNie())
    }
}
