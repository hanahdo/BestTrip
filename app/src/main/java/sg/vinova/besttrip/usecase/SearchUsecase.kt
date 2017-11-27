package sg.vinova.besttrip.usecase

import android.content.Context
import io.reactivex.Flowable
import sg.vinova.besttrip.base.BaseUseCase
import sg.vinova.besttrip.base.DataManager
import sg.vinova.besttrip.model.search.BaseObjectResponse
import javax.inject.Inject

/**
 * Created by Hanah on 11/27/2017.
 */
class SearchUsecase @Inject constructor(context: Context, manager: DataManager) : BaseUseCase<Void>(context, manager) {

    fun getAllSearchResult(query: String): Flowable<BaseObjectResponse> {
        return manager.getApi().getListLocation(query)
    }
}