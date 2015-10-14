import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
 * Created by yu on 2015/10/11.
 */
object Main {

  def main(args: Array[String]) {
    printf("a")
    val f : Future[Seq[(String, Int)]] = Future {
      Seq(("1", 1))
    }

    val ff = f.mapTo[Seq[Dmy]]

    Await.ready(ff, Duration.Inf)
    val v:Seq[Dmy] = ff.value.get.get
    v.map( dmy => print(dmy.str))
  }

}

case class Dmy3(str:String, i:Int) {
}
case class Dmy3(str:String, i:Int) {
  def apply(t:Tuple2[String,Int]) {
    new Dmy2(t._1, t._2)
  }
}
