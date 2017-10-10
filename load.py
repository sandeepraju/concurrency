#!/usr/bin/env python
# source: https://wiki.python.org/moin/TcpCommunication#Client

import socket
import time
import sys

TCP_IP = '0.0.0.0'
TCP_PORT = 9999
BUFFER_SIZE = 1024
MESSAGE = str.encode('ping\n')

while True:
    start = time.time()
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((TCP_IP, TCP_PORT))
    conn = time.time()
    s.send(MESSAGE)
    data = s.recv(BUFFER_SIZE)
    s.close()
    end = time.time()
    print('conn = {}\tresp = {}\ttotal = {}'.format((conn - start), (end - conn), (end - start)))
