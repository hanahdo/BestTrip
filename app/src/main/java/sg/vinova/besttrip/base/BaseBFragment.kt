package sg.vinova.besttrip.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sg.vinova.besttrip.R

/**
 * Created by hanah on 11/22/17.
 */
abstract class BaseBFragment : Fragment() {
    private var baseActivity: BaseBActivity? = null

    abstract fun getLayoutId(): Int

//  fun getLeftIcon(): Int

    abstract fun inject()

    abstract fun init()

    abstract fun bindPresenter()

    abstract fun unbindPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        if (activity is BaseBActivity && baseActivity == null)
            baseActivity = activity as BaseBActivity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(if (getLayoutId() == 0) R.layout.fragment_blank else getLayoutId(), container, false)
        bindPresenter()
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbindPresenter()
    }

    open fun getBaseBActivity(): BaseBActivity = baseActivity!!

    fun changeFragment(fragment: BaseBFragment, addBackStack: Boolean) {
        baseActivity!!.changeFragment(fragment, addBackStack)
    }

    fun replaceFragment(fragment: BaseBFragment, containerId: Int) {
        baseActivity!!.replaceFragment(fragment, containerId)
    }

    fun changeActivity(cls: Class<*>) {
        baseActivity!!.changeActivity(cls)
    }
}
