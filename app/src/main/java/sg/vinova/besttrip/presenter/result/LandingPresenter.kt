package sg.vinova.besttrip.presenter.result

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.library.Constant.FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
import sg.vinova.besttrip.library.Constant.UPDATE_INTERVAL_IN_MILLISECONDS
import sg.vinova.besttrip.model.BLocation
import sg.vinova.besttrip.ui.fragments.result.LandingFragment
import sg.vinova.besttrip.usecase.SearchUsecase
import javax.inject.Inject


/**
 * Created by Hanah on 12/3/2017.
 */
class LandingPresenter @Inject constructor(private var context: Context) : BaseBPresenter<LandingFragment>(context) {
    @Inject lateinit var searchUsecase: SearchUsecase

    fun createLocationRequest(mGoogleApiClient: GoogleApiClient) {
        val mLocationRequest = LocationRequest.create()
        mLocationRequest.interval = UPDATE_INTERVAL_IN_MILLISECONDS
        mLocationRequest.fastestInterval = FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        mGoogleApiClient.connect()
    }

    fun getMyLocation(mGoogleApiClient: GoogleApiClient) {
        if (mGoogleApiClient.isConnected) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) return
            LocationServices.getFusedLocationProviderClient(context).lastLocation.addOnSuccessListener({ location ->
                weakReference!!.get()!!.getLocationSuccess(location)
            })
        }
    }

    fun checkPlayServices(): Boolean {
        val googleAPI = GoogleApiAvailability.getInstance()
        val result = googleAPI.isGooglePlayServicesAvailable(context)
        return result == ConnectionResult.SUCCESS
    }

    fun getAddress(yourLocation: BLocation) {
        requestSubscriptions!!.add(searchUsecase.getAddress("${yourLocation.lat},${yourLocation.long}")
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ geocode ->
                    weakReference!!.get()!!.getGeocodeSuccess(geocode.results!![0].address)
                }))
    }

}