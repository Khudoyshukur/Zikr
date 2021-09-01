package uz.androdev.zikr

import android.app.Application
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ZikrApplication: Application() {

    override fun onCreate() {
        super.onCreate()


    }
}