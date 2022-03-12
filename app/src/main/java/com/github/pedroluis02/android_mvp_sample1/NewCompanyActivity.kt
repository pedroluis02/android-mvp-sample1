package com.github.pedroluis02.android_mvp_sample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.pedroluis02.android_mvp_sample1.databinding.ActivityNewCompanyBinding
import com.github.pedroluis02.android_mvp_sample1.model.Company
import com.github.pedroluis02.android_mvp_sample1.presenter.NewCompanyPresenter

class NewCompanyActivity : AppCompatActivity(), NewCompanyPresenter.View {

    private lateinit var binding: ActivityNewCompanyBinding
    private lateinit var presenter: NewCompanyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = NewCompanyPresenter(this)
        initComponents()
    }

    private fun initComponents() {
        with(binding) {
            saveAction.setOnClickListener { onSaveClicked() }
        }
    }

    private fun onSaveClicked() {
        with(binding) {
            val id = companyId.text.trim().toString()
            val name = companyName.text.trim().toString()

            if (id.isEmpty() || name.isEmpty()) {
                return
            }

            val company = Company(id, name)
            try {
                presenter.saveCompany(company)
            } catch (e: Exception) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onSuccessfulSave() {
        //setResult(RESULT_OK)
        finish()
    }

}