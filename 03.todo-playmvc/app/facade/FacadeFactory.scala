package facade

import facade.facades.TasksFacade
import service.ServiceRepository

/**
 * FacadeをDI的に作るファクトリ
 */
class FacadeFactory(serviceRepository: ServiceRepository) {

  // コンストラクタによって渡されたリポジトリを、各Facadeのimplicitなコンストラクタ引数にあてる
  import serviceRepository._

  lazy val tasks = new TasksFacade()
}
