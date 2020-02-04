package shasin.weatherapp.network.http

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import shasin.weatherapp.network.environment.BaseEnvironment

class HttpClientTest{

    @Before
    @Throws(Exception::class)
    fun setup() {

    }

    @Test
    fun testGetBaseUrl(){
        //Arrange
        var environment = BaseEnvironment()

        //Act
        val expectedBaseUrl = "https://api.apixu.com"
        val httpClient = BaseHttpClient(environment)

        //Assert
        Assert.assertNotNull(httpClient.getApiService())
        Assert.assertNotNull(httpClient.getRetrofit())
        Assert.assertNotNull(httpClient.getRetrofit().baseUrl().host() == expectedBaseUrl)
    }
}