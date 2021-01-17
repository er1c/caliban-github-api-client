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

import com.github.er1c.github.graphql.Client
import sttp.client._
import sttp.client.asynchttpclient.zio.{ AsyncHttpClientZioBackend, SttpClient }
import zio._

object Main extends App {
  import Client._

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] = {
//    val character = {
//      import caliban.client.Client.Character._
//      (name ~
//        nicknames ~
//        origin ~
//        role(
//          Captain.shipName.map(Role.Captain),
//          Engineer.shipName.map(Role.Engineer),
//          Mechanic.shipName.map(Role.Mechanic),
//          Pilot.shipName.map(Role.Pilot)
//        )).mapN(Character)
//    }
//    val query =
//      Queries.characters(None) {
//        character
//      } ~
//        Queries.character("Amos Burton") {
//          character
//        } ~
//        Queries.character("Naomi Nagata") {
//          character
//        }
//    val mutation = Mutations.deleteCharacter("James Holden")
//
//    def send[T](req: Request[Either[CalibanClientError, T], Nothing]): RIO[Console with SttpClient, T] =
//      SttpClient.send(req).map(_.body).absolve.tap(res => putStrLn(s"Result: $res"))
//
//    val uri   = uri"http://localhost:8088/api/graphql"
//    val call1 = send(mutation.toRequest(uri))
//    val call2 = send(query.toRequest(uri, useVariables = true))
//
//    (call1 *> call2)
//      .provideCustomLayer(AsyncHttpClientZioBackend.layer())
//      .exitCode
    ???
  }
}