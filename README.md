# demo-coins: Hello world Bitcoin Payment API

### Software and Tools
1. Bitcoin Core 0.21.0. Download from https://bitcoin.org/en/download.
2. JDK 11. The language level is set to Java 8 features.
3. IntelliJ IDEA.

### Local Development
#### Bitcoin Core
Bitcoin node must run in `regtest` mode.

Copy the `bitcoin.conf` file available under repository's `bitcoin-files` folder to the local `.bitcoin` folder. Backup the existing conf file if Bitcoin Core is already installed on development machine. 

Start the bitcoin daemon.
```
bitcoind -regtest -daemon
```

Create wallets for `test-funds`, `receiver` and `sender`.
```
bitcoin-cli -regtest createwallet "test-funds"
bitcoin-cli -regtest createwallet "receiver"
bitcoin-cli -regtest createwallet "sender"
```
View the loaded wallets.
```
bitcoin-cli -regtest listwallets
```
The wallets have to be created the first time or after deleting the `.bitcoin/regtest` folder.
Other times they can be loaded as shown below, if not loaded already.
```
bitcoin-cli -regtest loadwallet "test-funds"
bitcoin-cli -regtest loadwallet "receiver"
bitcoin-cli -regtest loadwallet "sender"
```

Generate bitcoins to the `test-funds` wallet.
```
bitcoin-cli -regtest generatetoaddress 1001 $(bitcoin-cli -regtest -rpcwallet=test-funds getnewaddress)
```

Transfer bitcoins from `test-funds` to `receiver` wallet.
```
NEW_ADDRESS=$(bitcoin-cli -regtest -rpcwallet=receiver getnewaddress)
bitcoin-cli -regtest -rpcwallet=test-funds sendtoaddress $NEW_ADDRESS 3000.00
bitcoin-cli -regtest generatetoaddress 1 $NEW_ADDRESS
unset NEW_ADDRESS
```

Similarly, transfer bitcoins from `test-funds` to `sender` wallet.
```
NEW_ADDRESS=$(bitcoin-cli -regtest -rpcwallet=sender getnewaddress)
bitcoin-cli -regtest -rpcwallet=test-funds sendtoaddress $NEW_ADDRESS 3000.00
bitcoin-cli -regtest generatetoaddress 1 $NEW_ADDRESS
unset NEW_ADDRESS
```

#### Spring Boot Application
To build artifact for local development.
```
./mvnw clean package
```

Deploy as,
```
java -jar target/demo-coins-0.0.1-SNAPSHOT.jar
```

The application APIs will be available at `http://localhost:8080/coins/swagger-ui/index.html`.

The H2 database console will be available at `http://localhost:8080/coins/h2-console`. The connection settings can be taken from `application.properties`.
