#!/bin/bash

function start_stub {
  PID1=`java -jar stubby4j-3.3.0.jar -l 0.0.0.0 -s 4567 -a 19080 -t 29080 -d user_service_config.yml > stub_4567.log 2>&1 & echo $!`
  PID2=`java -jar stubby4j-3.3.0.jar -l 0.0.0.0 -s 5000 -a 19090 -t 29090 -d hierarchy.yml > stub_5000.log 2>&1 & echo $!`
  echo "PIDs: $PID1 $PID2"
  return $?
}


function _list_stub_services {
  ps -ef | grep stubby4j | grep -v grep | grep -v 'kill'
}

function stop_stub {
  _list_stub_services | awk '{print $2}' | xargs kill -9 || echo "Nothing to kill"
}

case "$1" in
  start)
    start_stub
    ;;
  stop)
    stop_stub
    ;;
  list)
    _list_stub_services
    ;;
  *)
    echo "Usage:
    /stub.sh [start|stop|list]"
    exit 2
esac

