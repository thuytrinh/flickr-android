package thuy.flickr

import io.reactivex.Flowable
import javax.inject.Inject

open class GetPhotos @Inject internal constructor(
    private val photoRepository: PhotoRepository
) {
  open operator fun invoke(queries: Flowable<String>): Flowable<AsyncResult<Photos>> =
      queries.flatMap {
        when (it.isNotBlank()) {
          true -> photoRepository.search(it)
          false -> photoRepository.getRecent()
        }
      }
}
