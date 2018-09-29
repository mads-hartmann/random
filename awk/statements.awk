BEGIN {
    skipped = 0
    long_lines = 0
    count = 0
}

NF <= 5 { skipped++ }
NF > 5 {
    {
        long_lines++
        count += NF
    }
}

END {
    print "Skipped " skipped " lines as they were too short"
    print "Found " long_lines " with a total of " count " words!"
}
