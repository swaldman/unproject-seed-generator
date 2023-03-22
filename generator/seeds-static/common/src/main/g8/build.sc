import mill._
import mill.define._
import mill.scalalib._
import mill.define.Source
import mill.modules.Jvm
import mill.api.Result

// huge thanks to @lolgab onn the Scala discord!
import \$file.buildCompilationSettings

import \$ivy.`com.mchange::untemplate-mill:$untemplate_version$`
import untemplate.mill._

object $module;format="camel"$ extends UntemplateModule {
  override def scalaVersion = "3.2.1"

  // supports Scala 3.2.1
  override def ammoniteVersion = "2.5.6"

  // we'll build an index!
  override def untemplateIndexNameFullyQualified : Option[String] = Some("$package;format="package"$.IndexedUntemplates")

  override def untemplateSelectCustomizer: untemplate.Customizer.Selector = { key =>
    var out = untemplate.Customizer.empty

    // to customize, examine key and modify the customer
    // with out = out.copy=...
    //
    // e.g. out = out.copy(extraImports=Seq("$module;format="camel"$.*"))

    out
  }
}


