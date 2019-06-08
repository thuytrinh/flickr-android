package thuy.flickr

internal open class PhotoEntityMapper {
  open operator fun invoke(entity: PhotoDbEntity?): PhotoEntity? = entity?.let {
    ImmutablePhotoEntity.builder().apply {
      id(it.id)
      secret(it.secret)
      server(it.server)
      farm(it.farm)
      title(it.title)
      url_l(it.urlL)
      url_o(it.urlO)
      url_c(it.urlC)
      url_z(it.urlZ)
      url_n(it.urlN)
      url_m(it.urlM)
      url_q(it.urlQ)
      url_s(it.urlS)
      url_t(it.urlT)
      url_sq(it.urlSq)
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
        urlL = it.url_l(),
        urlO = it.url_o(),
        urlC = it.url_c(),
        urlZ = it.url_z(),
        urlN = it.url_n(),
        urlM = it.url_m(),
        urlQ = it.url_q(),
        urlS = it.url_s(),
        urlT = it.url_t(),
        urlSq = it.url_sq()
      )
    }
}
