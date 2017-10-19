package thuy.flickr

open internal class PhotoMapper {
  open operator fun invoke(entities: List<PhotoEntity>): List<Photo> {
    return entities
        .filter { it.thumbnailUrl() != null }
        .map {
          val thumbnailUrl = it.thumbnailUrl()
          checkNotNull(thumbnailUrl) {
            "Thumbnail url must be specified"
          }
          Photo(id = it.id(), link = thumbnailUrl!!, title = it.title())
        }
  }

  private fun PhotoEntity.thumbnailUrl() = with(this) {
    url_l() ?: url_o() ?: url_c() ?: url_z() ?: url_n() ?: url_m()
        ?: url_q() ?: url_s() ?: url_t() ?: url_sq()
  }
}
