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

object Dependencies {
  val resolutionRepos = Seq(
    Resolver.mavenLocal,
    "spray repo" at "http://repo.spray.io",
    "Akka Repository" at "http://repo.akka.io/releases/"
  )

  object V {
    val guava        = "19.0-rc1"
    val scalaTest    = "3.0.0-SNAP5"
    // Add versions for your additional libraries here...
  }

  object Libraries {
    // Scala (test only)
    val scalaTest    = "org.scalatest" % "scalatest_2.11" % V.scalaTest % "test"
    val guava        = "com.google.guava" % "guava" % V.guava % "test"
  }
}
