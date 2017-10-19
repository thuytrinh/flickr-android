package thuy.flickr

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

open class QueryRepository @Inject internal constructor() {
  private val queryTextRelay = BehaviorRelay.create<String>()

  val latestQuery: String? get() = queryTextRelay.value

  val queries: Flowable<Query>
    get() = queryTextRelay
        .map {
          when (it.isBlank()) {
            true -> Recent
            false -> Search(it)
          }
        }
        .toFlowable(BackpressureStrategy.BUFFER)

  open fun putQueryText(query: String?) {
    queryTextRelay.accept(query ?: "")
  }
}
