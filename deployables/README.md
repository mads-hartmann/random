# Deployables
This is a collection of starting points when creating small easily
deployable web services in various programming languages.

The intention is to remove some of the friction going from a bit of
running code to have something that is deployable. Convenience is
in focus. If you plan to release to the public you should expect
to have to tweak things quite a bit (deploy on multiple machines,
configure a load-balancer, use a dedicated host for your postgres
database etc).

Each of these starting points take care of the following quite boring,
but important, tasks:

  * Deploying (Using docker-machine)
  * Configuration (Simple environment variables)
  * Logging (???)
  * Error Reporting (Sentry?)
  * Performance measuring (???)
  * Storage (Postgres)

Interesting links:

- https://docs.docker.com/compose/production/ : Talks about using
  docker-compose for production.
