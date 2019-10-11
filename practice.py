from fractions import gcd
from math import sqrt

def maxArithmeticLength2(a, b):
    if len(a) == 1:
        factors = list(map(lambda i: abs(i - a[0]), b))
    else:
        candidates = [a[1]]
        for num in b:
            if  a[0] < num < a[1]:
                candidates.append(num)
        factors = list(map(lambda i: abs(i - a[0]), candidates))
    
    seta, setb = set(a), set(b)
    max_length = -1

    for factor in factors:
        cur = a[0] + factor
        length = 1
        while cur in setb or cur in seta:
            cur += factor
            length += 1
        if cur >= a[-1]:
            cur = a[0] - factor
            while cur in setb:
                cur -= factor
                length += 1
            max_length = max(max_length, length)
    
    return max_length

def maxArithmeticLength(a, b):
    if len(a) == 1:
        factors = list(map(lambda i: abs(i - a[0]), b))
    else:
        num = a[1] - a[0]
        for i in range(2, len(a)):
            num = gcd(num, a[i] - a[i - 1])

        factors = []
        last = int(sqrt(num) + 1)
        for div in range(1, last):
            if num % div == 0:
                factors.append(div)
                if div != last:
                    factors.append(num//div)
    
    seta, setb = set(a), set(b)
    max_length = -1

    for factor in factors:
        cur = a[0] + factor
        length = 1
        while cur in setb or cur in seta:
            cur += factor
            length += 1
        if cur >= a[-1]:
            cur = a[0] - factor
            while cur in setb:
                cur -= factor
                length += 1
            max_length = max(max_length, length)
    
    return max_length

def maxRibbon(arr, k):
    hi = max(arr)
    lo = 1
    res = 0
    while lo <= hi:
        mid = (lo + hi) / 2
        curr = 0
        for i in arr:
            curr += i/mid
        if curr >= k:
            res = max(res, mid)
            lo = mid + 1
        else:
            hi = mid - 1
    return res

class Solution(object):
    def findMaxLength(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        count = 0
        table = {0:-1}
        ans = 0
        for index in range(len(nums)):
            count += 1 if nums[index] == 1 else -1
            if count not in table:
                table[count] = index
            else:
                ans = max(ans, index - table[count])
        return ans


if __name__ == '__main__':
    ans = maxArithmeticLength2([0,4,8,20],[2,6,10,12,14,16,18,22])
    print ans
