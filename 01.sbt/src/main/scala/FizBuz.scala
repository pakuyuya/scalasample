object FizBuz {
  def apply(x: Int) = x match {
    case x if x % 15 == 0 => "fizbuz"
    case x if x % 3  == 0 => "fiz"
    case x if x % 5  == 0 => "buz"
    case _ => ""
  }
}
