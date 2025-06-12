class Solution {
    func maxAdjacentDistance(_ nums: [Int]) -> Int {
        var maxDistance = 0
        let n = nums.count

        for i in 0..<n {
            let next = (i + 1) % n // circular array
            let distance = abs(nums[i] - nums[next])
            maxDistance = max(maxDistance, distance)
        }

        return maxDistance
    }
}
