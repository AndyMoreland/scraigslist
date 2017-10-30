object HousingAttributeParser {
  private val both = ".*(\\d)br - (\\d+)ft.*".r
  private val sqft = ".*?(\\d+)ft.*".r
  private val br = ".*(\\d)br -.*".r

  private val brWithSqft = "/ 1br - 672ft"
  private val brWithNoSqft = "/ 2br - "
  private val sqftWithNoBr = "/ 450ft"

  def parse(input: String): (Option[String], Option[String]) = {
    input match {
      case both(br, sqft) => (Some(br), Some(sqft))
      case sqft(sqft) => (None, Some(sqft))
      case br(br) => (Some(br), None)
    }
  }

  def main(args: Array[String]): Unit = {
    println(parse(brWithSqft))
    println(parse(brWithNoSqft))
    println(parse(sqftWithNoBr))
  }
}
