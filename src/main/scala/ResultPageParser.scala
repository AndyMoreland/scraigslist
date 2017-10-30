import java.net.URL

import net.ruippeixotog.scalascraper.browser.Browser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._

import scala.util.matching.Regex

class ResultPageParser(browser: Browser, url: URL) {
  private val urlPattern: Regex = ".*/(.*)\\.html".r

  def parse: Option[ResultPage] = {
    try {
      val page = browser.get(url.toString)

      val (br, sqft) = HousingAttributeParser.parse(page >> text(".housing"))

      url.toString match {
        case urlPattern(pageId) =>
          Some(
            ResultPage(
              id = pageId,
              href = url.toString,
              title = page >> text("title"),
              price = page >> text("span.price"),
              br = br,
              sqft = sqft,
              picture = Set.empty,
              body = page >> text("#postingbody"),
              contact = "foo",
              neighborhood = page >> text(".postingtitletext small")
            ))
        case _ => throw new IllegalStateException(s"Invalid url: $url")
      }
    } catch {
      case _: Exception => {
        println(s"Error while fetching: $url")
        None
      }
    }
  }
}

case class ResultPage(id: String,
                      href: String,
                      title: String,
                      price: String,
                      br: Option[String],
                      sqft: Option[String],
                      picture: Set[String],
                      body: String,
                      contact: String,
                      neighborhood: String) {}
