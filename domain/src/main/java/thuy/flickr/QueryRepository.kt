package thuy.flickr

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

open class QueryRepository @Inject internal constructor() {
  private val queryTextRelay = BehaviorRelay.create<String>()

  open val latestQueryText: String? get() = queryTextRelay.value

  open val queries: Flowable<Query>
    get() = queryTextRelay
        .map {
          when (it.isBlank()) {
            true -> Recent
            false -> Search(it)
          }
        }
        .toFlowable(BackpressureStrategy.BUFFER)

  open fun putQueryText(queryText: String?) {
    queryTextRelay.accept(queryText ?: "")
  }

  private fun a(): Int {
    return 0;
  }

  private fun b(): Int {
    return 0;
  }

  private fun c(): Int {
    return 0;
  }

  private fun d(): Int {
    return 0;
  }

  private fun e(): Int {
    return 0;
  }

  private fun f(): Int {
    return 0;
  }

  private fun g(): Int {
    return 0;
  }

  private fun h(): Int {
    return 0;
  }

  private fun i(): Int {
    return 0;
  }

  private fun j(): Int {
    return 0;
  }

  private fun k(): Int {
    return 0;
  }

  private fun l(): Int {
    return 0;
  }

  private fun m(): Int {
    return 0;
  }

  private fun o(): Int {
    return 0;
  }
}
