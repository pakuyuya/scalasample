package models

import slick.driver.H2Driver.api._

import slick.lifted.{ Tag}

class Tasks(tag: Tag) extends Table[(Option[Int], String, Boolean)](tag, "TASKS"){
  def id  = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def text = column[String]("TEXT")
  def done = column[Boolean]("DONE")

  def * = (id.?, text, done)
}
