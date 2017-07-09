import os


class Config(object):
    DEBUG = os.environ['DEBUG']
    DB_NAME = os.environ['DB_NAME']
    DB_USER = os.environ['DB_USER']
    DB_PASS = os.environ['DB_PASS']
    DB_HOST = os.environ['DB_HOST']
    DB_PORT = os.environ['DB_PORT']
