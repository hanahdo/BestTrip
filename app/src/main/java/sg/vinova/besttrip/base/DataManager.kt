package sg.vinova.besttrip.base

import sg.vinova.besttrip.library.BApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Hanah on 11/27/2017.
 */
@Singleton
class DataManager @Inject constructor(private var api: BApi) {
    fun getApi(): BApi = api
}