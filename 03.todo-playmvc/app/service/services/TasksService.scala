package service.services

import dtos.TaskDTO
import models.Tables
import models.Tables._
import slick.driver.H2Driver.api._
import slick.driver.JdbcProfile

import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

import play.api.Logger

/**
 * Tasksのサービスコンポーネント
 */
trait TasksService {

  /**
   * Taskを主キー検索
   * @param id
   * @param db
   * @return
   */
  def findByPK(id: Int)(implicit db:JdbcProfile#Backend#Database) : Future[TaskDTO]

  /**
   * テキストに一致するTaskを取得する
   * @param text 検索キーワード
   * @param db
   */
  def findByText(text: Option[String])(implicit db:JdbcProfile#Backend#Database)  : Future[Seq[TaskDTO]]

  /**
   * Taskを追加する
   * @param task
   * @param db
   * @return 新規採番ID
   */
  def add(task: TaskDTO)(implicit db:JdbcProfile#Backend#Database)  : Future[Int]

  /**
   * Taskの内容を更新する
   * @param task
   * @param db
   */
  def update(task: TaskDTO)(implicit db:JdbcProfile#Backend#Database)  : Future[Int]

  /**
   * タスクを削除する
   * @param id
   * @param db
   */
  def delete(id: Int)(implicit db:JdbcProfile#Backend#Database)  : Future[Int]
}

/**
 * Taskのサービスコンポーネント実装
 */
class TasksServiceImpl extends TasksService {

  override def findByPK(id: Int)(implicit db:JdbcProfile#Backend#Database) : Future[TaskDTO] = {

    Logger.debug("run TasksServiceImpl#findByPK()")

    // DBIOActionを生成し、db.run()でSQLにコンパイル・実行。
    val query = Tasks.filter(_.id === id)
                      .result.head
    val f: Future[Tables.Tasks#TableElementType] = db.run(query)

    // Future[Tasks]をFuture[STaskDTO]にmapして返す
    f.map { task => TaskDTO(Some(task.id), task.text, task.done) }
  }

  override def findByText(text: Option[String] = None)(implicit db:JdbcProfile#Backend#Database) : Future[Seq[TaskDTO]] = {

    Logger.info("run TasksServiceImpl#findByText()")

    val condText : String = text match{
      case Some(t) => "%" + t + "%"
      case None    => "%"
    }

    // DBIOActionを生成し、db.run()でSQLにコンパイル・実行。
    val query = Tasks.filter(_.text like condText)
                      .sortBy(row => row.id.asc)
                      .result
    val f:Future[Seq[Tables.Tasks#TableElementType]] = db.run(query)

    // Future[Seq[Tasks]]をFuture[Seq[TaskDTO]]にmapして返す
    f .map { tasks => tasks.map { task => TaskDTO(Some(task.id), task.text, task.done) } }
  }

  override def add(task: TaskDTO)(implicit db:JdbcProfile#Backend#Database) : Future[Int] = {

    Logger.info("run TasksServiceImpl#add()")

    lazy val tasks = Tasks

    // Insert値をモデルに設定
    val row = TasksRow(0, task.text, task.done)

    // DBIOActionの生成。run時にSQLにコンパイルされる
    val action = (tasks returning tasks.map(_.id)) += row

    // 非同期実行し、Future[Int]を返す
    db.run(action)
  }
  override def update(task: TaskDTO)(implicit db:JdbcProfile#Backend#Database) : Future[Int] = {

    Logger.info("run TasksServiceImpl#update()")

    // Update値をモデルに設定
    val row = TasksRow(task.id.get, task.text, task.done)

    // DBIOActionの生成。run時にSQLにコンパイルされる
    val action = Tasks.filter(t => t.id === task.id.get).update(row)

    // 非同期実行し、Future[Int]を返す
    db.run(action)
  }
  override def delete(id: Int)(implicit db:JdbcProfile#Backend#Database)  : Future[Int] = {

    Logger.info("run TasksServiceImpl#delete()")

    // DBIOActionの生成。run時にSQLにコンパイルされる
    val action = Tasks.filter(_.id === id).delete

    // 非同期実行し、Future[Int]を返す
    db.run[Int](action)
  }
}
