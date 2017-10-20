package thuy.flickr

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.amshove.kluent.shouldContainAll
import org.junit.Test

@Suppress("IllegalIdentifier")
class PhotoRepositoryImplTest {
  private val api: FlickrApi = mock()
  private val photoMapper: PhotoMapper = mock()
  private val photoEntityMapper: PhotoEntityMapper = mock()
  private val photoDao: PhotoDao = mock()
  private val repo by lazy {
    PhotoRepositoryImpl(
        api,
        photoMapper,
        photoEntityMapper,
        photoDao
    )
  }

  @Test
  fun `should perform searching properly`() {
    // Arrange.
    val photoEntities = listOf(
        mock<PhotoEntity> {
          on { id() }.thenReturn("0")
        },
        mock<PhotoEntity> {
          on { id() }.thenReturn("1")
        }
    )
    val photosEntity = mock<PhotosEntity> {
      on { photos() }.thenReturn(photoEntities)
    }
    val photosResponseEntity = mock<PhotosResponseEntity> {
      on { photos() }.thenReturn(photosEntity)
    }
    whenever(api.search(API_KEY, "Sydney"))
        .thenReturn(Single.just(photosResponseEntity))

    val photos = listOf(
        Photo(link = ""),
        Photo(link = "")
    )
    whenever(photoMapper(any())).thenReturn(photos)

    val photoDbEntities = listOf<PhotoDbEntity>(PhotoDbEntity(), PhotoDbEntity())
    whenever(photoEntityMapper.toDbEntities(any()))
        .thenReturn(photoDbEntities)

    // Act.
    val subscriber = repo.search("Sydney").test().apply {
      assertNoErrors()
      assertComplete()
      assertValues(Busy(), Success(photos))
    }

    // Assert.
    repo.memoryCache.shouldContainAll(mapOf(
        "0" to photoEntities[0],
        "1" to photoEntities[1]
    ))
    verify(photoDao).insertAll(same(photoDbEntities))
  }
}
