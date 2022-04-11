package br.com.umvini.appandroidgit.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UtilsUnitTest {

    @Test
    fun validateStringIsNullReturnUnderscore() {
        assertEquals("-", Utils.stringIsNullReturnUnderscore(null))
    }

    @Test
    fun validateStringIsNullReturnString() {
        assertEquals("Vinicius", Utils.stringIsNullReturnUnderscore("Vinicius"))
    }

    @Test
    fun validateIntIsNullReturnUnderscore() {
        assertEquals("-", Utils.intIsNullReturnUnderscore(null))
    }

    @Test
    fun validateIntIsNullReturnString() {
        assertEquals("10", Utils.intIsNullReturnUnderscore(10))
    }
}