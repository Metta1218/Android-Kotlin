@file:Suppress("DEPRECATION")

package com.example.githubuser_awal.ui.detail
import FollowersFragment
import FollowingFragment
import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.githubuser_awal.R



class PagerAdapter (private val mCtx: Context, fm: FragmentManager, data: Bundle) : FragmentPagerAdapter (fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private var fragmentBundle: Bundle

    init {
        fragmentBundle = data
    }
    companion object {
        @StringRes
        private val TAB_TITTLE = intArrayOf(R.string.tab_text_1,R.string.tab_text_2)
    }
    override fun getCount(): Int = TAB_TITTLE.size

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mCtx.resources.getString(TAB_TITTLE[position])
    }
}
