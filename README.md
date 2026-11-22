# Bi-Directional TCP Chat Application

A small Java client/server chat program built on `java.net.Socket` and `ServerSocket`. The server listens on TCP port 5000, accepts one client, and both sides can send text messages at the same time using separate sending and receiving threads.

## Contents

```text
chattingapplication/chattingapplication/
├── src/com/chattingapplication/server/MyServer.java
├── src/com/chattingapplication/client/MyClient.java
└── src/module-info.java
```

Compiled `.class` files are also committed under `bin/`.

## Running

1. Start `MyServer`.
2. Edit the server address in `MyClient.java` (`serverAddress`) to the server's IP address, then start `MyClient`.

## Limitations

- Messages are sent as plain text without encryption or authentication.
- The server accepts a single client.
- The client's server address is hard-coded and must be edited before use.

## Scope note

The repository name mentions "TCP analysis", but this repository does not contain packet captures or traffic-analysis results; it contains only the chat source code. Any traffic analysis performed during the coursework is not documented here.
