import time
from socket import *
from threading import Thread
from concurrent.futures import ThreadPoolExecutor as Pool
# from concurrent.futures import ProcessPoolExecutor as Pool

pool = Pool(2)

def countdown(n, name = "default"):
    start = time.time()
    while n > 0:
        n -= 1
    end = time.time()
    print ('[{}] exec time = {}'.format(name, (end - start)))

COUNT = 50000000

def server(address):
    sock = socket(AF_INET, SOCK_STREAM)
    sock.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
    sock.bind(address)
    sock.listen(5)
    while True:
        client, addr = sock.accept()
        print("Connection", addr)
        Thread(
            target=request_handler,
            args=(client,),
            daemon=False
        ).start()

def request_handler(client):
    while True:
        req = client.recv(100)
        if not req:
            break

        # submit to the pool
        future = pool.submit(countdown, COUNT, "Pool")
        result = future.result()

        # process it in the handler thread
        # result = countdown(COUNT, "Default")

        resp = str(result).encode('ascii') + b'\n'
        client.send(resp)
    print("Closed")

server(('0.0.0.0', 9999))
