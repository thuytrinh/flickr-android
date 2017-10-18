package thuy.flickr

import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

open class GetOriginalPhoto @Inject internal constructor(
    private val photoRepository: PhotoRepository
) {
  operator fun invoke(photoId: String): Single<Pair<Photo, PhotoSize>> =
      photoRepository.getPhotoById(photoId)
          .zipWith(
              photoRepository.getOriginalPhotoSize(photoId),
              BiFunction { photo, size -> Pair(photo, size) }
          )
}
