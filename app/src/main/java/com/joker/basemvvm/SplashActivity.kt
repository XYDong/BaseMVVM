package com.joker.basemvvm

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.fuusy.common.base.BaseActivity
import com.fuusy.common.support.Constants
import com.joker.basemvvm.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun initData(savedInstanceState: Bundle?) {
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
        ARouter.getInstance().build(Constants.PATH_LOGIN).navigation()
        finish()
    }

    override fun getLayoutId(): Int = R.layout.activity_splash
}