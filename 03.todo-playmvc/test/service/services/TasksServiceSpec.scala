package service.services

import org.specs2.Specification
import service.services.TasksServiceImpl

/**
 * Created by yu on 2015/10/14.
 */
class TasksServiceSpec extends Specification {


  lazy val tasksService = new TasksServiceImpl

  def is = s2"""
    Tasks schema IO.

    get first row

    """

}
