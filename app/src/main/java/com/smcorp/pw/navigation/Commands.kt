package com.smcorp.pw.navigation

import android.net.Uri
import android.text.SpannableString
import ru.terrakok.cicerone.commands.Command

sealed class ToastCommand(val message: String) : Command

class InfoToastCommand(message: String) : ToastCommand(message)
class ErrorToastCommand(message: String) : ToastCommand(message)
class SuccessToastCommand(message: String) : ToastCommand(message)
class WarningToastCommand(message: String) : ToastCommand(message)

// dialogs
sealed class DialogCommand(val message: String, val title: String = "") : Command
sealed class SpannableDialogCommand(val message: SpannableString) : Command

class ErrorDialogCommand(message: String) : DialogCommand(message)
class InfoDialogCommand(message: String, title: String = "") : DialogCommand(message, title)
class SpannableErrorDialogCommand(message: SpannableString) : SpannableDialogCommand(message)
class SpannableInfoDialogCommand(message: SpannableString) : SpannableDialogCommand(message)

// deep linking
sealed class ExternalCommand : Command

class UriViewCommand(val uri: Uri): ExternalCommand()

// activities
sealed class ActivityCommand(val screenKey: String, val data: Any? = null) : Command

class NewRootActivityCommand(screenKey: String, data: Any? = null): ActivityCommand(screenKey, data)
class StartActivityCommand(screenKey: String, data: Any? = null): ActivityCommand(screenKey, data)