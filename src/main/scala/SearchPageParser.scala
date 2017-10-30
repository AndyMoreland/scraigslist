import java.net.URL

import net.ruippeixotog.scalascraper.browser.Browser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._

class SearchPageParser(browser: Browser, url: String) {
  def resultPages: Seq[URL] =
    (browser.get(url) >> elementList("a.result-title.hdrlnk") >> attr("href"))
      .map(new URL(_))
}
