import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-heroku-s3-uploader"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "nl.rhinofly" %% "api-s3" % "2.6.1",
    "commons-io" % "commons-io" % "2.4"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += "Rhinofly Internal Repository" at "http://maven-repository.rhinofly.net:8081/artifactory/libs-release-local"
  )

}
