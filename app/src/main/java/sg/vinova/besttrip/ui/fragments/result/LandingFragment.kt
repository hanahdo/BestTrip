package sg.vinova.besttrip.ui.fragments.result

import android.app.Dialog
import android.app.ProgressDialog
import android.location.Location
import android.os.Bundle
import android.view.View
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.fragment_landing.*
import org.jetbrains.anko.design.snackbar
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.model.BLocation
import sg.vinova.besttrip.model.YourDirection
import sg.vinova.besttrip.presenter.result.LandingPresenter
import sg.vinova.besttrip.services.BaseListener
import sg.vinova.besttrip.ui.activities.MapActivity
import sg.vinova.besttrip.utils.KeyboardUtils
import sg.vinova.besttrip.utils.LogUtils
import sg.vinova.besttrip.widgets.dialogs.BErrorDialog
import javax.inject.Inject

/**
 * Created by Hanah on 12/3/2017.
 */
class LandingFragment : BaseBFragment(), View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, BaseListener.OnToolbarClickListener {
    private lateinit var mActivity: MapActivity

    @Inject lateinit var presenter: LandingPresenter

    companion object {
        fun newInstance(): LandingFragment = LandingFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_landing

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        if (!isAdded) return
        if (activity is MapActivity)
            mActivity = activity as MapActivity

        KeyboardUtils.setUpHideSoftKeyboard(mActivity, layoutContainer)

        mActivity.bToolbar.setLeftIcon(R.drawable.drawer)

        if (presenter.checkPlayServices() == ConnectionResult.SUCCESS) {
            mActivity.showLoading()
            mGoogleApiClient.registerConnectionCallbacks(this)
            mGoogleApiClient.registerConnectionFailedListener(this)
            presenter.createLocationRequest(mGoogleApiClient)
        } else {
            tvYourLocation.text = ""
            BErrorDialog(context).setMessage("Error code: ${presenter.checkPlayServices()}")!!.show()
        }

        onClick()
    }

    private fun onClick() {
        mActivity.bToolbar.listener = this
        tvDestination.setOnClickListener(this)
    }

    override fun onLeftClick() {
        mActivity.showMenu()
    }

    override fun onRightClick() {

    }

    override fun bindPresenter() {
        presenter.bind(this)
    }

    override fun unbindPresenter() {
        presenter.unbind()
    }

    override fun onClick(v: View?) {
        if (v == null) return

        when (v.id) {
            R.id.tvDestination -> {
//                changeFragment(SearchFragment.newInstance(mGoogleApiClient, yourLocation), true)
            }
        }
    }

    @Inject lateinit var mGoogleApiClient: GoogleApiClient

    override fun onConnected(p0: Bundle?) {
        presenter.getMyLocation(mGoogleApiClient)
    }

    override fun onConnectionSuspended(p0: Int) {
        mGoogleApiClient.connect();
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        LogUtils.bError(this.javaClass, "Connection failed: ${p0.errorCode} - ${p0.errorMessage}")
    }

    private var direction: YourDirection = YourDirection()
    private var yourLocation: BLocation = BLocation()

    fun getLocationSuccess(location: Location) {
        LogUtils.bInfo(this.javaClass, "Your location is: ${location.latitude}, ${location.longitude}")
        yourLocation.lat = location.latitude
        yourLocation.long = location.longitude
        direction.myBLocation = yourLocation
        presenter.getAddress(yourLocation)
    }

    fun getGeocodeSuccess(address: String?) {
        if (address == null) return
        LogUtils.bInfo(this.javaClass, address)
        tvYourLocation.text = address
        mActivity.hideLoading()
    }

    fun error(localizedMessage: String?) {
        LogUtils.bError(this.javaClass, localizedMessage!!)
        snackbar(view!!, localizedMessage)
    }
}
