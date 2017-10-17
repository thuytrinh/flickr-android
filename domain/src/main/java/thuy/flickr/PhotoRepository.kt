package thuy.flickr

import io.reactivex.Flowable

typealias Photos = List<Photo>

interface PhotoRepository {
  /**
   * Returns a list of the latest public photos uploaded to flickr.
   */
  fun getRecent(): Flowable<AsyncResult<Photos>>
}
