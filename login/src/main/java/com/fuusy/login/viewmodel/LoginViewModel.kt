package com.fuusy.login.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.fuusy.common.base.BaseViewModel
import com.fuusy.common.network.net.StateLiveData
import com.fuusy.login.repo.LoginRepo
import com.fuusy.service.repo.LoginResp
import kotlinx.coroutines.launch

class LoginViewModel(private val repo : LoginRepo) : BaseViewModel() {

    //用户名的绑定
    var userNameInput = ObservableField("")

    //密码的绑定
    var passwordInput = ObservableField("")


    val loginLiveData = StateLiveData<LoginResp>()
    val registerLiveData = StateLiveData<LoginResp>()

    fun login(userName: String, password: String) {

        viewModelScope.launch {
            repo.login(userName, password, loginLiveData)
        }

    }

    fun register(
        userName: String,
        password: String,
        rePassword: String,
    ) {
        viewModelScope.launch {
            repo.register(userName, password, rePassword, registerLiveData)
        }

    }

}