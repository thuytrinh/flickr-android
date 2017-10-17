package thuy.flickr.recent

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import thuy.flickr.PhotoRepository
import javax.inject.Inject

class RecentPhotosViewModel @Inject internal constructor(
    private val photoRepository: PhotoRepository,
    private val photoViewModelMapper: PhotoViewModelMapper
) {
  val photoCountText = ObservableField<String>()
  val isPhotoCountVisible = ObservableBoolean()
  val photos: ObservableList<PhotoViewModel> = ObservableArrayList<PhotoViewModel>()
  val isLoading = ObservableBoolean()

  fun loadPhotos() {
    photoRepository.getRecent()
        .map { it.map { photoViewModelMapper(it) } }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { newPhotos ->
          photos.clear()
          photos.addAll(newPhotos)
        }
  }
}
