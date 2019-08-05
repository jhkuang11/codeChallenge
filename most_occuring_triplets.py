'''
ignore this file
'''
def user_logger(log_file):
    """
    :type log_file: List[Tuple(string, string)]
    :rtype: Dict[String, List[String]]

    ex: {"user_1": ["/" , "/about", "/features", "/purchase", "/thank-you"]}
    """

    #a dictionary to map user to all request paths that user had
    userData = {}

    #go through each entry, tuple in this case, of the log file
    #if the user name does not exist, create an entry for that username
    #and store the corresponding path
    #else append it to the exisitng list
    for tup in log_file:
        if tup[1] not in userData:
            userData[tup[1]] = [(tup[0])]
        else:
            userData[tup[1]].append(tup[0])
    return userData

def generate_unique_triplet(requestList):
    """
    :type requestList: List[String]
    :rtype: List[Tuple(string)]

    ex: user_1 had request paths: ["/" , "/about", "/features", "/purchase", "/thank-you"]
    result:
    [("/", "/about", "/features"),("/about", "/features", "/purchase"),("/features", "/purchase", "/thank-you")]
    """

    #just check if the list is NULL
    if requestList is None:
        return
    
    #if the request paths only had no more than 3 items, just return that list with each item as a tuple
    length = len(requestList)
    if length <= 3:
        return [tuple(requestList)]
    
    #if paths have more than 3 items, starting with index 0, put 3 consecutive items in the tuple
    #and append to the triplet list, stop when the index is at length of request paths minus 3
    #because last three items form the last triplet
    triplet = []
    i = 0
    while i <= length - 3:
        triplet.append((requestList[i],requestList[i+1],requestList[i+2]))
        i += 1
    return triplet

def calculate_ten_most_frequent_triplet(userData):
    """
    :type log_file: List[Tuple(string, string)]
    :rtype: List[Tuple(Tuple(string),counts)]

    ex: input: {"user_1": ["/" , "/about", "/features", "/purchase", "/thank-you"],...}
        output: [(('/about', '/purchase', '/thank-you'), 2),...]
    """

    #a dictionary to hold counts for each triplet
    results = {}

    #go through each user and generate unique triplets for each user
    for user in userData.keys():
        userTriplet = generate_unique_triplet(userData[user])

        #keep count of each triplet in the results dictionary
        for eachTriplet in userTriplet:
            if eachTriplet not in results:
                results[eachTriplet] = 1
            else:
                results[eachTriplet] += 1
    #sort the results dictionary in descending order and return 10 items if it is more than 10 or all items
    sorted_results = sorted(results.iteritems(), key=lambda (k,v): (v,k), reverse=True)
    return sorted_results if len(sorted_results) <= 10 else sorted_results[:10]
    


if __name__ == "__main__":
    log_file = [
    ("/", "user_1"),
    ("/about", "user_1"),
    ("/", "user_3"),
    ("/features", "user_1"),
    ('/about', "user_2"),
    ("/purchase", "user_2"),
    ("/purchase", "user_1"),
    ("/thank-you", "user_1"),
    ("/about", "user_3"),
    ("/thank-you", "user_2"),
    ("/purchase", "user_3"),
    ("/thank-you", "user_3"),
    ]

    userData = user_logger(log_file)
    print "\n-------------User_Logger-------------"
    print userData
    print "\n-------------User_1_triplets-------------"
    user_1_triplet = generate_unique_triplet(userData["user_1"])
    print user_1_triplet
    print "\n-------------Results-------------"
    topTen = calculate_ten_most_frequent_triplet(userData)
    print topTen