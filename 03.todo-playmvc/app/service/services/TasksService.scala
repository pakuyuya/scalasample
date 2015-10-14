package service.services

import models.Tasks
import slick.driver.H2Driver.api._

import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
 * Tasksのサービスコンポーネント
 */
trait TasksService {
  /**
   * Taskを取得する
   * @param text 検索キーワード
   */
  def findByText(text: String)(implicit session:Session)  : Seq[TaskDTO]

  /**
   * Taskを追加する
   * @param task
   * @return 新規採番ID
   */
  def add(task: TaskDTO)(implicit session:Session)  : Int

  /**
   * Taskの内容を更新する
   * @param task
   */
  def update(task: TaskDTO)(implicit session:Session)  : Unit

  /**
   * タスクを削除する
   * @param id
   */
  def delete(id: Int)(implicit session:Session)  : Unit
}

/**
 * TaskのDTO
 * @param id
 * @param text
 * @param done
 */
case class TaskDTO(id: Option[Int], text: String, done: Boolean) {

}

/**
 * Taskのサービスコンポーネント実装
 */
class TasksServiceImpl extends TasksService {

  override def findByText(text: String)(implicit session:Session) : Seq[TaskDTO] = {
    var ret:Seq[TaskDTO] = null

    val condText : String = if(text != null) text + "%" else "%"
    lazy val tasks = TableQuery[Tasks]

    val query = tasks.filter(_.text like condText).result
    val f = session.database.run(query)

    f.onSuccess {
      case v => ret = v.map(TaskDTO.tupled)
    }
    Await.ready(f, Duration.Inf)

    ret
  }

  override def add(task: TaskDTO)(implicit session:Session) : Int = {
    lazy val tasks = TableQuery[Tasks]
    var ret : Int = -1

    var action = (tasks returning tasks.map(_.id)) += TaskDTO.unapply(task).get

    val f = session.database.run(action)

    f.onSuccess {
      case v => ret = v
    }

    ret
    // 旧コード
//    val action = tasks.forceInsert(TaskDTO.unapply(task).get)
//    val f = session.database.run(action)
//    Await.ready(f, Duration.Inf)
  }
  override def update(task: TaskDTO)(implicit session:Session) = {
    lazy val tasks = TableQuery[Tasks]
    val action = tasks.update(TaskDTO.unapply(task).get)

    val f = session.database.run(action)
    Await.ready(f, Duration.Inf)
  }
  override def delete(id: Int)(implicit session:Session)  : Unit = {
    lazy val tasks = TableQuery[Tasks]

    val action = tasks.filter(_.id === id).delete

    val f = session.database.run(action)
    Await.ready(f, Duration.Inf)
  }
}
