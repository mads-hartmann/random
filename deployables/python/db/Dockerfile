FROM postgres:9.4

# The postgres docker image will run any *.sh or *.sql files in the
# /docker-entrypoint-initdb.d folder
COPY init.sql /docker-entrypoint-initdb.d
