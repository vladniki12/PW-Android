package com.smcorp.pw.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.smcorp.pw.common.extension.showAlertDialog
import com.smcorp.pw.common.extension.showSpannableAlertDialog
import es.dmoral.toasty.Toasty
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.*


abstract class BaseActivityNavigator(private val activity: AppCompatActivity): Navigator {

    override fun applyCommands(commands: Array<Command>) {
        for (command in commands) applyCommand(command)
    }

    private fun applyCommand(command: Command) {
        if (command is Forward) {
            forward(command)
        } else if (command is Replace) {
            replace(command)
        } else if (command is Back) {
            back()
        }  else if (command is ToastCommand) {
            when (command) {
                is InfoToastCommand -> Toasty.info(activity, command.message, Toast.LENGTH_LONG).show()
                is WarningToastCommand -> Toasty.warning(activity, command.message, Toast.LENGTH_LONG).show()
                is SuccessToastCommand -> Toasty.success(activity, command.message, Toast.LENGTH_LONG).show()
                is ErrorToastCommand -> Toasty.error(activity, command.message, Toast.LENGTH_LONG).show()
            }
        } else if (command is DialogCommand) {
            activity.showAlertDialog(command.message, command.title)
        } else if (command is SpannableDialogCommand) {
            activity.showSpannableAlertDialog(command.message)
        } else if( command is ExternalCommand) {
            when (command){
                is UriViewCommand -> activity.startActivity(Intent(Intent.ACTION_VIEW,command.uri))
            }

        } else if (command is NewRootActivityCommand) {
            newRootActivity(command)
        } else {
            Log.e("Cicerone", "Illegal command for this screen: " + command.javaClass.simpleName)
        }
    }

    private fun forward(command: Forward, options: Bundle? = null) {
        val intent = createIntent(command.screen.screenKey, options)
        if (intent == null) {
            unknownScreen()
            return
        }
        activity.startActivity( intent, options)
    }

    private fun replace(command: Replace, options: Bundle? = null) {
        forward(Forward(command.screen), options)
        activity.finish()

    }

    private fun back() {
        activity.finish()
    }

    private fun newRootActivity(command: NewRootActivityCommand, options: Bundle? = null) {
        val intent = createIntent(command.screenKey, command.data)
        if (intent == null) {
            unknownScreen()
            return
        }
        activity.startActivity( intent, options)
        activity.finishAffinity()
    }

    /**
     * Creates Fragment matching `screenKey`.
     *
     * @param screenKey screen key
     * @param data      initialization data
     * @return instantiated fragment for the passed screen key
     */
    protected abstract fun createIntent(screenKey: String, data: Any?): Intent?

    /**
     * Shows system text.
     *
     * @param message text to show
     */
    protected fun showSystemMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Called when we try to back from the root.
     */
    protected fun exit() {
        activity.finish()
    }

    /**
     * Called if we can't create a screen.
     */
    protected fun unknownScreen() {
        throw RuntimeException("Can't create a screen for passed screenKey.")
    }
}