package thuy.flickr

open internal class PhotoMapper {
  open operator fun invoke(photoEntity: PhotoEntity): Photo = Photo(
      id = photoEntity.id(),
      link = photoEntity.large1024Link,
      title = photoEntity.title()
  )

  private val PhotoEntity.large1024Link
    get() = "https://farm${this.farm()}.staticflickr.com/${this.server()}/${this.id()}_${this.secret()}.jpg"
}
