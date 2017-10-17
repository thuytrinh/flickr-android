package thuy.flickr.recent

import android.databinding.ObservableField
import javax.inject.Inject

class RecentPhotosViewModel @Inject internal constructor() {
  val photoCountText = ObservableField<String>()
}
