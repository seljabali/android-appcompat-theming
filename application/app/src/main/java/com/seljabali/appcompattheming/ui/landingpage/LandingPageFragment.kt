package com.seljabali.appcompattheming.ui.landingpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment
import com.seljabali.appcompattheming.R
import com.seljabali.appcompattheming.ui.HomeActivity
import kotlinx.android.synthetic.main.fragment_landing_page.*

class LandingPageFragment : BaseFragment() {

    companion object {
        val TAG: String = LandingPageFragment::class.java.simpleName
        fun newInstance(): LandingPageFragment =
            LandingPageFragment()
    }

    lateinit var adapter: LandingPageAdapter

    override fun getToolbarTitle(): String = getString(R.string.theme_selector)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(
        R.layout.fragment_landing_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LandingPageAdapter {
            onPageItemClicked(it)
        }
        adapter.setLandingPageItems(LandingPageItems.values() as Array<LandingItem>)
        landingPageGridView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        adapter.clearSubscriptions()
    }

    override fun onResume() {
        super.onResume()
        adapter.renewSubscriptions()
    }

    private fun onPageItemClicked(landingPageItem: LandingItem) {
        val homeActivity = baseActivity as HomeActivity
        when (landingPageItem) {
            LandingPageItems.DESIGN -> homeActivity.showDesignFragment()
            LandingPageItems.WIDGETS -> homeActivity.showWidgetCatalogue()
        }
        return
    }

}