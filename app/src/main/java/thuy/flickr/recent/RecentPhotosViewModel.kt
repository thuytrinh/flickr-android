package thuy.flickr.recent

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import javax.inject.Inject

class RecentPhotosViewModel @Inject internal constructor() {
  val photoCountText = ObservableField<String>()
  val isPhotoCountVisible = ObservableBoolean()
  val photos: ObservableList<PhotoViewModel> = ObservableArrayList()
  val isLoading = ObservableBoolean()
}
