package thuy.flickr.recent

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import android.net.Uri
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RecentPhotosViewModel @Inject internal constructor() {
  val photoCountText = ObservableField<String>()
  val isPhotoCountVisible = ObservableBoolean()
  val photos: ObservableList<PhotoViewModel> = ObservableArrayList<PhotoViewModel>()
  val isLoading = ObservableBoolean()

  fun loadPhotos() {
    Flowable.just(1)
        .delay(1, TimeUnit.SECONDS, Schedulers.io())
        .flatMap { Flowable.fromIterable(0..99) }
        .map { PhotoViewModel(link = Uri.parse("https://c1.staticflickr.com/7/6055/6301420616_da3cf7c55b_b.jpg")) }
        .toList()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { newPhotos ->
          photos.clear()
          photos.addAll(newPhotos)
        }
  }
}
