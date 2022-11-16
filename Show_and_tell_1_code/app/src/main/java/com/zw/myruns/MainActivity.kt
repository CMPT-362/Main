package com.zw.myruns

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )

    private val tabNames = arrayOf("Start", "Exercise", "Settings")
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager2
    private lateinit var fragments : ArrayList<Fragment>
    private lateinit var tabLayoutMediator : TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Util.checkPermissions(this, PERMISSIONS)

        val startFragment = StartFragment()
        val historyFragment = HistoryFragment()
        val settingsFragment = SettingsFragment()

        fragments = ArrayList<Fragment>()
        fragments.add(startFragment)
        fragments.add(historyFragment)
        fragments.add(settingsFragment)

        //Tabs setup
        viewPager = findViewById(R.id.viewpager)
        tabLayout = findViewById(R.id.tab)
        val fsa = MyFragmentStateAdapter(this, fragments) //adapter tells viewpager how to render data
        viewPager.adapter = fsa

        val tabConfigurationStrategy = TabLayoutMediator.TabConfigurationStrategy {
            tab, position -> tab.text = tabNames[position]    //set names of tabs
        }
        tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager, tabConfigurationStrategy) //wire together
        tabLayoutMediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        tabLayoutMediator.detach()
    }

}