def shift_list(initial_list, index):
    """
    :type initial_list: List[int]
    :type index: int
    :rtype: List[int]
    """

    #check if index is out of input list's range.
    if index > len(initial_list)-1:
        raise IndexError("index is too large")

    #index 0 means no need to shift the list.
    if index == 0:
        return initial_list
    
    #append values before the index to the bottom half.
    shifted_list = initial_list[index:]
    shifted_list.extend(initial_list[:index])

    return shifted_list


def largest_integer_shifted_list_slow(shifted_list):
    """
    :type shifted_list: List[int]
    :rtype: int
    """

    #keep incrementing the index pointer i until value[i+1] is smaller than value[i]
    i = 0
    length = len(shifted_list)
    while i < length-1 and shifted_list[i+1] > shifted_list[i]:
        i += 1
    return shifted_list[i]

def largest_integer_shifted_list_fast(shifted_list):
    """
    :type shifted_list: List[int]
    :rtype: int
    """

    #call a helper function with additional parameters
    return largest_integer_helper(shifted_list, 0, len(shifted_list) - 1)

def largest_integer_helper(shifted_list, start, end):
    """
    :type shifted_list: List[int]
    :type start: int
    :type end: int
    :rtype: int
    """

    #if there is only one element in the list, return that element. Base case.
    if start == end:
        return shifted_list[start]
    
    #if there are two elements, return the larger element. Base case.
    if (end - start) == 1:
        return max(shifted_list[start],shifted_list[end])
    
    #find the middle point of the array
    mid = start + (end - start)/2

    #case when not rotated, which means it is a increasing sorted list, return the last element.
    if shifted_list[start] < shifted_list[end]:
        return shifted_list[end]
    
    #if middle element is larger than the start element, look in the middle to end half for answer, recursively.
    elif shifted_list[mid] > shifted_list[start]:
        return largest_integer_helper(shifted_list, mid, end)
    
    #or look in the start to middle half for answer, recursively.
    else:
        return largest_integer_helper(shifted_list, start, mid)



if __name__ == "__main__":
    initial_list = [1, 3, 7, 8, 9, 10, 11]
    list_1 = shift_list(initial_list, 3)
    print "\n------Case_1------"
    print largest_integer_shifted_list_slow(list_1)
    print largest_integer_shifted_list_fast(list_1)
    print "\n------Case_2------"
    initial_list_2 = [2, 4, 6, 8, 10]
    list_2 = shift_list(initial_list_2, 2)
    print largest_integer_shifted_list_slow(list_2)
    print largest_integer_shifted_list_fast(list_2)
    print "\n------Case_3------"
    list_3 = shift_list(initial_list_2, 0)
    print largest_integer_shifted_list_slow(list_3)
    print largest_integer_shifted_list_fast(list_3)
    print "\n------Additional tests------"
    test_list = [3, 3, 7, 8, 9, 10, 11]
    list_4 = shift_list(test_list, 1)
    print largest_integer_shifted_list_slow(list_4)
    print largest_integer_shifted_list_fast(list_4)
    test_list_2 = [3, 3, 3, 3, 3, 3, 3]
    list_5 = shift_list(test_list_2, 1)
    print largest_integer_shifted_list_slow(list_5)
    print largest_integer_shifted_list_fast(list_5)
    test_list_3 = []
    list_6 = shift_list(test_list_3, 0)
    print largest_integer_shifted_list_slow(list_6)
    print largest_integer_shifted_list_fast(list_6)