package thuy.flickr.recent

import android.net.Uri
import java.util.*

data class PhotoViewModel(
    val id: String = UUID.randomUUID().toString(),
    val link: Uri,
    val title: String? = null
)
