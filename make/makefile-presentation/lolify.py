import sys

with open(sys.argv[1]) as f:
    for line in f:
        words = line.split()
        lols = " ".join(["lol" for _ in words])
        print(lols)
