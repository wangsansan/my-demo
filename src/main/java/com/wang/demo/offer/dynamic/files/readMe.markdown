##动态规划分两种
### 1. 求价值，求多少，最大、最多、最小、最少
此时应该都用都是 dp[j] = Math.max(dp[j], dp[j - nums[i]] + value[i]) 这种公式

### 2. 求多少种方式、方法
用的都是 dp[j] += dp[j - 1]

#### 综上可以发现，求当前价值或者重量只跟上一个状态相关；而求总共多少种方式、方法，和之前的每一个取值相关，类似无限斐波拉契数列问题

## 背包问题
**物品是否可重复进行区分：物品不可重复->01背包；物品可重复->完全背包**
### 1. 01背包问题
01背包问题一般用来求最大、最小值
两层for循环的内层，对target进行for循环，需要从大到小进行遍历，否则可能存在重复
`for(int i = 0; int i < nums.length; i++) {
    for(int j = target; j >= nums[i]; j--){
        dp[j] = Math.max(dp[j], dp[j - nums[i]] + values[i])
    }
}`
一般题目变种就是nums[i]和values[i]的设定
### 2. 完全背包问题
#### 2.1 求最大、最小值
两层for循环的内层，对target进行for循环，需要从小到大进行遍历，因为需要重复
此时的两层for循环里外层对换没有差别
`for(int i = 0; int i < nums.length; i++) {
    for(int j = nums[i]; j <= target; j++){
        dp[j] = Math.max(dp[j], dp[j - nums[i]] + values[i])
    }
}`
#### 2.2 求次数、求个数
完全背包更多的使用场景是这种使用场景。
`dp[j] += dp[j - i];
 dp[0] = 1;`
 组合和排列的不同就不再解释了。
**对target的for循环从小到大遍历**
##### 2.2.1 求组合
对target的for循环放在两层for循环的内侧
`int[] dp = new int[target + 1];
 dp[0] = 1;
 for(int i = 0; int i < nums.length; i++) {
    for(int j = nums[i]; j <= target; j++){
        dp[j] += dp[j - nums[i]]
    }
}`
##### 2.2.2 求排列
对target的for循环放在两层for循环的外侧
`int[] dp = new int[target + 1];
 dp[0] = 1;
 for(int j = 1; j <= target; j++){
    for(int i = 0; i < nums.length; i++) {
        if(j >= nums[i]){
            dp[j] += dp[j - nums[i]]
        }
    }
 }`
##### 2.2.3 why
把for循环里外层交换一下就可以分别求组合、排列。我的分析如下：
`差别在于：
 排序遍历不管是在求dp[j]中任何一个数的时候都可以使用所有的排列数，也就是nums[0..i]里所有的数，依赖的dp[0..j-1]都会包含nums[i]
 组合遍历在求dp[j]中的值的时候，依赖的都是不包含本次使用的nums[i]得到的dp[0..j-1]的值
 从而导致了差别`
