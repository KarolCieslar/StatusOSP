package pl.kcieslar.statusosp.model.service.impl

import android.util.Log
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import pl.kcieslar.statusosp.model.service.FirebaseLogService
import timber.log.Timber
import javax.inject.Inject

class FirebaseLogServiceImpl @Inject constructor() : FirebaseLogService {
    override fun logNonFatalCrash(throwable: Throwable) =
        Firebase.crashlytics.recordException(throwable)

    override fun printStackTrace(throwable: Throwable) {
        Log.e("Error:", throwable.printStackTrace().toString())
    }
}
