package thuy.flickr

sealed class AsyncResult<T>

class Busy<T> : AsyncResult<T>() {
  override fun equals(other: Any?): Boolean = other is Busy<*>
}

data class Success<T>(val value: T) : AsyncResult<T>()

data class Failure<T>(val error: Throwable) : AsyncResult<T>()
