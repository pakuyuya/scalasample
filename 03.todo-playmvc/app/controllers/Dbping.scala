package controllers

import javax.inject.Inject

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.db.slick._
import slick.driver.JdbcProfile
import javax.inject.Inject
import slick.driver.H2Driver.api._
import play.api.i18n.{MessagesApi, I18nSupport}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc._


import models.Tables._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * Created by yu on 2015/11/03.
 */
class Dbping @Inject()(val dbConfigProvider: DatabaseConfigProvider,val messagesApi: MessagesApi) extends Controller
with HasDatabaseConfigProvider[JdbcProfile] with I18nSupport
{
  implicit def index = Action { implicit rs =>

    val task:TasksRow = TasksRow(0, "a", false)
    Await.ready(db.run(Tasks += task), Duration.Inf)

    BadRequest("bad desu")
  }
}
