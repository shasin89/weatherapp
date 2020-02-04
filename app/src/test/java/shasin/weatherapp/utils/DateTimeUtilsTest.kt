package shasin.weatherapp.utils

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DateTimeUtilsTest{


    @Before
    @Throws(Exception::class)
    fun setup() {

    }

    @Test
    fun testDateTimeGetDayOutput(){
        //Arrange
        val date  = "2019-04-29"

        //Act
        val expectedResult = "Monday"
        val actualResult = DateTimeUtils.getDay(date)

        //Assert
        Assert.assertTrue(expectedResult == actualResult)
    }
}