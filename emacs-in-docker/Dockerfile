from ubuntu:16.04

RUN apt-get update && \
    apt-get install -y wget build-essential emacs24

ADD entrypoint.sh /entrypoint.sh
RUN chmod u+x /entrypoint.sh

COPY init.el /init.el

EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]
CMD bash
