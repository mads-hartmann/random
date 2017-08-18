import os

import pymysql
import pymysql.cursors

class Config(object):
    DEBUG = os.environ.get('DEBUG', False)
    DB_NAME = os.environ.get('DB_NAME', '')
    DB_USER = os.environ.get('DB_USER', 'root')
    DB_PASS = os.environ.get('DB_PASS', 'root')
    DB_HOST = os.environ.get('DB_HOST', 'localhost')
    DB_PORT = os.environ.get('DB_PORT', 3306)


def main():
    conn = pymysql.connect(
        user=Config.DB_USER,
        db=Config.DB_NAME,
        password=Config.DB_PASS,
        host=Config.DB_HOST,
        port=Config.DB_PORT,
        charset='utf8mb4',
        cursorclass=pymysql.cursors.DictCursor
    )

    with conn:
        with conn.cursor() as cursor:
            cursor.execute("SELECT rand() as wat")
            print(cursor.fetchone())


if __name__ == '__main__':
    main()
