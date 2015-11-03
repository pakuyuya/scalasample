package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.H2Driver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Tasks.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Tasks
   *  @param id Database column ID SqlType(INTEGER), AutoInc
   *  @param text Database column TEXT SqlType(VARCHAR), Length(255,true)
   *  @param done Database column DONE SqlType(BOOLEAN) */
  case class TasksRow(id: Int, text: String, done: Boolean)
  /** GetResult implicit for fetching TasksRow objects using plain SQL queries */
  implicit def GetResultTasksRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean]): GR[TasksRow] = GR{
    prs => import prs._
    TasksRow.tupled((<<[Int], <<[String], <<[Boolean]))
  }
  /** Table description of table TASKS. Objects of this class serve as prototypes for rows in queries. */
  class Tasks(_tableTag: Tag) extends Table[TasksRow](_tableTag, "TASKS") {
    def * = (id, text, done) <> (TasksRow.tupled, TasksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(text), Rep.Some(done)).shaped.<>({r=>import r._; _1.map(_=> TasksRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(INTEGER), AutoInc */
    val id: Rep[Int] = column[Int]("ID", O.AutoInc)
    /** Database column TEXT SqlType(VARCHAR), Length(255,true) */
    val text: Rep[String] = column[String]("TEXT", O.Length(255,varying=true))
    /** Database column DONE SqlType(BOOLEAN) */
    val done: Rep[Boolean] = column[Boolean]("DONE")
  }
  /** Collection-like TableQuery object for table Tasks */
  lazy val Tasks = new TableQuery(tag => new Tasks(tag))
}
