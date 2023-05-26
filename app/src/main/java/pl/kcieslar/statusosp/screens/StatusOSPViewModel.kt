package pl.kcieslar.statusosp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pl.kcieslar.statusosp.common.snackbar.SnackbarManager
import pl.kcieslar.statusosp.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import pl.kcieslar.statusosp.model.service.FirebaseLogService

open class StatusOSPViewModel(private val logService: FirebaseLogService) : ViewModel() {
    fun launchCatching(snackbar: Boolean = true, invokeOnCompletion: () -> Unit? = {}, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }
                logService.logNonFatalCrash(throwable)
                logService.printStackTrace(throwable)
            },
            block = block
        ).invokeOnCompletion { invokeOnCompletion() }
}
