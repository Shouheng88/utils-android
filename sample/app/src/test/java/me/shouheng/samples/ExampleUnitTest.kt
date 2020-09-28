package me.shouheng.samples

import me.shouheng.utils.ktx.*
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2.toLong())
    }

    @Test
    fun test_String_Ktx() {
        Assert.assertEquals("ABC".lowerFirstLetter(), "aBC")
        Assert.assertEquals("abc".upperFirstLetter(), "Abc")
        Assert.assertEquals("  ".isTrimEmpty(), true)
        Assert.assertEquals("  ".isSpace(), true)
        Assert.assertEquals("abc".reversed(), "cba")
        Assert.assertEquals("j@ke.fyi".isEmail(), true)
        Assert.assertFalse("asda".md2().isEmpty())
    }
}