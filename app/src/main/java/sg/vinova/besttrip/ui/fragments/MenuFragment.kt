package sg.vinova.besttrip.ui.fragments

import android.view.View
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_menu.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.presenter.MenuPresenter
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.ui.activities.MapActivity
import sg.vinova.besttrip.ui.fragments.result.MapFragment
import sg.vinova.besttrip.utils.FirebaseUtils
import sg.vinova.besttrip.utils.GlideUtils
import sg.vinova.besttrip.utils.LogUtils
import sg.vinova.besttrip.widgets.dialogs.BErrorDialog
import javax.inject.Inject

/**
 * Created by Hanah on 11/23/2017.
 */
class MenuFragment : BaseBFragment(), View.OnClickListener {


    @Inject lateinit var presenter: MenuPresenter
    private lateinit var mActivity: MapActivity
    private lateinit var mUser: FirebaseUser

    companion object {
        fun newInstance(): MenuFragment = MenuFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_menu

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        if (!isAdded) return
        if (activity is MapActivity)
            mActivity = activity as MapActivity
        if (FirebaseUtils.getCurrentUser() != null) {
            mUser = FirebaseUtils.getCurrentUser()!!
            initView()
        } else {
            initAnonymousView()
        }

        setOnClick()
    }

    private fun setOnClick() {
        tvHome.setOnClickListener(this)
        tvListTaxi.setOnClickListener(this)
        tvLogOut.setOnClickListener(this)
    }

    private fun initAnonymousView() {
        GlideUtils.loadImage(R.drawable.avatar_placeholder, activity!!, ivAvatar)
        tvUsername.text = getString(R.string.login_now)
        tvEmail.text = getString(R.string.guest)

        tvUsername.setOnClickListener(this)
    }

    private fun initView() {
        GlideUtils.loadImageFromURL(mUser.photoUrl, activity!!, ivAvatar, R.drawable.avatar_placeholder)
        tvUsername.text = mUser.displayName
        tvEmail.text = mUser.email
    }

    override fun bindPresenter() {
        presenter.bind(this)
    }

    override fun unbindPresenter() {
        presenter.unbind()
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.tvUsername -> {
                if (activity !is LoginActivity) changeActivity(LoginActivity::class.java)
            }
            R.id.tvHome -> {
                if (activity !is MapActivity) changeActivity(MapActivity::class.java)
                else changeFragment(MapFragment.newInstance(), false)
            }
            R.id.tvListTaxi -> {
//                if (activity is MapActivity) changeActivity()
//                else changeFragment()
            }
            R.id.tvLogOut -> {
                presenter.logout()
            }
        }
    }

    fun logoutSuccess() {
        LogUtils.bDebug(this.javaClass, "Logout success")
        changeActivity(LoginActivity::class.java)
    }

    fun error(error: String?) {
        LogUtils.bError(this.javaClass, error!!)
        BErrorDialog(context).setMessage(error)!!.show()
    }
}
