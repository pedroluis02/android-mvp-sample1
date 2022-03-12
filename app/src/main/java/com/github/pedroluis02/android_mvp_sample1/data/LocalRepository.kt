package com.github.pedroluis02.android_mvp_sample1.data

import com.github.pedroluis02.android_mvp_sample1.model.Company
import com.github.pedroluis02.android_mvp_sample1.model.Employee

class LocalRepository {

    companion object {
        @JvmStatic
        private var companies: MutableList<Company> = mutableListOf()

        @JvmStatic
        private var employees: MutableList<Employee> = mutableListOf()
    }

    fun registerCompany(company: Company) {
        if (findCompany(company.id) != null) {
            throw CompanyAlreadyExistsException(company.id)
        }

        companies.add(company)
    }

    fun findCompany(id: String) : Company? {
        return companies.find { company ->  company.id == id}
    }

    fun getCompanies() : List<Company> {
        return companies;
    }

    fun registerEmployee(employee: Employee) {
        if (findEmployee(employee.id, employee.companyId) != null) {
            throw EmployeeAlreadyExistsException(employee.id)
        }

        employees.add(employee)
    }

    fun findEmployee(id: String, companyId: String) : Employee? {
        return employees.find { employee -> employee.id == id && employee.companyId == companyId}
    }

    fun getEmployees(companyId: String) : List<Employee> {
        return employees.filterIndexed { _, employee ->  employee.companyId == companyId}
    }

}