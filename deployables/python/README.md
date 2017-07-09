# Deployable Python Example

## Development
You need to have a docker machine on your system. If you don't have
one already you can create one using the following command

    docker-machine create -d virtualbox dev

Start your dev docker machine and switch to it

    docker-machine start dev
    eval "$(docker-machine env dev)"

To build and start the docker-containers run

    docker-compose build
    docker-compose up

To open the app in a browser, run the following

    open "http://$(docker-machine ip dev)"

To get a python REPL run

    docker-compose run web ptpython

## Database
To start the database container separately run

    docker-compose up postgres

To run migrations you can run the following

    docker-compose run web /usr/local/bin/python migrate_db.py

Now you have a deployables database:

    docker-compose run postgres psql -h "$(docker-machine ip dev)" -p 5432 --username=postgres --dbname=deployables

## Deploying to AWS
First off you need to create a docker-machine to deploy to. For AWS
you use the following command:

    docker-machine create --driver amazonec2 \
        --amazonec2-access-key <access-key> \
        --amazonec2-secret-key <secret-key> \
        --amazonec2-instance-type t2.micro \
        --amazonec2-region us-west-2 \
        --amazonec2-zone a \
        --amazonec2-vpc-id <vpc-id> aws-deployables-python

This will create a `t2.micro` for you and configure it to be used as a
docker-machine. However, it seems that the security group that docker
automatically created doesn't open up for incoming HTTP traffic, so
you have to go into the AWS console and open up for that.

Now switch to that docker-machine

    eval "$(docker-machine env aws-deployables-python)"

Now to build and deploy the services for that machine run the following
commands

    docker-compose -f docker-compose.yml -f docker-compose.prod.yml build
    docker-compose -f docker-compose.yml -f docker-compose.prod.yml up -d

This will first rebuild the images and then stop, destroy, and
recreate the services.
