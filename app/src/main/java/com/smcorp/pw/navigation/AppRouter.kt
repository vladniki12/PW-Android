package com.smcorp.pw.navigation

import android.net.Uri
import android.text.SpannableString
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Command

class AppRouter: Router() {
    private fun executeCommand(command: Command) {
        executeCommands(command)
    }

    fun openUri(uri: Uri){
        executeCommand(UriViewCommand(uri = uri))
    }

    fun infoMessage(message: String, title: String = "") {
        executeCommand(InfoDialogCommand(message, title))
    }

    fun spannableInfoMessage(message: SpannableString) {
        executeCommand(SpannableInfoDialogCommand(message))
    }

    fun errorMessage(message: String) {
        executeCommand(ErrorDialogCommand(message))
    }

    fun successMessage(message: String) {
        //executeCommand(SuccessToastCommand(message))
    }

    fun warningMessage(message: String) {
        executeCommand(WarningToastCommand(message))
    }

    fun newRootActivity(screenKey: String, data: Any? = null) {
        executeCommand(NewRootActivityCommand(screenKey, data))
    }

    fun startActivity(screenKey: String, data: Any? = null) {
        executeCommand(StartActivityCommand(screenKey, data))
    }
}