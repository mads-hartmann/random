# encoding: utf-8
#
# python check_ascii.py email-templates.pot
#

import sys

if __name__ == "__main__":
    filename = sys.argv[1]
    if not filename:
        print "Please supply filename as first argument"
        exit(1)

    with open(filename) as f:
        found_error = False
        for line in f:
            try:
                line.encode('ascii')
            except UnicodeDecodeError:
                found_error = True
                print "Problems with line '{}'".format(line)

        if found_error:
            exit(1)
        exit(0)
