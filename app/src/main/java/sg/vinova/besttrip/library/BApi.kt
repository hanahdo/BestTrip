package sg.vinova.besttrip.library

import android.content.res.Resources
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import sg.vinova.besttrip.BuildConfig
import sg.vinova.besttrip.R
import sg.vinova.besttrip.model.search.BaseObjectResponse

/**
 * Created by Hanah on 11/27/2017.
 */
interface BApi {
    companion object {
        const val textS = "textsearch/"
        const val nearbyS = "nearbysearch/"
        var ApiKey = Resources.getSystem().getString(R.string.API_key)
    }

    @GET(textS + "json")
    fun getListLocation(@Query("query") query: String, @Query("key") key: String = ApiKey): Flowable<BaseObjectResponse>
}
