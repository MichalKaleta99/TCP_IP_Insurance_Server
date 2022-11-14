# TCP_IP_Insurance_Server - about

Project was made for recruitment process.

# Working principle

A simple TCP/IP server that opens a Socket and starts listening for incoming data on a selected port. 
After receiving the incoming message (the client sends the user's id, i.e. the data in the id column of the "users" table), the server will download all insurance offers for a given user and his vehicles and send them back in response. 

# Tech stack
Java

Pure JDBC to read data from the database - PostgreSQL. 

# How to run?

First, create tables with sql_tables_creator SQL which is avaible in my repo. Then fill them with your test data corresponding to required datatypes of columns.

Then, run Server.java file. By default it creates a new Socket which is hardcoded to localhost at port 4999. After running that file, run Client.java, which by default
is hardcoded to connect to port 4999 of Server.

If console asks you for user ID then it means that connection got established properly. Type user ID (remember it should be an integer data type) and wait for data :)
