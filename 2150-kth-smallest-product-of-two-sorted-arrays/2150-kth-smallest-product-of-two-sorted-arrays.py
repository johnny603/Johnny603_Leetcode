class Solution:
    def kthSmallestProduct(self, nums1: List[int], nums2: List[int], k: int) -> int:
        # Separate negatives and non-negatives, flip negatives to positives
        def split_and_reverse_negatives(arr):
            neg = []
            pos = []
            for x in arr:
                if x < 0:
                    neg.append(-x)  # Flip negative to positive
                else:
                    pos.append(x)
            neg.reverse()  # reverse to keep ascending order
            return neg, pos

        # Count number of products ≤ m using two pointers
        def countProductsLEQ(arr1, arr2, max_product):
            count = 0
            j = len(arr2) - 1
            for x in arr1:
                while j >= 0 and x * arr2[j] > max_product:
                    j -= 1
                count += j + 1
            return count

        # Separate and flip negative numbers for easier handling
        neg1, pos1 = split_and_reverse_negatives(nums1)
        neg2, pos2 = split_and_reverse_negatives(nums2)

        # Total number of negative products: one element is negative, other is positive
        total_negatives = len(neg1) * len(pos2) + len(pos1) * len(neg2)

        resultSign = 1
        if k > total_negatives:
            # kth product is positive
            k -= total_negatives
        else:
            # kth product is negative => we reverse sign later
            k = total_negatives - k + 1
            resultSign = -1
            neg2, pos2 = pos2, neg2  # switch arrays to find the neg product as pos

        # Binary search to find the kth smallest *positive* product
        left = 0
        right = 10**10

        while left < right:
            mid = (left + right) // 2
            # Count how many products are ≤ mid
            count = countProductsLEQ(neg1, neg2, mid) + countProductsLEQ(pos1, pos2, mid)
            if count >= k:
                right = mid
            else:
                left = mid + 1

        return resultSign * left
