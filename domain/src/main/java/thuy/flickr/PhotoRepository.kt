package thuy.flickr

import io.reactivex.Flowable
import io.reactivex.Single

typealias Photos = List<Photo>

interface PhotoRepository {
  /**
   * Returns a list of the latest public photos uploaded to flickr.
   */
  fun getRecent(): Flowable<AsyncResult<Photos>>

  fun search(query: String): Flowable<AsyncResult<Photos>>

  fun getPhotoById(photoId: String): Single<Photo>

  fun getOriginalPhotoSize(photoId: String): Single<PhotoSize>
}
