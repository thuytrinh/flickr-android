package thuy.detektjsonreport

import com.google.gson.Gson
import io.gitlab.arturbosch.detekt.api.Detektion
import io.gitlab.arturbosch.detekt.api.OutputReport

class DangerJsReport : OutputReport() {
  private val gson by lazy { Gson() }

  override val ending: String
    get() = "json"

  override fun render(detektion: Detektion): String? {
    val comments = detektion.findings.values.map {
      it.map { finding ->
        DangerJsComment(
          message = "${finding.id}: ${finding.message}",
          file = finding.location.file,
          line = finding.location.source.line
        )
      }
    }.flatten()
    return gson.toJson(comments)
  }
}

internal data class DangerJsComment(
  val message: String,
  val file: String,
  val line: Int
)
