package sg.vinova.besttrip.presenter.result

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.support.v4.app.ActivityCompat
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.library.Constant.FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
import sg.vinova.besttrip.library.Constant.UPDATE_INTERVAL_IN_MILLISECONDS
import sg.vinova.besttrip.model.places.Location
import sg.vinova.besttrip.ui.fragments.result.LandingFragment
import sg.vinova.besttrip.usecase.SearchUsecase
import javax.inject.Inject


/**
 * Created by Hanah on 12/3/2017.
 */
class LandingPresenter @Inject constructor(private var context: Context) : BaseBPresenter<LandingFragment>(context) {
    @Inject lateinit var searchUsecase: SearchUsecase
    private lateinit var mLocationRequest: LocationRequest
    private var lastLocation: Location? = null

    fun createLocationRequest(mGoogleApiClient: GoogleApiClient) {
        mLocationRequest = LocationRequest.create()
        mLocationRequest.interval = UPDATE_INTERVAL_IN_MILLISECONDS
        mLocationRequest.fastestInterval = FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        mGoogleApiClient.connect()
    }

    fun getMyLocation(mGoogleApiClient: GoogleApiClient) {
        if (mGoogleApiClient.isConnected) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) return
            requestSubscriptions.add(
                    Observable.just("")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                LocationServices.getFusedLocationProviderClient(context).lastLocation
                                        .addOnSuccessListener({ location -> if (location != null) lastLocation = Location(location.latitude, location.longitude) })
                                        .addOnFailureListener({ exception -> weakReference.get()!!.error(exception.localizedMessage) })
                                if (lastLocation == null)
                                    LocationServices.getFusedLocationProviderClient(context).requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())
                                else
                                    weakReference.get()!!.getLocationSuccess(lastLocation!!)
                            }, { throwable -> weakReference.get()!!.error(throwable.localizedMessage) })
            )

        }
    }

    private val mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult!!.locations
                    .filterNotNull()
                    .forEach { weakReference.get()!!.getLocationSuccess(Location(it.latitude, it.longitude)) }
        }
    }

    fun checkPlayServices(): Int {
        val googleAPI = GoogleApiAvailability.getInstance()
        val result = googleAPI.isGooglePlayServicesAvailable(context)
        return result
    }

    fun getAddress(yourLocation: Location) {
        requestSubscriptions.add(searchUsecase.getAddress("${yourLocation.lat},${yourLocation.lng}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ geocode ->
                    if (geocode.results != null && geocode.results!!.isNotEmpty())
                        weakReference.get()!!.getGeocodeSuccess(geocode.results!![0].address1)
                }))
    }

}
