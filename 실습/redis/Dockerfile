FROM redis:3.0
MAINTAINER kyungmi kang <carami@gmail.com>

RUN mkdir /var/log/redis

RUN mkdir /usr/local/etc/redis

COPY conf/redis.conf /usr/local/etc/redis/redis.conf
CMD [ "redis-server", "/usr/local/etc/redis/redis.conf" ]

