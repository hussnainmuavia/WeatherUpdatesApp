package com.android.opensooq

import com.android.opensooq.core.api.ApiEndPoints
import com.android.opensooq.core.api.ApiModule
import com.android.opensooq.core.utils.Constants.API_KEY
import com.android.opensooq.core.utils.Constants.FORMAT
import com.android.opensooq.core.utils.Constants.KEY_AMMAN
import com.android.opensooq.core.utils.Constants.KEY_AQABA
import com.android.opensooq.core.utils.Constants.KEY_IRBID
import com.android.opensooq.core.utils.Constants.NUM_OF_DAYS
import com.android.opensooq.core.utils.Constants.TIME_PERIOD
import org.junit.Test

/**
 * Unit test, which will execute the API call to search the results for the states cities.
 */
class SearchResultAPIServiceUnitTest {

    /**
     * Unit test, which will execute the API call for Amman Weather Update.
     */
    @Test
    fun testSearchResultApiCallForAmman() {
        val citySearchResultForAmman =
            ApiModule(ApiEndPoints.BASE_URL).getClient().getSearchResults(
                API_KEY,
                KEY_AMMAN,
                NUM_OF_DAYS,
                TIME_PERIOD,
                FORMAT
            )

        citySearchResultForAmman.test().assertValue {
            it.data != null
            it.data?.request != null
            it.data?.weather != null
            it.data?.currentCondition != null
            it.data?.climateAverages != null
        }
    }

    /**
     * Unit test, which will execute the API call for Irbid Weather Update.
     */
    @Test
    fun testSearchResultApiCallForIrbid() {
        val citySearchResultForIrbid =
            ApiModule(ApiEndPoints.BASE_URL).getClient().getSearchResults(
                API_KEY,
                KEY_IRBID,
                NUM_OF_DAYS,
                TIME_PERIOD,
                FORMAT
            )

        citySearchResultForIrbid.test().assertValue {
            it.data != null
            it.data?.request != null
            it.data?.weather != null
            it.data?.currentCondition != null
            it.data?.climateAverages != null
        }
    }

    /**
     * Unit test, which will execute the API call for Aqaba Weather Update.
     */
    @Test
    fun testSearchResultApiCallForAqaba() {
        val citySearchResultForAqaba =
            ApiModule(ApiEndPoints.BASE_URL).getClient().getSearchResults(
                API_KEY,
                KEY_AQABA,
                NUM_OF_DAYS,
                TIME_PERIOD,
                FORMAT
            )

        citySearchResultForAqaba.test().assertValue {
            it.data != null
            it.data?.request != null
            it.data?.weather != null
            it.data?.currentCondition != null
            it.data?.climateAverages != null
        }
    }
}
