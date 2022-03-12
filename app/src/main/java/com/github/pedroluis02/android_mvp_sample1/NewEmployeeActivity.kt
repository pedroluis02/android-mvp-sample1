package com.github.pedroluis02.android_mvp_sample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.pedroluis02.android_mvp_sample1.databinding.ActivityNewEmployeeBinding
import com.github.pedroluis02.android_mvp_sample1.model.Company
import com.github.pedroluis02.android_mvp_sample1.model.Employee
import com.github.pedroluis02.android_mvp_sample1.presenter.NewEmployeePresenter

class NewEmployeeActivity : AppCompatActivity(), NewEmployeePresenter.View {

    private lateinit var binding: ActivityNewEmployeeBinding
    private lateinit var presenter: NewEmployeePresenter

    companion object {
        const val COMPANY_ID_EXTRA = "companyId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        presenter = NewEmployeePresenter(this)
        presenter.companyId = bundle?.get(COMPANY_ID_EXTRA) as String
        presenter.loadCompanyInfo()

        initComponents()
    }

    private fun initComponents() {
        with(binding) {
            saveAction.setOnClickListener { onSaveClicked() }
        }
    }

    private fun onSaveClicked() {
        with(binding) {
            val id = employeeId.text.trim().toString()
            val name = employeeName.text.trim().toString()
            val email = employeeEmail.text.trim().toString()
            val phone = employeePhone.text.trim().toString()

            if (id.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                return
            }

            val employee = Employee(id, name, email, phone, presenter.companyId)
            presenter.saveEmployee(employee)
        }
    }

    override fun updateCompanyInfo(company: Company) {
        supportActionBar?.subtitle = company.toString()
    }

    override fun onSuccessfulSave() {
        finish()
    }

}