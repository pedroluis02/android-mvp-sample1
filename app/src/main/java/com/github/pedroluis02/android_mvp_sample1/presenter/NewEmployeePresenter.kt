package com.github.pedroluis02.android_mvp_sample1.presenter

import com.github.pedroluis02.android_mvp_sample1.data.LocalRepository
import com.github.pedroluis02.android_mvp_sample1.model.Company
import com.github.pedroluis02.android_mvp_sample1.model.Employee

class NewEmployeePresenter(private val view: View) {

    lateinit var companyId: String
    private val repository = LocalRepository()

    interface View {
        fun updateCompanyInfo(company: Company)
        fun onSuccessfulSave()
    }

    fun loadCompanyInfo() {
        val company = repository.findCompany(companyId)!!
        view.updateCompanyInfo(company)
    }

    fun saveEmployee(employee: Employee) {
        repository.registerEmployee(employee)
        view.onSuccessfulSave()
    }

}