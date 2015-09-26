import org.specs2._

class FizBuzSpec extends Specification  {def is = s2"""

 This is my first specification
   it is working                 $ok
   really working!               $ok
                                 """

	  def e1 = FizBuz(1) must beEqualTo("")
	  def e2 = FizBuz(3) must beEqualTo("fiz")
	  def e3 = FizBuz(15) must beEqualTo("fizbuz")
}