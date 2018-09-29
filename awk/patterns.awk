# A bunch of pattern examples

# The empty pattern
{print "The empty pattern is always true: " $0}

# Regular expression
/second/ {print "A line matched the regex: " $0 }

# An expression
NR == 1 { print "The first line: " $0}

# A range
NR == 2, NR == 3 { print "In range as NR is " NR ": " $0}

# Using the special patterns
BEGIN { print "Before everything else" }
END { print "After everything else" }
