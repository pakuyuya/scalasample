package facade.facades

import dtos.TaskDTO
import service.services.TasksService
import slick.driver.JdbcProfile

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.util.{Success, Failure}

/**
 * Created by yu on 2015/10/11.
 */

class TasksFacade(implicit tasksService: TasksService) {

  /**
   * タスクを検索する
   * 
   * @param text
   * @param db
   * @return
   */
  def search(text: Option[String] = None)(implicit db:JdbcProfile#Backend#Database): Seq[TaskDTO] = {
    val f = tasksService.findByText(text)
    Await.ready(f, Duration.Inf)

    f.value match {
      case Some(Success(v)) => v
      case Some(Failure(e)) => throw e
      case _                => throw new java.lang.RuntimeException("Futureの結果が想定外")
    }
  }

  def add(task: TaskDTO)(implicit db:JdbcProfile#Backend#Database): Unit = {
    val f = tasksService.add(task)

    Await.ready(f, Duration.Inf)

    f.value match {
      case Some(Failure(e)) => throw e
      case _                =>
    }
  }

  def update(task: TaskDTO)(implicit db:JdbcProfile#Backend#Database): Unit = {
    val f =tasksService.update(task)

    Await.ready(f, Duration.Inf)

    f.value match {
      case Some(Failure(e)) => throw e
      case _                =>
    }
  }

  def remove(id: Int)(implicit db:JdbcProfile#Backend#Database): Unit = {
    val f = tasksService.delete(id)

    Await.ready(f, Duration.Inf)

    f.value match {
      case Some(Failure(e)) => throw e
      case _                =>
    }
  }
}
