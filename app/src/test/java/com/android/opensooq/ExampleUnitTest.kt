package com.android.opensooq

import com.android.opensooq.core.api.ApiEndPoints
import com.android.opensooq.core.api.ApiModule
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testAllUsersApiCall(){
        val allUsersApi = ApiModule(ApiEndPoints.BASE_URL).getClient().getAudioTracks()

        allUsersApi.test().assertValue {
            it.ms247!!.size > 0

            it.ms247!![0].items != null
            it.ms247!![0].items!!.album != null
            it.ms247!![0].items!!.track != null
            it.ms247!![0].items!!.id != null
        }
    }
}
