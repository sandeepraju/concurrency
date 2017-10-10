# source: https://gist.github.com/dyerw/3d53e7cd94f05cc92c1c

#     # some decorator magic
#
#     @coroutine
#     def xyz():
#         blah blah
#
#     xyz()
#
#     # the above snippet is same as
#
#     def xyz():
#         blah blah
#
#     newXyz = coroutine(xyz)
#
#     newXyz()
#
#     # @coroutine wraps the function that has been
#     # annotated (decorated) with

import random

def coroutine(func):
    # ignore this magic for now
    def start():
        cr = func()
        next(cr)
        return cr
    return start

@coroutine
def consumer():
    while True:
        # receive this number from the parent number
        print("[consumer] waiting to receive a number")
        num = yield
        print("[consumer] received a number: {}".format(num))

def producer(consumer):
    while True:
        r = random.randint(0, 20)
        print("[producer] generated a number: {}".format(r))
        consumer.send(r) # send the generated number to the yielded consumer
        print("[producer] sent the generated number to consumer")

        print("[producer] waiting before generating next number")
        # Wait here until next() is called
        yield

if __name__ == "__main__":
    # Set up generators
    c = consumer()
    p = producer(c)

    # Produce 10 random numbers
    for _ in range(10):
        # invoke the yielded generators
        next(p)
