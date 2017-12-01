package sg.vinova.besttrip.ui.fragments

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.fragment_map.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.adapter.SearchAdapter
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.model.search.Result
import sg.vinova.besttrip.presenter.MapPresenter
import sg.vinova.besttrip.ui.activities.MapActivity
import sg.vinova.besttrip.utils.KeyboardUtils
import sg.vinova.besttrip.widgets.dialogs.BDialog
import javax.inject.Inject

/**
 * Created by Hanah on 11/27/2017.
 */
class MapFragment : BaseBFragment(), View.OnClickListener, OnMapReadyCallback, TextWatcher {
    private lateinit var mActivity: MapActivity

    @Inject lateinit var presenter: MapPresenter

    private var mapFragment: SupportMapFragment? = null
    private var adapter: SearchAdapter? = null

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

        if (mapFragment == null)
            mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?

        mapFragment!!.getMapAsync(this)

        rvSearch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        if (adapter == null)
            adapter = SearchAdapter()
        rvSearch.adapter = adapter

        onClick()
    }

    private fun onClick() {
        edtPlaces.setOnClickListener(this)
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
            R.id.edtPlaces -> edtPlaces.addTextChangedListener(this)
        }
    }

    override fun onMapReady(mMap: GoogleMap?) {
        mMap!!.uiSettings.setAllGesturesEnabled(true)
    }

    override fun afterTextChanged(p0: Editable?) {
        if (p0.isNullOrEmpty()) return
        presenter.getLocationList(p0!!.toString())
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    fun getSuccess(results: ArrayList<Result>?) {
        rvSearch.visibility = View.VISIBLE
        adapter!!.addAll(results!!)
    }

    fun error() {
        BDialog(context).setMessage("Nothing to show")!!.show()
    }
}