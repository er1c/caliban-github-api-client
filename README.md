# Caliban GitHub GraphQL API Client

[![Build](https://github.com/er1c/caliban-github-api-client/workflows/build/badge.svg?branch=main)](https://github.com/er1c/caliban-github-api-client/actions?query=branch%3Amain+workflow%3Abuild) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.er1c/caliban-github-api-client_2.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.er1c/caliban-github-api-client_2.13)

Scala GitHub GraphQL API Library based upon [Caliban](https://github.com/ghostdogpr/caliban): *Caliban is a purely functional library for building GraphQL servers and clients in Scala.*

## Usage

The packages are published on Maven Central.

```scala
libraryDependencies += "io.github.er1c" %% "caliban-github-api-client" % "<version>"
```

## Example

```scala
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
```


## Documentation

Links:

- [Website](https://er1c.github.io/caliban-github-api-client/)
- [API documentation](https://er1c.github.io/caliban-github-api-client/api/)

## Contributing

The Scala GitHub GraphQL API Library project welcomes contributions from anybody wishing to participate.  All code or documentation that is provided must be licensed with the same license that Scala GitHub GraphQL API Library is licensed with (Apache 2.0, see [LICENCE](./LICENSE.md)).

People are expected to follow the [Scala Code of Conduct](./CODE_OF_CONDUCT.md) when discussing Scala GitHub GraphQL API Library on GitHub, Gitter channel, or other venues.

Feel free to open an issue if you notice a bug, have an idea for a feature, or have a question about the code. Pull requests are also gladly accepted. For more information, check out the [contributor guide](./CONTRIBUTING.md).

## License

All code in this repository is licensed under the Apache License, Version 2.0.  See [LICENCE](./LICENSE.md).
