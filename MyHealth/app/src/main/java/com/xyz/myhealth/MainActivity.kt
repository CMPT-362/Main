package com.xyz.myhealth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var HomeFragment: HomeFragment
    private lateinit var ExerciseFragment: ExerciseFragment
    private lateinit var StressFragment: StressFragment
    private lateinit var WaterFragment: WaterFragment
    private lateinit var SettingsFragment: SettingsFragment
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var myMyFragmentStateAdapter: MyFragmentStateAdapter
    private lateinit var fragments: ArrayList<Fragment>
    private val tabTitles = arrayOf("Home", "Calorie", "Stress", "H2O", "Config") //Tab titles
    private lateinit var tabConfigurationStrategy: TabConfigurationStrategy
    private lateinit var tabLayoutMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2 = findViewById(R.id.viewpager)
        tabLayout = findViewById(R.id.tab)

        HomeFragment = HomeFragment()
        ExerciseFragment = ExerciseFragment()
        StressFragment = StressFragment()
        WaterFragment = WaterFragment()
        SettingsFragment = SettingsFragment()

        fragments = ArrayList()
        fragments.add(HomeFragment)
        fragments.add(ExerciseFragment)
        fragments.add(StressFragment)
        fragments.add(WaterFragment)
        fragments.add(SettingsFragment)

        myMyFragmentStateAdapter = MyFragmentStateAdapter(this, fragments)
        viewPager2.adapter = myMyFragmentStateAdapter

        tabConfigurationStrategy = TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
            tab.text = tabTitles[position] }
        tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager2, tabConfigurationStrategy)
        tabLayoutMediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        tabLayoutMediator.detach()
    }
}