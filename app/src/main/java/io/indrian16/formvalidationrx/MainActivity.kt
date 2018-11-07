package io.indrian16.formvalidationrx

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.jakewharton.rxbinding3.widget.textChanges
import io.indrian16.formvalidationrx.detail.DetailActivity
import io.indrian16.formvalidationrx.util.CommonUtil
import io.indrian16.fromvalidation.R
import io.reactivex.Observable

class MainActivity : AppCompatActivity(), MainContract.View {

    companion object {

        const val EXTRA_ACCOUNT = "account"
    }

    private lateinit var mUsername: TextInputEditText
    private lateinit var mEmail: TextInputEditText
    private lateinit var mPassword: TextInputEditText

    private lateinit var mUsernameTIL: TextInputLayout
    private lateinit var mEmailTIL: TextInputLayout
    private lateinit var mPasswordTIL: TextInputLayout

    private lateinit var mButtonRegister: Button

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        presenter.start()
    }

    private fun initView() {

        mUsername = findViewById(R.id.edt_username)
        mEmail = findViewById(R.id.edt_email)
        mPassword = findViewById(R.id.edt_password)

        mUsernameTIL = findViewById(R.id.til_username)
        mEmailTIL = findViewById(R.id.til_email)
        mPasswordTIL = findViewById(R.id.til_password)

        mButtonRegister = findViewById(R.id.btn_register)

        mButtonRegister.setOnClickListener {

            val account = Account()

            account.username = mUsername.text.toString()
            account.email = mEmail.text.toString()
            account.password = mPassword.text.toString()

            val detail = Intent(this, DetailActivity::class.java)
            detail.putExtra(EXTRA_ACCOUNT, account)
            startActivity(detail)
        }
    }

    override fun usernameChange(): Observable<CharSequence> = mUsername.textChanges()

    override fun emailChange(): Observable<CharSequence> = mEmail.textChanges()

    override fun passwordChange(): Observable<CharSequence> = mPassword.textChanges()


    override fun showErrorUsername(username: CharSequence) {

        mUsernameTIL.helperText = username
    }

    override fun hideErrorUsername() {

        mUsernameTIL.helperText = CommonUtil.GOOD
    }

    override fun showErrorEmail(email: CharSequence) {

        mEmailTIL.helperText = email
    }

    override fun hideErrorEmail() {

        mEmailTIL.helperText = CommonUtil.GOOD
    }

    override fun showErrorPassword(password: CharSequence) {

        mPasswordTIL.helperText = password
    }

    override fun hideErrorPassword() {

        mPasswordTIL.helperText = CommonUtil.GOOD
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun enableRegister() {

        CommonUtil.enableButton(mButtonRegister)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun disableRegister() {

        CommonUtil.disableButton(mButtonRegister)
    }

    override fun showError(throwable: Throwable) {

        Toast.makeText(baseContext, throwable.message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }
}
