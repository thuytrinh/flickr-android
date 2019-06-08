package thuy.flickr

internal open class PhotoMapper {
  open operator fun invoke(entities: List<PhotoEntity>): List<Photo> =
      entities
          .filter { it.thumbnailUrl() != null }
          .map { toPhoto(it) }

  open fun toPhoto(entity: PhotoEntity): Photo = Photo(
      id = entity.id(),
      link = checkNotNull(entity.thumbnailUrl()) {
        "Thumbnail url doesn't exist"
      },
      title = entity.title()
  )

  open fun toPhotoSize(entity: PhotoEntity): PhotoSize = PhotoSize(
      width = 0,
      height = 0,
      link = checkNotNull(entity.originalUrl()) {
        "Original url doesn't exist"
      }
  )

  private fun PhotoEntity.thumbnailUrl() = with(this) {
    url_l() ?: url_o() ?: url_c() ?: url_z() ?: url_n() ?: url_m()
        ?: url_q() ?: url_s() ?: url_t() ?: url_sq()
  }

  private fun PhotoEntity.originalUrl() = with(this) {
    url_o() ?: url_l() ?: url_c() ?: url_z() ?: url_n() ?: url_m()
        ?: url_q() ?: url_s() ?: url_t() ?: url_sq()
  }
}
