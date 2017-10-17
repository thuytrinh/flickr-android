package thuy.flickr.recent

import android.content.res.Resources
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import thuy.flickr.*
import javax.inject.Inject

class RecentPhotosViewModel @Inject internal constructor(
    private val photoRepository: PhotoRepository,
    private val photoViewModelMapper: PhotoViewModelMapper,
    private val resources: Resources
) {
  val photoCountText = ObservableField<String>()
  val isPhotoCountVisible = ObservableBoolean()
  val photos: ObservableList<PhotoViewModel> = ObservableArrayList<PhotoViewModel>()
  val isLoading = ObservableBoolean()

  fun loadPhotos() {
    photoRepository.getRecent()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { result ->
          when (result) {
            is Busy -> isLoading.set(true)
            is Success<Photos> -> {
              photos.clear()
              photos.addAll(result.value.map {
                photoViewModelMapper(it)
              })
              isLoading.set(false)
              loadPhotoCount(result)
            }
            is Failure -> isLoading.set(false)
          }
        }
  }

  private fun loadPhotoCount(result: Success<Photos>) = when {
    result.value.isNotEmpty() -> {
      photoCountText.set(resources.getQuantityString(
          R.plurals.xPhotos,
          result.value.size,
          result.value.size
      ))
      isPhotoCountVisible.set(true)
    }
    else -> isPhotoCountVisible.set(false)
  }
}
