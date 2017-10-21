package example

import java.sql.DriverManager
import scala.collection.JavaConverters._

import doobie._
import doobie.implicits._
import cats._
import cats.data._
import cats.effect._
import cats.implicits._

case class Country(code: String, name: String, pop: Int, gnp: Option[Double])

object Main extends App {

  val transactor = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", "jdbc:postgresql:world", "postgres", ""
  )

  sql"select name from country"
    .query[String]
    .process
    .take(5)
    .list
    .transact(transactor)
    .unsafeRunSync
    .foreach(println)

  // Use tuples for the mapping
  sql"select code, name, population, gnp from country"
    .query[(String, String, Int, Option[Double])]
    .process
    .take(5)
    .list
    .transact(transactor)
    .unsafeRunSync
    .foreach(println)

  // Use a case-class for the mapping
  sql"select code, name, population, gnp from country"
    .query[Country]
    .process
    .take(5)
    .list
    .transact(transactor)
    .unsafeRunSync
    .foreach(println)

  // If you're running this application as part of a SBT session it will slowly leak meta-space memory
  // and crash after about ~15 runs. Performing this cleanup prevents this.
  // https://github.com/tpolecat/doobie/issues/510
  DriverManager
    .getDrivers
    .asScala
    .foreach(DriverManager.deregisterDriver)

}
