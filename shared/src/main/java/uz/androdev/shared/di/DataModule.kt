package uz.androdev.shared.di

import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

private const val BUCKET = "gs://zikr-61925.appspot.com"

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun getStorageReference() = FirebaseStorage.getInstance().reference
}