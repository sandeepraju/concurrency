# Example 01
numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
for i in numbers:
    print(numbers)



# # Example 02
# def square_eager(n):
#     print("start of square_eager")
#     result = []
#     for i in range(n):
#         print("generating square of {}".format(i))
#         result.append(i * i)

#     print("end of square_eager")
#     return result

# for s in square_eager(10):
#     print(s)




# # Example 03
# def square_lazy(n):
#     print("start of square_lazy")
#     for i in range(n):
#         print("generating square of {}".format(i))
#         yield i * i

#     print("end of square_lazy")

# for s in square_lazy(10):
#     print(s)
