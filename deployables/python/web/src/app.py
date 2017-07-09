from flask import Flask
from flask import request, render_template, g
from config import Config
import psycopg2
from message import Message

app = Flask(__name__)
app.config.from_object(Config)


@app.before_request
def before_request():
    g.db = psycopg2.connect(
        database=Config.DB_NAME,
        user=Config.DB_USER,
        password=Config.DB_PASS,
        host=Config.DB_HOST,
        port=Config.DB_PORT)


@app.teardown_request
def teardown_request(exception):
    g.db.close()


@app.route('/', methods=['GET', 'POST'])
def index():
    messages = Message.all(g.db, limit=20)
    texts = [m.message for m in messages]
    app.logger.debug(messages)

    if request.method == 'POST':
        messages.append(request.form['text'])

    return render_template('index.html', messages=texts)


# Only used when starting the server during development
if __name__ == '__main__':
    app.debug = True
    app.run(host='0.0.0.0', port=8000)
