/*
 * Copyright (c) 2020 the Scala GitHub GraphQL API Library contributors.
 * See the project homepage at: https://er1c.github.io/scala-github-graphql/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package examples.packages

import caliban.client.github.Client
import sttp.client._
import sttp.client.asynchttpclient.zio.{AsyncHttpClientZioBackend, SttpClient}
import sttp.model.Header
import zio._
import zio.console._

object Main extends App {
  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] = {
    import Client._

    val license = {
      import License._
      id ~
        key ~
        name
    }

    val query = Query.licenses {
      license
    }

    val uri = uri"https://api.github.com/graphql"

    SttpClient
      .send(query.toRequest(uri).headers(Header("Authorization", "Bearer " + sys.env("GITHUB_TOKEN"))))
      .map(_.body)
      .absolve
      .tap(res => putStrLn(s"Result: $res"))
      .provideCustomLayer(AsyncHttpClientZioBackend.layer())
      .foldM(ex => putStrLn(ex.toString).as(ExitCode.failure), _ => ZIO.succeed(ExitCode.success))
  }
}