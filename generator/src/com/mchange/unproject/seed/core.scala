package com.mchange.unproject.seed

case class BuildScInput( extraPrologue : String, dependencies : immutable.SortedMap[String,String] )

@main
def buildSeeds =



