package sg.vinova.besttrip.ui.fragments

import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_menu.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.exts.checkIsAdded
import sg.vinova.besttrip.exts.debug
import sg.vinova.besttrip.exts.loadCircleImage
import sg.vinova.besttrip.presenter.MenuPresenter
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.ui.activities.MapActivity
import sg.vinova.besttrip.ui.fragments.result.MapFragment
import javax.inject.Inject

class MenuFragment : BaseBFragment(), View.OnClickListener {
    @Inject
    lateinit var presenter: MenuPresenter
    private lateinit var mActivity: MapActivity
    private lateinit var mUser: FirebaseUser
    private lateinit var mAuth: FirebaseAuth

    companion object {
        fun newInstance(): MenuFragment = MenuFragment()
    }

    override fun getLeftIcon(): Int = 0

    override fun getLayoutId(): Int = R.layout.fragment_menu

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        checkIsAdded()
        if (activity is MapActivity)
            mActivity = activity as MapActivity

        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            mUser = mAuth.currentUser!!
            initView()
        } else initAnonymousView()

        setOnClick()
    }

    private fun setOnClick() {
        tvHome.setOnClickListener(this)
        tvListTaxi.setOnClickListener(this)
        tvLogOut.setOnClickListener(this)
    }

    private fun initAnonymousView() {
        ivAvatar.loadCircleImage(R.drawable.avatar_placeholder)
        tvUsername.text = getString(R.string.login_now)
        tvEmail.text = getString(R.string.guest)

        tvUsername.setOnClickListener(this)
    }

    private fun initView() {
        ivAvatar.loadCircleImage(mUser.photoUrl)
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
            R.id.tvUsername -> if (activity !is LoginActivity) changeActivity(LoginActivity::class.java)
            R.id.tvHome -> {
                if (activity !is MapActivity) changeActivity(MapActivity::class.java)
                else changeFragment(MapFragment.newInstance())
            }
            R.id.tvListTaxi -> {
//                if (activity is MapActivity) changeActivity()
//                else changeFragment()
            }
            R.id.tvLogOut -> presenter.logout(mAuth)
        }
    }

    fun logoutSuccess() {
        debug("Logout success")
        changeActivity(LoginActivity::class.java)
    }
}
