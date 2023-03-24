package com.mchange.unproject.seed

import scala.collection.*

case class BuildScInput( extraPrologue : String, dependencies : immutable.SortedMap[String,String] )
case class DefaultPropertiesInput( defaultName : String, defaultModule : String )

val UntemplateBuildScInput = BuildScInput("", immutable.SortedMap.empty)
val UntemplateBuildScText  = build_sc( UntemplateBuildScInput ).text

val UnstaticBuildScExtraPrologue = """val UnstaticVersion = "0.0.4""""
val UnstaticBuidScDependencies   = immutable.SortedMap (
  "Unstatic"       -> """ivy"com.mchange::unstatic:\${UnstaticVersion}"""",        // we need dollar signs escaped in g8 template files
  "UnstaticZTapir" -> """ivy"com.mchange::unstatic-ztapir:\${UnstaticVersion}"""", // we need dollar signs escaped in g8 template files
)
val UnstaticBuildScInput = BuildScInput( UnstaticBuildScExtraPrologue, UnstaticBuidScDependencies )
val UnstaticBuildScText  = build_sc( UnstaticBuildScInput ).text

val UntemplateReadmeMdText = README_md( "untemplate" ).text
val UnstaticReadmeMdText   = README_md( "unstatic" ).text


val UntemplateDefaultPropertiesInput = DefaultPropertiesInput( "untemplateplay", """$name;format="camel"$""")
val UntemplateDefaultPropertiesText = default_properties( UntemplateDefaultPropertiesInput ).text

val UnstaticDefaultPropertiesInput = DefaultPropertiesInput( "Unstatic Example", """$name$""")
val UnstaticDefaultPropertiesText = default_properties( UnstaticDefaultPropertiesInput ).text

val SeedsOutDir           = os.pwd / "seeds-out"

val UntemplateSeedDirName = "untemplate-seed.g8"
val UntemplateSeedOutDir  = SeedsOutDir / UntemplateSeedDirName

val UnstaticSeedDirName   = "unstatic-seed.g8"
val UnstaticSeedOutDir    = SeedsOutDir / UnstaticSeedDirName

val SeedsStaticSrcDir     = os.pwd / "generator" / "seeds-static"

val SeedCommonSrcDir      = SeedsStaticSrcDir / "common"
val SeedUntemplateSrcDir  = SeedsStaticSrcDir / "untemplate"
val SeedUnstaticSrcDir    = SeedsStaticSrcDir / "unstatic"

val SeedUntemplateBuildScFile = UntemplateSeedOutDir / "src" / "main" / "g8" / "build.sc"
val SeedUnstaticBuildScFile   = UnstaticSeedOutDir / "src" / "main" / "g8" / "build.sc"

val SeedUntemplateReadmeMdFile = UntemplateSeedOutDir / "README.md"
val SeedUnstaticReadmeMdFile   = UnstaticSeedOutDir / "README.md"

val SeedUntemplateDefaultPropertiesFile = UntemplateSeedOutDir / "src" / "main" / "g8" / "default.properties"
val SeedUnstaticDefaultPropertiesFile   = UnstaticSeedOutDir / "src" / "main" / "g8" / "default.properties"

val UntemplateMergeDir = os.pwd / os.up / UntemplateSeedDirName
val UnstaticMergeDir   = os.pwd / os.up / UnstaticSeedDirName

@main
def rebuildSeeds( merge : Boolean = false ) =
  os.remove.all( SeedsOutDir ) // danger warning this is rm -rf 

  os.makeDir.all( UntemplateSeedOutDir )
  os.copy( from = SeedCommonSrcDir, to = UntemplateSeedOutDir, copyAttributes = true, mergeFolders = true )
  os.copy( from = SeedUntemplateSrcDir, to = UntemplateSeedOutDir, copyAttributes = true, mergeFolders = true )
  os.write( target = SeedUntemplateBuildScFile, data = UntemplateBuildScText )
  os.write( target = SeedUntemplateReadmeMdFile, data = UntemplateReadmeMdText )
  os.write( target = SeedUntemplateDefaultPropertiesFile, data = UntemplateDefaultPropertiesText )

  os.makeDir.all( UntemplateSeedOutDir )
  os.copy( from = SeedCommonSrcDir, to = UnstaticSeedOutDir, copyAttributes = true, mergeFolders = true )
  os.copy( from = SeedUnstaticSrcDir, to = UnstaticSeedOutDir, copyAttributes = true, mergeFolders = true )
  os.write( target = SeedUnstaticBuildScFile, data = UnstaticBuildScText )
  os.write( target = SeedUnstaticReadmeMdFile, data = UnstaticReadmeMdText )
  os.write( target = SeedUnstaticDefaultPropertiesFile, data = UnstaticDefaultPropertiesText )

  if merge then
    os.makeDir.all( UntemplateMergeDir )
    os.copy( from = UntemplateSeedOutDir, to = UntemplateMergeDir, replaceExisting = true, copyAttributes = true, mergeFolders = true )
    os.makeDir.all( UnstaticMergeDir )
    os.copy( from = UnstaticSeedOutDir, to = UnstaticMergeDir, replaceExisting = true, copyAttributes = true, mergeFolders = true )





