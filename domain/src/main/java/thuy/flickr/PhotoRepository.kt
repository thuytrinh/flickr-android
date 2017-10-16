package thuy.flickr

import io.reactivex.Flowable

interface PhotoRepository {
  /**
   * Returns a list of the latest public photos uploaded to flickr.
   */
  fun getRecent(): Flowable<List<Photo>>
}
