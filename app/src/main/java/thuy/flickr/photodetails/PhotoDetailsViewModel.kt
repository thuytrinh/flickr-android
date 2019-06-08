package thuy.flickr.photodetails

import android.databinding.ObservableField
import android.net.Uri
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import thuy.flickr.GetOriginalPhoto
import thuy.flickr.core.BaseViewModel
import javax.inject.Inject

const val KEY_PHOTO_ID = "photoId"

class PhotoDetailsViewModel @Inject internal constructor(
  private val getOriginalPhoto: GetOriginalPhoto
) : BaseViewModel() {
  val title = ObservableField<String>()
  val link = ObservableField<Uri>()

  fun extractPhotoId(arguments: Bundle?) {
    val photoId = arguments?.getString(KEY_PHOTO_ID)
    checkNotNull(photoId) {
      "Must specify photo id"
    }

    getOriginalPhoto(photoId)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe { (photo, size) ->
        title.set(photo.title)
        link.set(Uri.parse(size.link))
      }
      .autoDispose()
  }
}
