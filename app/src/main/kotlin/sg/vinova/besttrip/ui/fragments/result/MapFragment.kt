package sg.vinova.besttrip.ui.fragments.result

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.fragment_map.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.exts.checkIsAdded
import sg.vinova.besttrip.exts.debug
import sg.vinova.besttrip.exts.setUpHideSoftKeyboard
import sg.vinova.besttrip.model.autocomplete.Prediction
import sg.vinova.besttrip.presenter.result.MapPresenter
import sg.vinova.besttrip.ui.activities.MapActivity
import javax.inject.Inject

class MapFragment : BaseBFragment(), OnMapReadyCallback {
    private lateinit var mActivity: MapActivity

    @Inject
    lateinit var presenter: MapPresenter

    private var mapFragment: SupportMapFragment? = null

    companion object {
        fun newInstance(): MapFragment = MapFragment()
    }

    override fun getLeftIcon(): Int = R.id.drawer

    override fun getLayoutId(): Int = R.layout.fragment_map

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        checkIsAdded()
        if (activity is MapActivity)
            mActivity = activity as MapActivity

        mActivity.setUpHideSoftKeyboard(layoutContainer)

        if (mapFragment == null)
            mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?

        mapFragment!!.getMapAsync(this)
    }

    override fun bindPresenter() {
        presenter.bind(this)
    }

    override fun unbindPresenter() {
        presenter.unbind()
    }

    override fun onMapReady(mMap: GoogleMap?) {
        mMap!!.uiSettings.setAllGesturesEnabled(true)
    }

    fun getSuccess(prediction: ArrayList<Prediction>?) {
        prediction?.forEach { debug("PlaceID: ${it.placeId} - Des: ${it.description}") }
    }
}
