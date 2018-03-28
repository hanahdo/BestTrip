package sg.vinova.besttrip.base

import android.content.Context

abstract class BaseUseCase<T>(private val context: Context, val manager: DataManager) {
}