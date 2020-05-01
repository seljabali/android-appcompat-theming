package com.seljabali.templateapplication.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.LinearLayout
import androidx.annotation.StyleRes
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.preference.PreferenceManager
import com.seljabali.core.BaseActivity
import com.seljabali.core.BaseFragment
import com.seljabali.design.landingpage.DesignLandingPageFragment
import com.seljabali.templateapplication.R
import com.seljabali.templateapplication.ui.landingpage.LandingPageFragment
import com.seljabali.widgets.landing.WidgetsLandingFragment

class HomeActivity : BaseActivity(), WidgetsLandingFragment.WidgetsLandingFragmentViewer,
    DesignLandingPageFragment.DesignLandingFragmentViewer {

    companion object {
        const val PREFERENCES_THEME_KEY = "theme"
    }

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getSavedTheme())
        setContentView(R.layout.activity_home)
        setupToolbar()
        showLandingPage()
    }

    override fun onBackStackChanged() {
        super.onBackStackChanged()
        val supportActionBar = supportActionBar ?: return
        val isAtHomePage: Boolean = supportFragmentManager.backStackEntryCount < 1
        supportActionBar.setDisplayHomeAsUpEnabled(!isAtHomePage)
        if (isAtHomePage) {
            setToolBarTitle(getString(R.string.app_name))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }

    override fun showFragment(baseFragment: BaseFragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .add(R.id.frameLayout, baseFragment)
            .addToBackStack(tag)
            .commit()
    }

    override fun showFragment(baseFragment: androidx.fragment.app.Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .add(R.id.frameLayout, baseFragment)
            .addToBackStack(tag)
            .commit()
    }

    override fun setAppTheme(@StyleRes themeId: Int) {
        saveUserTheme(themeId)
        setTheme(themeId)
        supportFragmentManager.popBackStack(0, POP_BACK_STACK_INCLUSIVE)
        recreate()
    }

    fun showWidgetCatalogue() {
        val widgetsLandingFragment = WidgetsLandingFragment.newInstance()
        widgetsLandingFragment.setWidgetsLandingFragmentViewer(this)
        showFragment(widgetsLandingFragment, WidgetsLandingFragment.TAG)
    }

    fun showDesignFragment() {
        val designLandingPageFragment = DesignLandingPageFragment.newInstance()
        designLandingPageFragment.setDesignLandingFragmentViewer(this)
        showFragment(designLandingPageFragment, DesignLandingPageFragment.TAG)
    }

    fun setToolBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun setupToolbar() {
        val appBar = findViewById<LinearLayout>(R.id.appToolbar)
        toolbar = appBar.findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        showBackButton(false)
    }

    private fun showLandingPage() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, LandingPageFragment.newInstance())
            .commit()
    }

    private fun saveUserTheme(@StyleRes themeId: Int) {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = preferences.edit()
        editor.putInt(PREFERENCES_THEME_KEY, themeId)
        editor.apply()
    }

    private fun getSavedTheme(): Int =
        PreferenceManager
            .getDefaultSharedPreferences(this)
            .getInt(PREFERENCES_THEME_KEY, com.seljabali.design.R.style.Theme_Tokyo)

}
