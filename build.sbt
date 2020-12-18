ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "1-SNAPSHOT"
ThisBuild / organization     := "io.doerfler"
ThisBuild / organizationName := "Philipp Dörfler"
ThisBuild / crossPaths       := true

Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val consoleTools = (project in file(".")).settings(
  scalacOptions := Seq("-unchecked", "-deprecation", "-Wvalue-discard", "-Xlint:unused",
    "-language:_", "-encoding", "UTF-8", "-target:jvm-1.8"),
  libraryDependencies += "org.fusesource.jansi" % "jansi" % "2.1.1",
  startYear := Some(2020),
  headerLicenseStyle := HeaderLicenseStyle.SpdxSyntax,
  licenses += ("BSD-3-Clause", new URL("https://opensource.org/licenses/BSD-3-Clause"))
).enablePlugins(AutomateHeaderPlugin)