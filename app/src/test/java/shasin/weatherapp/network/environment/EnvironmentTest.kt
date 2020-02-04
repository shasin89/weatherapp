package shasin.weatherapp.network.environment

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EnvironmentTest{

    @Before
    @Throws(Exception::class)
    fun setup() {

    }

    @Test
    fun testGetBaseUrl(){
        //Arrange
        var environment = BaseEnvironment()

        //Act
        val expectedResult = "https://api.apixu.com"
        val actualResult = environment.getAPIUXWeatherBaseUrl()

        //Assert
        Assert.assertTrue(expectedResult == actualResult)
    }
}