package controllers

import javax.inject.Inject

import play.api.i18n.{MessagesApi, I18nSupport}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import core.FactorySupport
import dtos.TaskDTO
import play.api._
import play.api.data.Form
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc._
import play.api.data.Forms._
import play.api.db.slick._
import slick.driver.H2Driver.api._
import play.api.Logger

import slick.driver.JdbcProfile


object Application extends FactorySupport
{
  case class TaskForm(id: Option[Int], content: String, done: Boolean)

  // リクエスト -> 入力フォームのマッピング
  val taskForm = Form(
    mapping(
      "id"       -> optional(number),
      "content"  -> nonEmptyText(maxLength = 250),
      "done"     -> boolean
    )(TaskForm.apply)(TaskForm.unapply)
  )

  // 一覧検索form入力値を保持するクラス
  case class TasksForm(content: Option[String])
  // リクエスト -> 入力フォームのマッピング
  val tasksForm = Form(
    mapping(
      "content"  -> optional(text)
    )(TasksForm.apply)(TasksForm.unapply)
  )

  // Facade取得
  lazy val tasksFacade = facadeFactory.tasks
}

class Application @Inject()(val dbConfigProvider: DatabaseConfigProvider,val messagesApi: MessagesApi) extends Controller
   with HasDatabaseConfigProvider[JdbcProfile] with I18nSupport
{
  import Application._

  def index = Action { implicit rs =>

      Logger.debug("run Application#index")

      implicit val db = this.db
      val tasks: Seq[TaskDTO] = tasksFacade.search()

      Ok(views.html.index(tasks))
  }

  implicit def search = Action { implicit rs =>
    taskForm.bindFromRequest.fold(
      error => {
        BadRequest("拒否")
      },
      form => {
        implicit val db = this.db
        val tasks = tasksFacade.search(Some(form.content))
        Ok(views.html.index.render(tasks))
      }
    )
    Redirect("/")
  }

  implicit def add = Action { implicit rs =>
    taskForm.bindFromRequest.fold(
      error => {
        BadRequest("拒否")
      },
      form => {
        implicit val db = this.db
        tasksFacade.add(TaskDTO(None, form.content, false))
        Redirect("/")
      }
    )
  }

  implicit def update = Action { implicit rs =>
    taskForm.bindFromRequest.fold(
      error => {
        BadRequest("拒否")
      },
      form => {
        implicit val db = this.db
        tasksFacade.update(TaskDTO(form.id, form.content, form.done))
        Redirect("/")
      }
    )
  }

  implicit def remove = Action { implicit rs =>
    taskForm.bindFromRequest.fold(
      error => {
        BadRequest("拒否")
      },
      form => {
        implicit val db = this.db
        tasksFacade.remove(form.id.get)
        Redirect("/")
      }
    )
  }
}
