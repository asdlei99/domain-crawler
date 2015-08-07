/*
 * Copyright (c) 2015 soulmachine@gmail.com. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
import sbt._
import Keys._

object BuildSettings {

  // Basic settings for our app
  lazy val basicSettings = Seq[Setting[_]](
    organization  := "me.soulmachine",
    version       := "1.0",
    description   := "A domain name crawler",
    scalaVersion  := "2.11.7",
    scalacOptions := Seq("-deprecation", "-encoding", "utf8"),
    resolvers     ++= Dependencies.resolutionRepos
  )

  // sbt-assembly settings for building a fat jar
  import sbtassembly.AssemblyPlugin.autoImport._
  lazy val sbtAssemblySettings = Seq(

    // Slightly cleaner jar name
    jarName in assembly := { name.value + "-" + version.value + ".jar" },
    
    // Drop these jars
    excludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
      val excludes = Set(
        "jsp-api-2.1-6.1.14.jar",
        "jsp-2.1-6.1.14.jar",
        "jasper-compiler-5.5.12.jar",
        "commons-beanutils-core-1.8.0.jar", 
        "commons-beanutils-1.7.0.jar",
        "servlet-api-2.5-20081211.jar",
        "servlet-api-2.5.jar"
      ) 
      cp filter { jar => excludes(jar.data.getName) }
    },

    mergeStrategy in assembly <<= (mergeStrategy in assembly) {
      (old) => {
        case x if x.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
        case x if x.startsWith("META-INF") => MergeStrategy.discard // Bumf
        case x if x.endsWith(".html") => MergeStrategy.discard // More bumf
        case PathList("org", "apache", xs @ _*) => MergeStrategy.first
        case PathList("org", "jboss", xs @ _*) => MergeStrategy.first
        case x => old(x)
      } 
    }
  )

  lazy val buildSettings = basicSettings ++ sbtAssemblySettings
}
