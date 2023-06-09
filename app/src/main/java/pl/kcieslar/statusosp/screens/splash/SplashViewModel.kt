package pl.kcieslar.statusosp.screens.splash

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.kcieslar.statusosp.FIRST_OPEN_STEPS_SCREEN
import pl.kcieslar.statusosp.GROUP_LIST_SCREEN
import pl.kcieslar.statusosp.LOGIN_SCREEN
import pl.kcieslar.statusosp.model.service.AccountService
import pl.kcieslar.statusosp.model.service.FirebaseLogService
import pl.kcieslar.statusosp.screens.StatusOSPViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: FirebaseLogService
) : StatusOSPViewModel(logService) {

    private fun isUserLogged() = FirebaseAuth.getInstance().currentUser != null

    fun handleAppReady(openAndClear: (String) -> Unit) {
        viewModelScope.launch {
            delay(3000)
            if (isUserLogged()) {
                if (accountService.isUserDoneFirstSetup()) {
                    openAndClear(GROUP_LIST_SCREEN)
                } else {
                    openAndClear(FIRST_OPEN_STEPS_SCREEN)
                }
            } else {
                openAndClear(LOGIN_SCREEN)
            }
        }
    }
}