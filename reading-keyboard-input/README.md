# Reading Keyboard Input

## Problem

In my Emacs configuration I have a key-binding for `ctrl-.` which works
perfectly when I run `Emacs.app`. When I run Emacs in the terminal (iTerm)
nothing happens. Why?

## Terminology

- What are these things called? The Non-printable characters (control
sequences?, escape sequence?)

- How can Emacs GUI catch it and not iTerm
  Is the problem that iTerm doesn't send the correct XXX (use from from above?)

# Solution

I don't know yet but I have a feeling that iTerm only sends a fixed set of
escape sequences and that more can be added through iTerms preferences

## Resources

I think this has the best description.
https://unix.stackexchange.com/questions/116562/key-bindings-table#answer-116614

For the Emacs side of things this looks to be the best
https://www.masteringemacs.org/article/mastering-key-bindings-emacs
