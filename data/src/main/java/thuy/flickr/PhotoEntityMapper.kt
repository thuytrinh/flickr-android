package thuy.flickr

internal open class PhotoEntityMapper {
  open operator fun invoke(entity: PhotoDbEntity?): PhotoEntity? = entity?.let {
    ImmutablePhotoEntity.builder().apply {
      id(it.id)
      secret(it.secret)
      server(it.server)
      farm(it.farm)
      title(it.title)
      url_l(it.url_l)
      url_o(it.url_o)
      url_c(it.url_c)
      url_z(it.url_z)
      url_n(it.url_n)
      url_m(it.url_m)
      url_q(it.url_q)
      url_s(it.url_s)
      url_t(it.url_t)
      url_sq(it.url_sq)
    }.build()
  }

  open fun toDbEntities(entities: List<PhotoEntity>): List<PhotoDbEntity> =
      entities.map {
        PhotoDbEntity(
            id = it.id(),
            secret = it.secret(),
            server = it.server(),
            farm = it.farm(),
            title = it.title(),
            url_l = it.url_l(),
            url_o = it.url_o(),
            url_c = it.url_c(),
            url_z = it.url_z(),
            url_n = it.url_n(),
            url_m = it.url_m(),
            url_q = it.url_q(),
            url_s = it.url_s(),
            url_t = it.url_t(),
            url_sq = it.url_sq()
        )
      }
}
