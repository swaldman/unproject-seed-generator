package com.mchange.unproject.seed

import scala.collection.*

case class BuildScInput( extraPrologue : String, dependencies : immutable.SortedMap[String,String] )

val UntemplateBuildScInput = BuildScInput("", immutable.SortedMap.empty)
val UntemplateBuildScText  = build_sc( UntemplateBuildScInput ).text

val UnstaticBuildScExtraPrologue = """val UnstaticVersion = "0.0.4""""
val UnstaticBuidScDependencies   = immutable.SortedMap (
  "Unstatic"       -> """ivy"com.mchange::unstatic:${UnstaticVersion}"""",
  "UnstaticZTapir" -> """ivy"com.mchange::unstatic-ztapir:${UnstaticVersion}"""",
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

val UntemplateMergeDir = os.pwd / os.up / UntemplateSeedDirName
val UnstaticMergeDir   = os.pwd / os.up / UnstaticSeedDirName

@main
def rebuildSeeds( merge : Boolean = false ) =
  os.remove.all( SeedsOutDir ) // danger warning this is rm -rf 

  os.makeDir.all( UntemplateSeedOutDir )
  os.copy( from = SeedCommonSrcDir, to = UntemplateSeedOutDir, copyAttributes = true, mergeFolders = true )
  os.copy( from = SeedUntemplateSrcDir, to = UntemplateSeedOutDir, copyAttributes = true, mergeFolders = true )
  os.write( target = SeedUntemplateBuildScFile, data = UntemplateBuildScText )

  os.makeDir.all( UntemplateSeedOutDir )
  os.copy( from = SeedCommonSrcDir, to = UnstaticSeedOutDir, copyAttributes = true, mergeFolders = true )
  os.copy( from = SeedUnstaticSrcDir, to = UnstaticSeedOutDir, copyAttributes = true, mergeFolders = true )
  os.write( target = SeedUnstaticBuildScFile, data = UnstaticBuildScText )

  if merge then
    os.makeDir.all( UntemplateMergeDir )
    os.copy( from = UntemplateSeedOutDir, to = UntemplateMergeDir, replaceExisting = true, copyAttributes = true, mergeFolders = true )
    os.makeDir.all( UnstaticMergeDir )
    os.copy( from = UnstaticSeedOutDir, to = UnstaticMergeDir, replaceExisting = true, copyAttributes = true, mergeFolders = true )





