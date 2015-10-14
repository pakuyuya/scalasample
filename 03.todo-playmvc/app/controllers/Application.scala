package controllers

import play.api._
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  implicit def get = Action {
    Redirect("/")
  }

  implicit def add = Action {
    Redirect("/")
  }

  implicit def update = Action {
    Redirect("/")
  }

  implicit def delete = Action {
    Redirect("/")
  }
}
