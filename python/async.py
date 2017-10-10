# source:

import asyncio
import time
from datetime import datetime

# async .. await is a

async def iotask():
    # time.sleep(1)  # blocking
    asyncio.sleep(1) # non-blocking

async def hammer():
    n = 500000
    start = time.time()
    while n > 0:
        n -= 1
        end = time.time()
    print ('[{}] exec time = {}'.format(name, (end - start)))

async def factorial(name, number):
    print('[{}][start] factorial({})'.format(name, number))
    f = 1
    for i in range(2, number+1):
        # Imagine this as making a network (IO) call
        # to connect to a server or query database, etc.
        await iotask()
        f *= i
    print('[{}][end] factorial({}) = {}'.format(name, number, f))


start = time.time()
loop = asyncio.get_event_loop()
tasks = [
    asyncio.ensure_future(factorial("A", 3)),
    asyncio.ensure_future(factorial("B", 4)),
    asyncio.ensure_future(factorial("C", 6)),
]
loop.run_until_complete(asyncio.wait(tasks))
loop.close()
end = time.time()
print("Total time: {}".format(end - start))
