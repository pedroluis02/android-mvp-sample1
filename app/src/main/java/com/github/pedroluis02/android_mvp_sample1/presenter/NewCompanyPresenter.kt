package com.github.pedroluis02.android_mvp_sample1.presenter

import com.github.pedroluis02.android_mvp_sample1.data.LocalRepository
import com.github.pedroluis02.android_mvp_sample1.model.Company

class NewCompanyPresenter(private val view: View) {

    private val repository = LocalRepository()

    interface View {
        fun onSuccessfulSave()
    }

    fun saveCompany(company: Company) {
        repository.registerCompany(company)
        view.onSuccessfulSave()
    }

}