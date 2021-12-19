# gRPC Android (Java)

Android Java demo project.

### Set-Up

#### 1. Start gRPC server on localhost.

Refer: https://github.com/ashwindmk/nodejs_grpc_typescript

#### 2. Start tunnelling.

You can use any tool, I have used https://github.com/vakuum/tcptunnel.

```
brew install tcptunnel

tcptunnel --local-port=40000 --remote-host=127.0.0.1 --remote-port=50000
```

#### 3. Get your private IP Address
```
ipconfig getifaddr en0
```

#### 4. Set your IP address and port in `local.properties` file as shown below
```
# For emulator
#server_host="10.0.2.2"

# For device
server_host="192.168.2.30"

server_port=40000
```

### Run
Run the app on your Android device.
