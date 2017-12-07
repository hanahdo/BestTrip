package sg.vinova.besttrip.ui.fragments.result

import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.fragment_map.*
import org.jetbrains.anko.design.snackbar
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.model.autocomplete.Prediction
import sg.vinova.besttrip.presenter.result.MapPresenter
import sg.vinova.besttrip.services.BaseListener
import sg.vinova.besttrip.ui.activities.MapActivity
import sg.vinova.besttrip.utils.KeyboardUtils
import javax.inject.Inject

/**
 * Created by Hanah on 11/27/2017.
 */
class MapFragment : BaseBFragment(), View.OnClickListener, OnMapReadyCallback, BaseListener.OnToolbarClickListener {
    override fun onLeftClick() {
        mActivity.showMenu()
    }

    override fun onRightClick() {

    }

    private lateinit var mActivity: MapActivity

    @Inject lateinit var presenter: MapPresenter

    private var mapFragment: SupportMapFragment? = null

    companion object {
        fun newInstance(): MapFragment = MapFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_map

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        if (!isAdded) return
        if (activity is MapActivity)
            mActivity = activity as MapActivity

        KeyboardUtils.setUpHideSoftKeyboard(mActivity, layoutContainer)

        mActivity.bToolbar.setLeftIcon(R.id.drawer)

        if (mapFragment == null)
            mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?

        mapFragment!!.getMapAsync(this)

        onClick()
    }

    private fun onClick() {
        mActivity.bToolbar.listener = this
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

        }
    }

    override fun onMapReady(mMap: GoogleMap?) {
        mMap!!.uiSettings.setAllGesturesEnabled(true)
    }

    fun getSuccess(prediction: ArrayList<Prediction>?) {

    }

    fun error(status: String?) {
        KeyboardUtils.setUpHideSoftKeyboard(mActivity, layoutContainer)
        snackbar(view!!, status!!)
    }
}
