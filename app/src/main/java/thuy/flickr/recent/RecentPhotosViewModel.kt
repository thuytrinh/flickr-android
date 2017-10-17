package thuy.flickr.recent

import android.content.res.Resources
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import thuy.flickr.*
import thuy.flickr.core.BaseViewModel
import javax.inject.Inject

class RecentPhotosViewModel @Inject internal constructor(
    private val photoRepository: PhotoRepository,
    private val photoViewModelMapper: PhotoViewModelMapper,
    private val resources: Resources
) : BaseViewModel() {
  val photoCountText = ObservableField<String>()
  val isPhotoCountVisible = ObservableBoolean()
  val photos: ObservableList<PhotoViewModel> = ObservableArrayList<PhotoViewModel>()
  val isLoading = ObservableBoolean()

  private val onErrorLoadingPhotosRelay = PublishRelay.create<Unit>()
  val onErrorLoadingPhotos: Observable<Unit>
    get() = onErrorLoadingPhotosRelay.autoClear()

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
            is Failure -> {
              onErrorLoadingPhotosRelay.accept(Unit)
              isLoading.set(false)
            }
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
