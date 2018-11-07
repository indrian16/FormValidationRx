package io.indrian16.formvalidationrx

import android.util.Patterns
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    companion object {

        const val MIN_USERNAME_ERROR = "Type at least 3 letters"
        const val MAX_USERNAME_ERROR = "Type a maximum of 16 letters"

        const val SAMPLE_EMAIL = "Type like example@domain.com"

        const val MIN_PASSWORD_ERROR = "Password minimum 6 of letters"
        const val MAX_PASSWORD_ERROR = "Password maximum 16 of letters"
    }

    private lateinit var disposable: Disposable

    override fun start() {

        val usernameObservable = view.usernameChange()
                .doOnNext { usernameValidate(it) }
        val emailObservable = view.emailChange()
                .doOnNext { emailValidate(it) }
        val passwordObservable = view.passwordChange()
                .doOnNext { passwordValidate(it) }

        disposable = Observables.combineLatest(

                usernameObservable,
                emailObservable,
                passwordObservable
        ) {

            u, e, p->

            usernameValidate(u) && emailValidate(e) && passwordValidate(p)
        }
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        {

                            if (it) {

                                view.enableRegister()

                            } else {

                                view.disableRegister()
                            }
                        },
                        {view.showError(it)}
                )
    }

    override fun usernameValidate(username: CharSequence): Boolean {

        var valid = true

        when {

            username.length < 3 -> {

                view.showErrorUsername(MIN_USERNAME_ERROR)
                valid = false

            }
            username.length > 16 -> {

                view.showErrorUsername(MAX_USERNAME_ERROR)
                valid = false

            }

            else -> view.hideErrorUsername()
        }

        return valid
    }

    override fun emailValidate(email: CharSequence): Boolean {

        var valid = true

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            view.showErrorEmail(SAMPLE_EMAIL)
            valid = false

        } else {

            view.hideErrorEmail()
        }

        return valid
    }

    override fun passwordValidate(password: CharSequence): Boolean {

        var valid = true

        when {

            password.length < 6 -> {

                view.showErrorPassword(MIN_PASSWORD_ERROR)
                valid = false
            }

            password.length > 16 -> {

                view.showErrorPassword(MAX_PASSWORD_ERROR)
                valid = false
            }

            else -> view.hideErrorPassword()
        }

        return valid
    }

    override fun unSubscribe() {

        disposable.dispose()
    }
}