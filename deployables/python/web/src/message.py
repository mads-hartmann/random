from datetime import datetime


class Message(object):

    def __init__(self, message, created=datetime.now()):
        self.message = message
        self.created = created

    def save(self, db_conn):
        with db_conn.cursor() as cursor:
            return cursor.execute('''
                INSERT INTO messages (message, created)
                VALUES (%(message)s, %(created)s)
            ''', {
                'message': self.message,
                'created': self.created
            })

    @classmethod
    def all(self, db_conn, limit=10):
        with db_conn.cursor() as cursor:
            cursor.execute('''
                SELECT message, created
                FROM messages
                ORDER BY created DESC
                LIMIT %(limit)s
            ''', {'limit': limit})
            return [Message(message, created) for (message, created) in cursor]
