package thuy.flickr.core

import android.databinding.BindingConversion
import android.view.View

object Bindings {
  @BindingConversion
  @JvmStatic
  fun convertBooleanToViewVisibility(value: Boolean): Int =
      if (value) View.VISIBLE else View.GONE
}
