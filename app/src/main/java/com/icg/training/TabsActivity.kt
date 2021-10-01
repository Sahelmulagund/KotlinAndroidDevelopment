package com.icg.training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.icg.training.adapter.ViewPager2Adapter
import com.icg.training.databinding.ActivityTabsBinding
import kotlinx.android.synthetic.main.activity_tabs.*

class TabsActivity : AppCompatActivity() {
    lateinit var binding:ActivityTabsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setTitle(R.string.app_name)
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.chats))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.status))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.call))
        val tabFragment = TabFragment()
        val bundle = Bundle()
        bundle.putString(Constants.Keys.data, "Chats")
        tabFragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, tabFragment).commit()


        binding.tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        val tabFragment1 = TabFragment()
                        val bundle1 = Bundle()
                        bundle1.putString(Constants.Keys.data, "Chats")
                        tabFragment1.arguments = bundle1
                        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, tabFragment1).commit()
                    }
                    1->{
                        val tabFragment2 = TabFragment()
                        val bundle2 = Bundle()
                        bundle2.putString(Constants.Keys.data, "Status")
                        tabFragment2.arguments = bundle2
                        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, tabFragment2).commit()
                    }
                    2->{
                        val tabFragment3 = TabFragment()
                        val bundle3 = Bundle()
                        bundle3.putString(Constants.Keys.data, "Call")
                        tabFragment3.arguments = bundle3
                        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, tabFragment3).commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

}