package facade.facades

import service.services.TasksService

/**
 * Created by yu on 2015/10/11.
 */
class TasksFacade(implicit tasksService: TasksService) {
  lazy val tasks = tasksService


}
