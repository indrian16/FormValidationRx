package io.indrian16.formvalidationrx.detail

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.indrian16.formvalidationrx.Account
import io.indrian16.formvalidationrx.MainActivity
import io.indrian16.fromvalidation.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fetchData()
    }

    @SuppressLint("SetTextI18n")
    private fun fetchData() {

        val account = intent?.extras?.get(MainActivity.EXTRA_ACCOUNT) as Account

        tv_username.text = "Username: ${account.username}"
        tv_email.text = "Email: ${account.email}"
        tv_password.text = "Password: ${account.password}"
    }

    override fun onSupportNavigateUp(): Boolean {

        finish()
        return true
    }
}
