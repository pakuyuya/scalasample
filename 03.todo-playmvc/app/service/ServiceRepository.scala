package service

import service.services._

/**
 * Facadeのリポジトリ.
 */
trait ServiceRepository {
  implicit def tasksService : TasksService
}

class ServiceRepositoryImpl extends ServiceRepository {
  lazy val tasksService = new TasksServiceImpl
}
//class ServiceRepositoryTest extends {
//  lazy val tasksService = mock[TasksService]
//}
