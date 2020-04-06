#!/bin/sh 
# used to find out where the gsql_client.jar file is on the server
find / -name gsql_client.jar 2>/dev/null
# /home/tigergraph/tigergraph/app/3.0.0/dev/gdk/gsql/lib/gsql_client.jar
# You can then copy this file to the mydata directory so it is on the client
# cp /home/tigergraph/tigergraph/app/3.0.0/dev/gdk/gsql/lib/gsql_client.jar ~/mydata
