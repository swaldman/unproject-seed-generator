import mill._
import mill.define._
import mill.scalalib._
import mill.define.Source
import mill.modules.Jvm
import mill.api.Result

// huge thanks to @lolgab onn the Scala discord!
import $file.buildCompilationSettings

import $ivy.`com.mchange::untemplate-mill:0.0.4`
import untemplate.mill._

object generator extends UntemplateModule {
  override def scalaVersion = "3.2.1"

  // supports Scala 3.2.1
  override def ammoniteVersion = "2.5.6"

  // we'll build an index!
  override def untemplateIndexNameFullyQualified : Option[String] = Some("com.mchange.unproject.seed.IndexedUntemplates")

  override def untemplateSelectCustomizer: untemplate.Customizer.Selector = { key =>
    var out = untemplate.Customizer.empty

    // to customize, examine key and modify the customer
    // with out = out.copy=...
    //
    // e.g. out = out.copy(extraImports=Seq("generator.*"))

    out
  }
}


