FROM python:3.4

RUN mkdir -p /usr/deployables/web
COPY . /usr/deployables/web

WORKDIR /usr/deployables/web

# Usefull when starting the ptpython or other
# python REPLs.
ENV PYTHONPATH="/usr/deployables/web/src":$PYTHONPATH

RUN pip install --no-cache-dir -r requirements.txt
