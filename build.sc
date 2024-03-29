import mill._
import mill.define._
import mill.scalalib._
import mill.define.Source
import mill.modules.Jvm
import mill.api.Result

import $meta._

import $ivy.`com.mchange::untemplate-mill:0.1.0`
import untemplate.mill._

object Dependency {
  val CodegenUtil = ivy"com.mchange::codegenutil:0.0.2"
  val OsLib       = ivy"com.lihaoyi::os-lib:0.9.1"
}

object generator extends UntemplateModule {
  override def scalaVersion = "3.2.2"

  // supports Scala 3.2.1
  //override def ammoniteVersion = "2.5.6"

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

  override def ivyDeps = T {
    super.ivyDeps() ++
      Agg (
        Dependency.CodegenUtil,
        Dependency.OsLib,
      )
  }
}


