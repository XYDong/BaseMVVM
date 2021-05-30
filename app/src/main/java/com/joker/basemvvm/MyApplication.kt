package com.joker.basemvvm

import android.app.Application
import android.content.Context
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDex
import androidx.paging.ExperimentalPagingApi
import com.alibaba.android.arouter.launcher.ARouter
import com.fuusy.common.loadsir.EmptyCallback
import com.fuusy.common.loadsir.ErrorCallback
import com.fuusy.common.loadsir.LoadingCallback
import com.fuusy.common.utils.AppHelper
import com.fuusy.common.utils.KLog
import com.fuusy.login.di.moduleLogin
import com.kingja.loadsir.core.LoadSir
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


@ExperimentalPagingApi
class MyApplication : Application() {

    private val modules = arrayListOf(
         moduleLogin
    )
    override fun onCreate() {
        super.onCreate()
        //内存泄漏检测
        initARouter()
        initLoadSir()
        initKoin()
        AppHelper.init(this.applicationContext)
        KLog.init(BuildConfig.DEBUG)
        //内存泄漏检测
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }
    }

    //koin
    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(modules)
        }
    }


    private fun initLoadSir() {
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback())
            .addCallback(LoadingCallback())
            .addCallback(EmptyCallback())
            .setDefaultCallback(LoadingCallback::class.java)
            .commit()
    }

    private fun initARouter() {
        ARouter.init(this)
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}