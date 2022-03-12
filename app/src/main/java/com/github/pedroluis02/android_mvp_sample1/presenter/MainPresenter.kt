package com.github.pedroluis02.android_mvp_sample1.presenter

import com.github.pedroluis02.android_mvp_sample1.data.LocalRepository
import com.github.pedroluis02.android_mvp_sample1.model.Company

class MainPresenter(private val view: View) {

    private val repository = LocalRepository()

    interface View {
        fun updateListView(companies: List<Company>)
    }

    fun loadCompanies() {
        val companies = repository.getCompanies()
        view.updateListView(companies)
    }

}