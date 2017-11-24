package sg.vinova.besttrip.ui.fragments

import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.presenter.MenuPresenter
import javax.inject.Inject

/**
 * Created by Hanah on 11/23/2017.
 */
class MenuFragment : BaseBFragment() {
    @Inject lateinit var presenter: MenuPresenter

    companion object {
        fun newInstance(): MenuFragment = MenuFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_blank

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        if (!isAdded) return
    }

    override fun bindPresenter() {
        presenter.bind(this)
    }

    override fun unbindPresenter() {
        presenter.unbind()
    }
}