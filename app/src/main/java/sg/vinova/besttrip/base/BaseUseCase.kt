package sg.vinova.besttrip.base

import android.content.Context

/**
 * Created by Hanah on 11/27/2017.
 */
abstract class BaseUseCase<T>(private val context: Context, val manager: DataManager) {
}