package io.indrian16.formvalidationrx

import io.reactivex.Observable

interface MainContract {

    interface Presenter {

        fun start()

        fun usernameValidate(username: CharSequence): Boolean
        fun emailValidate(email: CharSequence): Boolean
        fun passwordValidate(password: CharSequence): Boolean

        fun unSubscribe()
    }

    interface View {

        fun usernameChange(): Observable<CharSequence>
        fun emailChange(): Observable<CharSequence>
        fun passwordChange(): Observable<CharSequence>

        fun showErrorUsername(username: CharSequence)
        fun hideErrorUsername()

        fun showErrorEmail(email: CharSequence)
        fun hideErrorEmail()

        fun showErrorPassword(password: CharSequence)
        fun hideErrorPassword()

        fun enableRegister()
        fun disableRegister()

        fun showError(throwable: Throwable)
    }
}