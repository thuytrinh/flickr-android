package thuy.flickr

import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import java.util.concurrent.ConcurrentHashMap

internal class PhotoRepositoryImpl internal constructor(
    private val api: FlickrApi,
    private val photoMapper: PhotoMapper
) : PhotoRepository {
  private val memoryCache = ConcurrentHashMap<String, PhotoEntity>()

  override fun search(query: String): Flowable<AsyncResult<Photos>> =
      api.search(API_KEY, query).toPhotos()

  override fun getOriginalPhotoSize(photoId: String): Single<PhotoSize> =
      Single.fromCallable {
        val photoEntity = memoryCache[photoId]
        photoMapper.toPhotoSize(checkNotNull(photoEntity) {
          "Photo not found in memory cache"
        })
      }

  override fun getPhotoById(photoId: String): Single<Photo> =
      Single.fromCallable {
        val photo = memoryCache[photoId]
        photoMapper.toPhoto(checkNotNull(photo) {
          "Photo not found in memory cache"
        })
      }

  override fun getRecent(): Flowable<AsyncResult<Photos>> =
      api.getRecent(API_KEY).toPhotos()

  private fun Single<PhotosResponseEntity>.toPhotos(): Flowable<AsyncResult<Photos>> =
      this.toFlowable()
          .map { it.photos()?.photos() ?: emptyList() }
          .doOnNext { it.forEach { memoryCache[it.id()] = it } }
          .map { photoMapper(it) }
          .map<AsyncResult<Photos>> { Success(it) }
          .doOnError { Timber.e(it, "Error getting recent") }
          .onErrorReturn { Failure(it) }
          .startWith(Busy())
}
