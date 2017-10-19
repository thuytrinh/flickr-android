package thuy.flickr

import io.reactivex.Flowable
import javax.inject.Inject

open class GetPhotos @Inject internal constructor(
    private val photoRepository: PhotoRepository
) {
  open operator fun invoke(queries: Flowable<Query>): Flowable<AsyncResult<Photos>> =
      queries.switchMap {
        when (it) {
          is Search -> photoRepository.search(it.queryText)
          is Recent -> photoRepository.getRecent()
        }
      }
}
