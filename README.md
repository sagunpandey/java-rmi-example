# java-rmi-example
A history program that returns a list of events that took place on a particular day in history. The program consists of two parts: a client and server. The server maintains the data and the client supports add and query operations.

This project also demonstrates a simple example for using Makefile to build Java programs. 

### Prerequisites

What things you need to install.

```
Oracle JDK 8
GNU Make
```
### Installing

A step by step explanation on how to compile and run the project -

Open terminal and inside project folder,

```
$ make
```

This should compile and generate all `.class` files inside `/bin` folder.
Navigate to `/bin` folder and start the Server.

```
$ cd bin
$ java Server
```

Now, open a new terminal and enter commands for add or query operations. **Remember** to navigate to `/bin` folder.

Add Operation: `java Client server add month date event`

Query Operation: `java Client server query month date`

```
$ java Client localhost add 12 25 "Christmas"

$ java Client localhost query 12 25
```


**Note:** You can run Server and Client on different machines.
