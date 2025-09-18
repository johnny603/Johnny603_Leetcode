class TaskManager {
    unordered_map<int, pair<int,int>> taskMap; // taskId -> {userId, priority}
    priority_queue<tuple<int,int,int>> pq;     // {priority, taskId, userId}
    
public:
    TaskManager(vector<vector<int>>& tasks) {
        for (auto &t : tasks) {
            int userId = t[0], taskId = t[1], priority = t[2];
            taskMap[taskId] = {userId, priority};
            pq.push({priority, taskId, userId});
        }
    }
    
    void add(int userId, int taskId, int priority) {
        taskMap[taskId] = {userId, priority};
        pq.push({priority, taskId, userId});
    }
    
    void edit(int taskId, int newPriority) {
        auto [userId, oldPriority] = taskMap[taskId];
        taskMap[taskId] = {userId, newPriority};
        pq.push({newPriority, taskId, userId}); // push new version
    }
    
    void rmv(int taskId) {
        taskMap.erase(taskId); // lazy deletion: old entries remain in heap
    }
    
    int execTop() {
        while (!pq.empty()) {
            auto [priority, taskId, userId] = pq.top();
            if (taskMap.count(taskId) && taskMap[taskId].second == priority) {
                pq.pop();
                taskMap.erase(taskId);
                return userId;
            }
            pq.pop(); // discard outdated entry
        }
        return -1;
    }
};
