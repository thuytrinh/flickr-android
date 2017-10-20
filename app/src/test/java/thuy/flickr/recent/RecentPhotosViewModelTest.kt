package thuy.flickr.recent

import android.content.res.Resources
import com.nhaarman.mockito_kotlin.*
import io.reactivex.processors.BehaviorProcessor
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldEqual
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import thuy.flickr.*

@Suppress("IllegalIdentifier")
class RecentPhotosViewModelTest {
  private val photoFlow = BehaviorProcessor.create<AsyncResult<Photos>>()
  private val getPhotos = mock<GetPhotos> {
    on { invoke(any()) }.thenReturn(photoFlow)
  }

  private val photoViewModelMapper = mock<PhotoViewModelMapper>()
  private val resources = mock<Resources>()
  private val schedulerFactory = TestSchedulerFactory()

  private val queryFlow = BehaviorProcessor.create<Query>()
  private val queryRepository = mock<QueryRepository> {
    on { queries }.thenReturn(queryFlow)
  }

  private val viewModel by lazy {
    RecentPhotosViewModel(
        getPhotos,
        photoViewModelMapper,
        resources,
        queryRepository,
        schedulerFactory
    )
  }

  @Test
  fun `should show photo count from photo flow`() {
    // Arrange.
    val photos = listOf(
        Photo(link = "https://www.abc.com"),
        Photo(link = "https://www.xyz.com")
    )
    whenever(resources.getQuantityString(
        any(),
        eq(2),
        eq(2)
    )).thenReturn("2 photos")

    // Act.
    photoFlow.onNext(Success(photos))

    // Assert.
    viewModel.photoCountText.get().shouldEqual("2 photos")
    viewModel.isPhotoCountVisible.get().shouldBeTrue()
  }

  @Test
  fun `should create PhotoViewModels from photo flow`() {
    // Arrange.
    val photoViewModel0 = PhotoViewModel(link = mock())
    val photoViewModel1 = PhotoViewModel(link = mock())
    whenever(photoViewModelMapper(any()))
        .thenReturn(photoViewModel0)
        .thenReturn(photoViewModel1)

    val photos = listOf(
        Photo(link = "https://www.abc.com"),
        Photo(link = "https://www.xyz.com")
    )

    // Act.
    photoFlow.onNext(Success(photos))

    // Assert.
    assertThat(viewModel.photos).containsExactly(
        photoViewModel0,
        photoViewModel1
    )
    viewModel.isLoading.get().shouldBeFalse()
  }

  @Test
  fun `should be busy while loading photos`() {
    // Act.
    photoFlow.onNext(Busy())

    // Assert.
    viewModel.isLoading.get().shouldBeTrue()
  }

  @Test
  fun `should put query text into repo`() {
    viewModel.onQueryTextSubmit("Sydney")
    verify(queryRepository).putQueryText("Sydney")
  }
}
