package thuy.flickr

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

internal class PhotoRepositoryImpl : PhotoRepository {
  override fun getOriginalPhotoSize(photoId: String): Single<PhotoSize> =
      Single.just(PhotoSize(
          width = 0,
          height = 0,
          link = "https://c1.staticflickr.com/7/6055/6301420616_da3cf7c55b_b.jpg"
      ))

  override fun getPhotoById(photoId: String): Single<Photo> =
      Single.just(Photo(
          id = photoId,
          link = "https://c1.staticflickr.com/7/6055/6301420616_da3cf7c55b_b.jpg"
      ))

  override fun getRecent(): Flowable<AsyncResult<Photos>> =
      Flowable.just(0..99)
          .map {
            it.map {
              Photo(link = "https://c1.staticflickr.com/7/6055/6301420616_da3cf7c55b_b.jpg")
            }
          }
          .map<AsyncResult<Photos>> { Success(it) }
          .delay(2, TimeUnit.SECONDS, Schedulers.io())
          .onErrorReturn { Failure(it) }
          .startWith(Busy())
}
