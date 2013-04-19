/opt/module/nginx/current/sbin/nginx
export LC_ALL=C
mongod -f /opt/module/mongodb/conf/mongodb.conf &
redis-server /opt/module/redis/conf/redis.conf &
/opt/module/jetty/current/bin/jetty.sh restart
/opt/mkfree/free-apiservice/bin/apiservice.sh restart
/opt/module/elasticsearch/current/bin/elasticsearch
spawn-fcgi -a 127.0.0.1 -p 9000 -C 1 -u www-data -f /usr/bin/php-cgi