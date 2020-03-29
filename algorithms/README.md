# Algorithms written in Java

## What is recursion?
_Recursion_ is the process in which when a function calls itself directly or indirectly. This corresponding function is called _recursion function_.

Every recursive function has two parts: 
* the base case
* the recursive case.

All function calls go onto the _call stack_. It can get very large and can take up a lot of memory.

## What is Big O notation?

### The definition of Big O notation
It’s called `Big O notation` because you put a “big O” in front of the number of operations.
Big O notation lets you compare the number of operations. 
It tells you how fast the algorithm grows(how fast an algorithm is).

### Examples of Big O notation(from fastest to slowest)
* O(log2 N), also known as log time. Example: `binary search`.
* O(N), also known as linear time. Example: `simple search`.
* O(N * log2 N). Example: A fast sorting algorithm, like `quick sort`.
* O(N*N). Example: A slow sorting algorithm, like `selection sort`.
* O(N!). Example: A really slow algorithm, like the `traveling salesperson problem`.

#### Examples of Big O estimations:
* Printing the value of each element in an array.
  * O(n)
* Doubling the value of each element in an array.
  * O(n)
* Doubling the value of just the first element in an array.
  * O(1)

### Main points
* Algorithm speed isn’t measured in seconds.
* Algorithm times are measured in terms of growth of an algorithm.
* Algorithm times are written in Big O notation

## Arrays vs Lists?
_Complexity_:

Operation | Arrays | Lists
--- | --- | ---
*READING* | **O(1)** | `O(N)`
*INSERTIONS* | `O(N)`| **O(1)**
*DELETION* | `O(N)` | **O(1)**

Where: O(N) -linear time, O(1) - constant time.
 
* Arrays allow fast reads
* Linked lists allow fast inserts and deletes


## How does Divide&Conquer(D&C) approach work?
1. Figure out a simple case as the base case.
2. Figure out how to reduce your problem and get to the base case.
 