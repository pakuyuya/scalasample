package core

import facade.FacadeFactory
import service._

/**
 * Created by yu on 2015/10/24.
 */
trait FactorySupport {
  lazy val facadeFactory = new FacadeFactory(new ServiceRepositoryImpl)
}
