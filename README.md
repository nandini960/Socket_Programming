# Socket_Programming
To connect a server with multiple clients using socket programming, you can follow the following steps:
Create a server program that listens for incoming client connections. This can be done using a ServerSocket object in Java.
Once a client connection is accepted, create a new thread (or use a thread pool) to handle communication with the client. This can be done using a Socket object in Java.
In the thread, use InputStream and OutputStream objects to communicate with the client. You can use BufferedReader and PrintWriter objects to read and write data.
Repeat steps 2 and 3 for each new client connection.

Server Java class is a basic server that listens on port 9090 for incoming client connections.
The Server class has a main method that creates a ServerSocket object and listens for incoming client connections using the accept method. Once a client connects, a new ClientHandler object is created, passing the client's Socket object to its constructor.
The ClientHandler object is then added to an ArrayList of client handlers, and a ThreadPoolExecutor is used to handle the client in a separate thread.
The f method initializes a HashMap called mp with five key-value pairs, where each key is a set name and each value is another HashMap. Each inner HashMap contains two key-value pairs representing a card name and its corresponding value.
The mp HashMap is used to store data that the client can query

The Client class has a main method that creates a new Socket object, which connects to a server at the IP address 127.0.0.1 and port number 9090.
It then creates a BufferedReader object to read input from the server, a BufferedReader object to read input from the keyboard, and a PrintWriter object to send output to the server.
The while loop keeps running until the user enters "quit" command. Inside the loop, the user is prompted to enter a command, which is then sent to the server using the out.println(command) method.
If the server responds with "EMPTY", the client prints "Server says: EMPTY". Otherwise, the response is assumed to be an integer k, which represents the number of lines of output the server will send back. The client then reads k lines of output from the server using a for loop, printing each line preceded by "Server says: ".
Finally, the Socket is closed, and the program exits.

Java class for a ClientHandler implements the Runnable interface. It is responsible for handling incoming client messages, parsing them, and sending appropriate responses back to the client based on the data stored in the Server.mp HashMap.
The ClientHandler constructor takes a Socket object representing the client's connection, and initializes input and output streams to communicate with the client.
The run method is the main method for handling client messages. It runs in an infinite loop, reading incoming client messages from the input stream and sending responses back to the client via the output stream.
The incoming message is split using the - delimiter, with the first part representing the set name and the second part representing the card name. If the Server.mp HashMap contains the set name, and the inner HashMap for that set contains the card name, then the corresponding value is retrieved and sent back to the client.
If the set name or card name is not found in Server.mp, or the incoming message is "quit", then the loop is terminated and the connection is closed.
