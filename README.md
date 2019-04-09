# PingClientServerProgramUsingUDP
Pair program (Client &amp; Server) for implementing UDP's version of 'Ping', which tests your machine's ability to reach a specified host, in Java using DatagramSockets

Ping:
Ping is a network utility tool that is used to test reachability to a particular host on a TCP/IP network. It works by sending a packet to a specified host and waiting for a reply. Ping can be used for troubleshooting to test connectivity and determine response time between two IP host machines. The standard Ping program uses ICMP (Internet Control Message Protocol).

The client program sends 10 ping messages to the target server over UDP. For each message the client determines and prints Round-Trip Time (RTT) when the corresponding ping message is returned. Since UDP is an unreliable protocol a packet sent by the client or server may be lost. For this reason, the client cannot wait indefinitely for a reply to a ping message. The client waits up to one second for a reply from the server (uses random number generator to simulate packet loss); if no reply is received, the client assumes that the packet was lost and prints a message accordingly.
