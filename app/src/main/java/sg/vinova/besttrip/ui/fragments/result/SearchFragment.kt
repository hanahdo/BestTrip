package sg.vinova.besttrip.ui.fragments.result

import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.fragment_search.*
import org.jetbrains.anko.design.snackbar
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.adapter.NearbyAdapter
import sg.vinova.besttrip.adapter.SearchAdapter
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.model.YourDirection
import sg.vinova.besttrip.model.autocomplete.Prediction
import sg.vinova.besttrip.model.places.Location
import sg.vinova.besttrip.model.places.Result
import sg.vinova.besttrip.presenter.result.SearchPresenter
import sg.vinova.besttrip.services.BaseListener
import sg.vinova.besttrip.ui.activities.MapActivity
import sg.vinova.besttrip.utils.KeyboardUtils
import sg.vinova.besttrip.utils.LogUtils
import javax.inject.Inject

/**
 * Created by Hanah on 12/7/2017.
 */
class SearchFragment : BaseBFragment() {

    private lateinit var mActivity: MapActivity
    private lateinit var direction: YourDirection
    private lateinit var sAdapter: SearchAdapter
    private lateinit var nAdapter: NearbyAdapter
    private var listNearby: ArrayList<Result> = ArrayList()

    @Inject lateinit var presenter: SearchPresenter

    companion object {
        fun newInstance(direction: YourDirection): SearchFragment {
            val fragment = SearchFragment()
            fragment.direction = direction
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        if (!isAdded) return
        if (activity is MapActivity)
            mActivity = activity as MapActivity

        KeyboardUtils.setUpHideSoftKeyboard(mActivity, layoutContainer)

        mActivity.bToolbar.setToolbarTitle("Search")

        sAdapter = SearchAdapter()
        nAdapter = NearbyAdapter()

        rvSearch.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = sAdapter
        }
        rvNearby.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = nAdapter
        }

        mActivity.showLoading()
        presenter.getNearby(direction.myBLocation!!)

        onClick()
    }

    private fun showNearby() {
        rvSearch.visibility = View.GONE
        rvNearby.visibility = View.VISIBLE
    }

    private fun showSearch() {
        rvSearch.visibility = View.VISIBLE
        rvNearby.visibility = View.GONE
    }

    private fun onClick() {
        sAdapter.listener = object : BaseListener.OnItemClickListener<String> {
            override fun onItemClick(t: String) {
                presenter.getLocationByPlaceId(t)
            }

        }

        nAdapter.listener = object : BaseListener.OnItemClickListener<Location> {
            override fun onItemClick(t: Location) {
                direction.destination = t
                LogUtils.bDebug(this.javaClass, "Destination: ${t.lat}, ${t.lng}")
//                changeFragment(ResultFragment.newInstance(direction), false)
            }

        }

        edtDestination.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                mActivity.showLoading()
                if (p0.isNullOrEmpty()) {
                    showNearby()
                    mActivity.hideLoading()
                }
                presenter.getSearchResult(p0.toString())
                mActivity.hideLoading()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
    }

    override fun bindPresenter() {
        presenter.bind(this)
    }

    override fun unbindPresenter() {
        presenter.unbind()
    }

    fun error(error: String?) {
        LogUtils.bError(this.javaClass, error!!)
        snackbar(view!!, error)
    }

    fun getNearbySuccess(results: ArrayList<Result>?) {
        if (results == null || results.isEmpty()) return
        if (nAdapter.itemCount > 0) nAdapter.clear()
        val list: ArrayList<Result> = ArrayList()
        results.filterTo(list) { it.name != it.address2 }
        showNearby()
        nAdapter.addAll(list)
        nAdapter.notifyDataSetChanged()
        mActivity.hideLoading()
        listNearby = list
    }

    fun getSearchResultSuccess(predictions: ArrayList<Prediction>?) {
        if (predictions == null || predictions.isEmpty()) return
        if (sAdapter.itemCount > 0) sAdapter.clear()
        showSearch()
        sAdapter.addAll(predictions)
        sAdapter.notifyDataSetChanged()
        mActivity.hideLoading()
    }

    fun getLocationByPlaceIdSuccess(location: Location?) {
        if (location == null) return
        direction.destination = location
        LogUtils.bDebug(this.javaClass, "Destination: ${location.lat}, ${location.lng}")
//                changeFragment(ResultFragment.newInstance(direction), false)
    }
}