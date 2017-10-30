import java.io.FileWriter

import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}

object ScraperApp {
  def main(args: Array[String]): Unit = {

    val browser: Browser = JsoupBrowser()

    val searchPage = new SearchPageParser(
      browser,
      "https://sfbay.craigslist.org/search/sfc/apa?nh=3&nh=4&nh=6&nh=12&nh=10&nh=18&nh=21&nh=25&nh=1&min_price=2600&max_price=4100&availabilityMode=0"
    )

    val results =
      searchPage.resultPages.flatMap(new ResultPageParser(browser, _).parse)

    OrgEmitter.emitOrgFile(new FileWriter("/home/andrew/foo.org"), results)
  }
}
