package com.github.pedroluis02.android_mvp_sample1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.github.pedroluis02.android_mvp_sample1.databinding.ActivityMainBinding
import com.github.pedroluis02.android_mvp_sample1.model.Company
import com.github.pedroluis02.android_mvp_sample1.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainPresenter

    private lateinit var adapter: ArrayAdapter<Company>

    //private lateinit var startForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)
        /*startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                presenter.loadCompanies()
            }
        }*/
        initComponents()
    }

    override fun onResume() {
        super.onResume()

        presenter.loadCompanies()
    }

    private fun initComponents() {
        with(binding) {
            newCompanyAction.setOnClickListener{ onNewCompanyClicked() }

            adapter = ArrayAdapter<Company>(
                this@MainActivity,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
            )
           companyList.adapter = adapter
            companyList.setOnItemClickListener{ _, _, index, _ -> onCompanyItemClicked(index) }
        }
    }

    private fun onNewCompanyClicked() {
        val intent = Intent(this, NewCompanyActivity::class.java)
        //startForResult.launch(intent)
        startActivity(intent)
    }

    private fun onCompanyItemClicked(index: Int) {
        val company = adapter.getItem(index)!!
        val intent = Intent(this, EmployeesActivity::class.java).apply {
            putExtra(EmployeesActivity.COMPANY_ID_EXTRA, company.id)
        }

        startActivity(intent)
    }

    override fun updateListView(companies: List<Company>) {
        adapter.clear()
        adapter.addAll(companies)
        adapter.notifyDataSetChanged()
    }

}