package example

import java.sql.DriverManager
import scala.collection.JavaConverters._

import doobie._
import doobie.implicits._
import cats._
import cats.data._
import cats.effect.IO
import cats.implicits._

case class Child(childId: String)

object Main extends App {

  val transactor = Transactor.fromDriverManager[IO](
    "com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/famly", "root", "root"
  )

  val y = transactor.yolo
  import y._

  def getChild(child: Child) = sql"""
        SELECT gender
        FROM famly_daycare_child_domain_model_child
        WHERE childid = ${child.childId}
  """.query[Child]

  getChild(Child("026ec4bd-b9fc-4685-abed-fcdd653749a5")).checkOutput.unsafeRunSync

  // query
  //   .to[List]
  //   .transact(transactor)
  //   .unsafeRunSync
  //   .take(5)
  //   .foreach(println)


  // // Use tuples for the mapping
  // sql"select code, name, population, gnp from country"
  //   .query[(String, String, Int, Option[Double])]
  //   .process
  //   .take(5)
  //   .list
  //   .transact(transactor)
  //   .unsafeRunSync
  //   .foreach(println)

  // // Use a case-class for the mapping
  // sql"select code, name, population, gnp from country"
  //   .query[Country]
  //   .process
  //   .take(5)
  //   .list
  //   .transact(transactor)
  //   .unsafeRunSync
  //   .foreach(println)

  // If you're running this application as part of a SBT session it will slowly leak meta-space memory
  // and crash after about ~15 runs. Performing this cleanup prevents this.
  // https://github.com/tpolecat/doobie/issues/510
  DriverManager
    .getDrivers
    .asScala
    .foreach(DriverManager.deregisterDriver)

}
