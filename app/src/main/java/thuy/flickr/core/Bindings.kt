package thuy.flickr.core

import android.databinding.BindingAdapter
import android.databinding.BindingConversion
import android.net.Uri
import android.view.View
import com.facebook.drawee.view.SimpleDraweeView

object Bindings {
  @BindingConversion
  @JvmStatic
  fun convertBooleanToViewVisibility(value: Boolean): Int =
      if (value) View.VISIBLE else View.GONE

  @BindingAdapter("imageUri")
  @JvmStatic
  fun setImageUri(view: SimpleDraweeView, imageUri: Uri)
      = view.setImageURI(imageUri)
}
