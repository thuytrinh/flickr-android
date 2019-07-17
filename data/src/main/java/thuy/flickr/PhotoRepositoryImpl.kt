package thuy.flickr

import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import java.util.concurrent.ConcurrentHashMap

internal class PhotoRepositoryImpl internal constructor(
    private val api: FlickrApi,
    private val photoMapper: PhotoMapper,
    private val photoEntityMapper: PhotoEntityMapper,
    private val photoDao: PhotoDao
) : PhotoRepository {
  internal val memoryCache = ConcurrentHashMap<String, PhotoEntity>()

  override fun search(query: String): Flowable<AsyncResult<Photos>> =
      api.search(API_KEY, query).toPhotos()

  override fun getOriginalPhotoSize(photoId: String): Single<PhotoSize> =
      Single.fromCallable {
        photoMapper.toPhotoSize(checkNotNull(getPhotoEntity(photoId)) {
          "Photo not found"
        })
      }

  override fun getPhotoById(photoId: String): Single<Photo> =
      Single.fromCallable {
        photoMapper.toPhoto(checkNotNull(getPhotoEntity(photoId)) {
          "Photo not found"
        })
      }

  override fun getRecent(): Flowable<AsyncResult<Photos>> =
      api.getRecent(API_KEY).toPhotos()

  private fun getPhotoEntity(photoId: String): PhotoEntity? =
      memoryCache[photoId] ?: photoEntityMapper(photoDao.loadPhotoById(photoId))

  private fun Single<PhotosResponseEntity>.toPhotos(): Flowable<AsyncResult<Photos>> =
      this.toFlowable()
          .map { it.photos()?.photos() ?: emptyList() }
          .doOnNext { it.forEach { memoryCache[it.id()] = it } }
          .doOnNext { photoDao.insertAll(photoEntityMapper.toDbEntities(it)) }
          .map { photoMapper(it) }
          .map<AsyncResult<Photos>> { Success(it) }
          .doOnError { Timber.e(it, "Error getting photos") }
          .onErrorReturn { Failure(it) }
          .startWith(Busy())

  private fun a(): Int {
    return 0;
  }

  private fun b(): Int {
    return 0;
  }

  private fun c(): Int {
    return 0;
  }

  private fun d(): Int {
    return 0;
  }

  private fun e(): Int {
    return 0;
  }

  private fun f(): Int {
    return 0;
  }

  private fun g(): Int {
    return 0;
  }

  private fun h(): Int {
    return 0;
  }

  private fun i(): Int {
    return 0;
  }

  private fun j(): Int {
    return 0;
  }

  private fun k() {
  }

  private fun l(): Int {
    return 0;
  }

  private fun m(): Int {
    return 0;
  }

  private fun o(): Int {
    return 0;
  }
}
