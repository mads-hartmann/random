"""
Use this script to apply migration scripts to the active database.
"""

import psycopg2
import glob
import hashlib
from os import path
from config import Config
from enum import Enum


class State(Enum):
    applied = 1
    modified = 2
    missing = 3


def check_state(cursor, filename, checksum):
    cursor.execute('''
        SELECT checksum
        FROM migrations
        WHERE filename = %(filename)s
    ''', {'filename': filename})

    result = cursor.fetchone()
    if result is None:
        return State.missing
    elif result[0] == checksum:
        return State.applied
    else:
        return State.modified


if __name__ == "__main__":
    conn = psycopg2.connect(
        database=Config.DB_NAME,
        user=Config.DB_USER,
        password=Config.DB_PASS,
        host=Config.DB_HOST,
        port=Config.DB_PORT)

    migrations_files = sorted(glob.glob('migrations/*.sql'))

    with conn:
        with conn.cursor() as cursor:
            for migration_file in migrations_files:
                with open(migration_file, 'r', encoding='utf-8') as content_file:
                    filename = path.basename(migration_file)
                    sql = content_file.read()
                    checksum = hashlib.md5(sql.encode('utf-8')).hexdigest()
                    state = check_state(cursor, filename, checksum)

                    if state == State.applied:
                        print('Skipping: {}'.format(filename))

                    elif state == State.modified:
                        print('Aborting: {} has been '.format(filename) +
                              'modified since it was applied')
                        exit(0)

                    elif state == State.missing:
                        print('Applying: {}'.format(filename))
                        cursor.execute(sql)
                        cursor.execute('''
                            INSERT INTO migrations (filename, checksum)
                            VALUES (%(filename)s, %(checksum)s)
                        ''', {
                            'filename': filename,
                            'checksum': checksum
                        })

    print('Done processing {} migrations'.format(len(migrations_files)))
