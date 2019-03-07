## Shifted List Search (Answer below)

We have a list of ordered integers (ex: `[1, 3, 7, 8, 9, 10, 11]`). Suppose we slice into that list at a random index and append the "top" half of list to the "bottom" (maintaining the order of both halves while doing so).

**Challenge:** Write a function that returns the largest integer in the "shifted" list.

View the samples below for a better understanding of how the lists are mutated:

```python
# Sample 1
initial_list = [1, 3, 7, 8, 9, 10, 11]  # Here is our initial ordered list
shifted_list = [8, 9, 10, 11, 1, 3, 7]  # Here is the list after it has been sliced (at index 3) and shifted
# Your function should return `11`

# Sample 2
initial_list = [2, 4, 6, 8, 10]  # Here is our initial oredered list
shifted_list = [6, 8, 10, 2, 4]  # Here is the list after it has been sliced (at index 2) and shifted
# Your function should return `10`

# Sample 3
initial_list = [2, 4, 6, 8, 10]  # Here is our initial oredered list
shifted_list = [2, 4, 6, 8, 10]  # Here is the list after it has been sliced (at index 0) and shifted
# Your function should return `10`
```
**Answer** all functions are defined in shifted_list_search.py
1. Can you identify any edge cases that we need to account for?<br/>
**Answer:** Empty list, list with duplicates, list with same values. Empty list is handled by shift_list function
I defined, which raises index error when index is larger than the input list. List with duplicates and list with
same values are also handled well by the function to find the largest integer.

2. Can you explain the orders of growth implications of the algorithm you implemented?<br/>
**Answer:** The naive method is to scan through the list and stop at index (i) when we find value at index (i+1)
is smaller than value at index (i). That is the property of rotated list. If it is not rotated, then we will
scan the whole list, which makes the naive method upper bound by O(n) time, where n is the length of the list.

3. Suppose our initial list contains 1 million elements, is there a more performant way we can find the answer?
```python
# initial ordered list
initial_list = [1, 3, 7, 8, 9, 10, 11]

# Case 1: not rotated, or rotated at index 0
shifted_list = [1, 3, 7, 8, 9, 10, 11]

# Case 2: rotated at index 1
shifted_list = [3, 7, 8, 9, 10, 11, 1] #start:3, mid:9, end:1

# Case 1: rotated at index 4
shifted_list = [9, 10, 11, 1, 3, 7, 8] #start:9, mid:1, end:8
```
**Answer:** For a large list, scanning the list linearly would be an expensive operation, especially if the list 
is not rotated. We could do better by applying binary search on this task. If the list is not rotated, just
return the value at the end. If it is rotated, we could see two possible scenarios, one in which the value
at middle index is larger than the value at start index (case 2), and one in which the value at middle index
is smaller than the value at end index (case 3). If it is case 2, we look recursively at the second half, or
from middle to end because the value increases then decreases, which means there is a peak. Similarly, if it
is case 3, we look recursively at the first half, or from start to middle, because that segment has increasing
value followed by decreasing value, which implies a peak. The binary search approach reduces time complexity
to O(logN).

<img src="https://github.com/jhkuang11/codeChallenge/blob/master/results/shift_list_search.png" />

## Most Occurring Triplets (Answer below)

Suppose we have a list of tuples representing a web access "log file". Each tuple contains two elements:

1. The path of the request.
2. The ID of the user who made the request.

We want to track user flow through our website; specifically, we want to track "triplets". A triplet is a unique group of three consecutive pages visited by the same user.

**Challenge:** Write a function that returns the 10 most occurring triplets in the log file.

```python
# Here is our log file; essentially a list of tuples.
# Each tuple has two items: `(request_path, user_id)`
log_file = [
    ("/", "user_1"),
    ("/about", "user_1"),
    ("/", "user_3"),
    ("/features", "user_1"),
    ("/about", "user_2"),
    ("/purchase", "user_2"),
    ("/purchase", "user_1"),
    ("/thank-you", "user_1"),
    ("/about", "user_3"),
    ("/thank-you", "user_2"),
    ("/purchase", "user_3"),
    ("/thank-you", "user_3"),
]

# A triplet is a group of three consecutive pages visited by the same user. For example,
# user_1 visited the following pages in this order:
#    "/" , "/about", "/features", "/purchase", "/thank-you"
#
# In this case, user_1 created three unique triplets during her visit:
#    1. ("/", "/about", "/features")
#    2. ("/about", "/features", "/purchase")
#    2. ("/features", "/purchase", "/thank-you")
#
# At the same time, user_3 went through a different workflow but created two unique triplets during his visit:
#    1. ("/", "/about", "/purchase")
#    2. ("/about", "/purchase", "/thank-you")
#
# Your function should return the 10 most occurring triplets in the log file. Given the log file above, the
# returned list should something look like:
#
# [
#    ("/about", "/purchase", "/thank-you"), # cnt 2
#    ("/", "/about", "/features"), # cnt 1
#    ("/about", "/features", "/purchase"), # cnt 1
#    ("/features", "/purchase", "/thank-you"), # cnt 1
#    ("/", "/about", "/purchase"), #cnt 1
# ]
```
**Answer** all functions are defined in most_occuring_triplets.py
1. Create a function that transforms log_file into something like this:
```python
{'user_1': ['/', '/about', '/features', '/purchase', '/thank-you']}
```
2. Create a function that generates unique triplets for each user. Example is for user_1:
 ```python
[('/', '/about', '/features'), 
 ('/about', '/features', '/purchase'), 
 ('/features', '/purchase', '/thank-you')]
```
3. Create a function that aggregates each user's list of triplets and keeps track of their frequencies
 ```python
[(('/about', '/purchase', '/thank-you'), 2), 
 (('/features', '/purchase', '/thank-you'), 1), 
 (('/about', '/features', '/purchase'), 1), 
 (('/', '/about', '/purchase'), 1), 
 (('/', '/about', '/features'), 1)]
```
<img src="https://github.com/jhkuang11/codeChallenge/blob/master/results/most_occurring_triplet.png" />




