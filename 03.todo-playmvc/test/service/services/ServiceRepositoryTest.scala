package service.services

import org.specs2.Specification
import service.ServiceRepository
import org.specs2.mock._
import org.specs2.specification._

/**
 * Created by yu on 2015/10/14.
 */
class Dummy  extends Specification with Mockito  {
  def is = s2""
  
  class ServiceRepositoryTest extends ServiceRepository  {
    lazy val tasksService = mock[TasksService]
  }
}