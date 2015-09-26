object FizBuz {
  def apply(x: Int): String = {
    var ret:String = ""
    if (x % 3 == 0) ret += "fiz"
    if (x % 5 == 0) ret += "buz"
    ret
  }
}
