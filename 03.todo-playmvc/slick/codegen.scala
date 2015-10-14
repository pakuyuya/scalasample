/**
 * Created by yu on 2015/10/05.
 */
import slick.codegen.SourceCodeGenerator

/**
 * DBに繋いでSlickのコードを自動生成するスクリプト<br>
 * <code>run</code>で実行
 *
 */
object SlickCodegen {

  def main(args: Array[String]): Unit = {
    val url = "jdbc:h2:tcp://localhost/~/03.todo-mvc"
    val slickDriver = "slick.driver.H2Driver"
    val jdbcDriver = "org.h2.Driver"
    val outputFolder = "app"
    val pkg = "models"

    val user = "sa"
    val pass = ""

    SourceCodeGenerator.main(
      Array(
        slickDriver,
        jdbcDriver,
        url,
        outputFolder,
        pkg,
        user,
        pass
      )
    )
  }
}