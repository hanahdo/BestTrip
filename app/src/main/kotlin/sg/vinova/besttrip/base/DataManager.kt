package sg.vinova.besttrip.base

import sg.vinova.besttrip.library.BApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(private var api: BApi) {
    fun getApi(): BApi = api
}