import java.io.Writer

object OrgEmitter {
  def emitOrgFile(output: Writer, resultPages: Seq[ResultPage]): Unit = {
    output.write("#+COLUMNS: %75Title %5Price %5BR %8sqft %30Neighborhood %8href\n\n")
    output.write("* Results\n")
    resultPages.foreach(emitResultPage(output, _))
  }

  private def emitResultPage(output: Writer, resultPage: ResultPage): Unit = {
    output.write(resultPageToString(resultPage))
  }

  private def resultPageToString(resultPage: ResultPage): String = {
    s"""
      |** [[${resultPage.href}][${resultPage.title}]]
      | :PROPERTIES:
      | :Id: ${resultPage.id}
      | :Title: ${resultPage.title}
      | :Neighborhood: ${resultPage.neighborhood}
      | :Price: ${resultPage.price}
      | :BR: ${resultPage.br.getOrElse("")}
      | :sqft: ${resultPage.sqft.getOrElse("")}
      | :contact: ${resultPage.contact}
      | :href: [[${resultPage.href}][${resultPage.title}]]
      | :END:
      | ${resultPage.body}
    """.stripMargin
  }
}
