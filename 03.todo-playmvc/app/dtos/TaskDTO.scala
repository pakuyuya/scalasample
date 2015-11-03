package dtos

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * タスク
 *
 * @param id
 * @param text
 * @param done
 */
case class TaskDTO(var id: Option[Int], var text: String, var done: Boolean) {
}
