import time
from threading import Thread

def countdown(n, name = "default"):
    start = time.time()
    while n > 0:
        n -= 1
    end = time.time()
    print ('[{}] exec time = {}'.format(name, (end - start)))

COUNT = 50000000

# Example 01
# countdown(COUNT, "default")

# Example 02
# Thread(target=countdown, args=(COUNT,)).start()

# Example 03
# Thread(target=countdown, args=(COUNT, "one")).start()
# Thread(target=countdown, args=(COUNT, "two")).start()
# # Thread(target=countdown, args=(COUNT, "three")).start()
# # Thread(target=countdown, args=(COUNT, "four")).start()
# # Thread(target=countdown, args=(COUNT, "five")).start()
