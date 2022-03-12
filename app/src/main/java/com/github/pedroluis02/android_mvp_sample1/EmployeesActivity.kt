package com.github.pedroluis02.android_mvp_sample1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.github.pedroluis02.android_mvp_sample1.databinding.ActivityEmployeesBinding
import com.github.pedroluis02.android_mvp_sample1.model.Company
import com.github.pedroluis02.android_mvp_sample1.model.Employee
import com.github.pedroluis02.android_mvp_sample1.presenter.EmployeesPresenter

class EmployeesActivity : AppCompatActivity(), EmployeesPresenter.View {
    private lateinit var binding: ActivityEmployeesBinding
    private lateinit var presenter: EmployeesPresenter

    private lateinit var adapter: ArrayAdapter<Employee>

    companion object {
        const val COMPANY_ID_EXTRA = "companyId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmployeesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        presenter = EmployeesPresenter(this)
        presenter.companyId = bundle?.get(COMPANY_ID_EXTRA) as String
        presenter.loadCompanyInfo()

        initComponents()
    }

    override fun onResume() {
        super.onResume()


        presenter.loadEmployees()
    }

    private fun initComponents() {
        with(binding) {
            newEmployeeAction.setOnClickListener{ onNewEmployeeClicked() }

            adapter = ArrayAdapter<Employee>(
                this@EmployeesActivity,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
            )
            employeeList.adapter = adapter
            employeeList.setOnItemClickListener{ _, _, index, _ -> onEmployeeItemClicked(index) }
        }
    }

    private fun onNewEmployeeClicked() {
        val intent = Intent(this, NewEmployeeActivity::class.java).apply {
            putExtra(NewEmployeeActivity.COMPANY_ID_EXTRA, presenter.companyId)
        }
        startActivity(intent)
    }

    private fun onEmployeeItemClicked(index: Int) {

    }


    override fun updateCompanyInfo(company: Company) {
        supportActionBar?.subtitle = company.toString()
    }

    override fun updateListView(employees: List<Employee>) {
        adapter.clear()
        adapter.addAll(employees)
        adapter.notifyDataSetChanged()
    }
}