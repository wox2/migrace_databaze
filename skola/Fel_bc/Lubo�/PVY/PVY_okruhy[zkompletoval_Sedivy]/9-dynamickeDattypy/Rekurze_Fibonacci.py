def fibonacci(n):
    if(n < 2):
        if(n < 1):
            return 0
        return 1

    return fibonacci(n-1) + fibonacci(n-2)

print fibonacci(6)

#
# 5 + 4
# 4 + 3 + 3 + 2
# 3 + 2 + 2 + 1 + 2 + 1 + 1 + 0
# 2 + 1 + 1 + 0 + 1 + 0 + 1 + 1 + 0 + 1 + 1
# 1 + 0 + 1 + 1 + 1 + 1 + 1 + 1 + 1
# 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1
# = 8




