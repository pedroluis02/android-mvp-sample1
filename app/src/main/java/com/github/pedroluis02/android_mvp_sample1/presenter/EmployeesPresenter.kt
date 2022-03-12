package com.github.pedroluis02.android_mvp_sample1.presenter

import com.github.pedroluis02.android_mvp_sample1.data.LocalRepository
import com.github.pedroluis02.android_mvp_sample1.model.Company
import com.github.pedroluis02.android_mvp_sample1.model.Employee

class EmployeesPresenter(private val view: View) {

    lateinit var companyId: String
    private val repository = LocalRepository()

    interface View {
        fun updateCompanyInfo(company: Company)
        fun updateListView(employees: List<Employee>)
    }

    fun loadCompanyInfo() {
        val company = repository.findCompany(companyId)!!
        view.updateCompanyInfo(company)
    }

    fun loadEmployees() {
        val employees = repository.getEmployees(companyId)
        view.updateListView(employees)
    }

}