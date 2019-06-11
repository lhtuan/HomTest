package lht.com.hometest

import lht.com.hometest.utils.StringUtil
import org.junit.Assert
import org.junit.Test

class StringUtilTest{
    @Test
    fun test_breakKeyword(){
        //empty cases
        Assert.assertEquals(StringUtil.breakKeyword(null), null)
        Assert.assertEquals(StringUtil.breakKeyword(""), "")
        Assert.assertEquals(StringUtil.breakKeyword(" "), " ")

        //1 word
        Assert.assertEquals(StringUtil.breakKeyword("xiaomi"), "xiaomi")

        //2 words
        Assert.assertEquals(StringUtil.breakKeyword("bitis hunter"), "bitis\nhunter")

        //>= 3 words
        Assert.assertEquals(StringUtil.breakKeyword("bitis hunter x"), "bitis\nhunter x")
        Assert.assertEquals(StringUtil.breakKeyword("nguyễn nhật ánh"), "nguyễn\nnhật ánh")
        Assert.assertEquals(StringUtil.breakKeyword("đắc nhân tâm"), "đắc\nnhân tâm")
        Assert.assertEquals(StringUtil.breakKeyword("tai nghe bluetooth"), "tai nghe\nbluetooth")
        Assert.assertEquals(StringUtil.breakKeyword("kem chống nắng"), "kem chống\nnắng")
        Assert.assertEquals(StringUtil.breakKeyword("anh chính là thanh xuân của em"), "anh chính là\nthanh xuân của em")
    }
}