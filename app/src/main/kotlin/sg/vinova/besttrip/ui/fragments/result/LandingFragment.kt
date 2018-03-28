package sg.vinova.besttrip.ui.fragments.result

import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.fragment_landing.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBActivity
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.exts.*
import sg.vinova.besttrip.model.YourDirection
import sg.vinova.besttrip.model.places.Location
import sg.vinova.besttrip.presenter.result.LandingPresenter
import sg.vinova.besttrip.widgets.dialogs.BErrorDialog
import javax.inject.Inject

class LandingFragment : BaseBFragment(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    @Inject
    lateinit var presenter: LandingPresenter

    companion object {
        fun newInstance(): LandingFragment = LandingFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_landing

    override fun getLeftIcon(): Int = R.drawable.drawer

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        checkIsAdded()
        getBaseBActivity().setUpHideSoftKeyboard(layoutContainer)

        if (presenter.checkPlayServices() == ConnectionResult.SUCCESS) {
            (activity as BaseBActivity).showLoading()
            mGoogleApiClient.apply {
                registerConnectionCallbacks(this@LandingFragment)
                registerConnectionFailedListener(this@LandingFragment)
            }
            presenter.createLocationRequest(mGoogleApiClient)
        } else {
            tvYourLocation.text = ""
            BErrorDialog(context).apply {
                setMessage("Error code: ${presenter.checkPlayServices()}")
                show()
            }
        }

        tvDestination.setOnClickListener({ changeFragment(SearchFragment.newInstance(direction), true) })
    }

    override fun bindPresenter() {
        presenter.bind(this)
    }

    override fun unbindPresenter() {
        presenter.unbind()
    }

    @Inject
    lateinit var mGoogleApiClient: GoogleApiClient

    override fun onConnected(p0: Bundle?) {
        presenter.getMyLocation(mGoogleApiClient)
    }

    override fun onConnectionSuspended(p0: Int) {
        mGoogleApiClient.connect()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        error("Connection failed: ${p0.errorCode} - ${p0.errorMessage}")
    }

    private var direction: YourDirection = YourDirection()

    fun getLocationSuccess(location: Location) {
        info("Your location is: ${location.lat}, ${location.lng}")
        direction.myBLocation = location
        presenter.getAddress(location)
    }

    fun getGeocodeSuccess(address: String?) {
        if (address == null) return
        info(address)
        tvYourLocation.text = address
        getBaseBActivity().hideLoading()
    }
}
