/*
 * Copyright (c) 2020 the Caliban GitHub GraphQL API Client contributors.
 * See the project homepage at: https://er1c.github.io/caliban-github-api-client/
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

package examples

import caliban.client.github.Client
import sttp.client3._
import sttp.client3.asynchttpclient.zio.AsyncHttpClientZioBackend
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

    AsyncHttpClientZioBackend().flatMap { implicit backend =>
      val uri = uri"https://api.github.com/graphql"
      query
        .toRequest(uri)
        .header("Authorization", s"Bearer ${sys.env("GITHUB_TOKEN")}")
        .send(backend)
        .map(_.body)
        .absolve
        .tap(res => putStrLn(s"Result: $res"))
    }.foldM(ex => putStrLn(ex.toString).as(ExitCode.failure), _ => ZIO.succeed(ExitCode.success))
  }
}
