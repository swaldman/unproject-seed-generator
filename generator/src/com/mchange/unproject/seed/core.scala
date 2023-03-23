package com.mchange.unproject.seed

import scala.collection.*

case class BuildScInput( extraPrologue : String, dependencies : immutable.SortedMap[String,String] )

val UntemplateBuildScInput = BuildScInput("", immutable.SortedMap.empty)
val UntemplateBuildScText  = build_sc( UntemplateBuildScInput ).text

val UnstaticBuildScExtraPrologue = """val UnstaticVersion = "0.0.4""""
val UnstaticBuidScDependencies   = immutable.SortedMap (
  "Unstatic"               -> """ivy"com.mchange::unstatic:${UnstaticVersion}"""",
  "UnstaticUnstaticZTapir" -> """ivy"com.mchange::unstatic-ztapir:${UnstaticVersion}"""",
)
val UnstaticBuildScInput = BuildScInput( UnstaticBuildScExtraPrologue, UnstaticBuidScDependencies )
val UnstaticBuildScText  = build_sc( UnstaticBuildScInput ).text

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

@main
def rebuildSeeds =
  os.remove.all( SeedsOutDir ) // danger warning this is rm -rf 

  os.makeDir.all( UntemplateSeedOutDir )
  os.copy( from = SeedCommonSrcDir, to = UntemplateSeedOutDir, mergeFolders = true )
  os.copy( from = SeedUntemplateSrcDir, to = UntemplateSeedOutDir, mergeFolders = true )
  os.write( target = SeedUntemplateBuildScFile, data = UntemplateBuildScText )

  os.makeDir.all( UntemplateSeedOutDir )
  os.copy( from = SeedCommonSrcDir, to = UnstaticSeedOutDir, mergeFolders = true )
  os.copy( from = SeedUnstaticSrcDir, to = UnstaticSeedOutDir, mergeFolders = true )
  os.write( target = SeedUnstaticBuildScFile, data = UnstaticBuildScText )






