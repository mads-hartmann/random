"""
python wait.py path1 path2 .... pathN

Sleep until the modification timestamp of path1 is greater than that of
files path2 through pathN
"""

import os
import time
import sys


target = sys.argv[1]
prerequisites = sys.argv[2:]


while os.path.getmtime(target) < max(os.path.getmtime(p) for p in prerequisites):
    time.sleep(1.0)
