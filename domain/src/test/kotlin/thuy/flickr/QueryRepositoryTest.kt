package thuy.flickr

import org.junit.Test

class QueryRepositoryTest {
  private val repo by lazy { QueryRepository() }

  @Test
  fun `initially no query should me emitted`() {
    repo.queries.test().assertNoValues()
  }

  @Test
  fun `query should be Recent if query text is blank`() {
    // Arrange.
    val subscriber = repo.queries.test()

    // Act.
    repo.putQueryText(null)
    repo.putQueryText("")
    repo.putQueryText(" ")

    // Assert.
    subscriber.apply {
      assertNoErrors()
      assertValues(Recent, Recent, Recent)
    }
  }

  @Test
  fun `query should be Search if query text is not blank`() {
    // Arrange.
    val subscriber = repo.queries.test()

    // Act.
    repo.putQueryText("Melbourne")

    // Assert.
    subscriber.apply {
      assertNoErrors()
      assertValue(Search("Melbourne"))
    }
  }
}
