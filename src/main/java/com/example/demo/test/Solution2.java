package com.example.demo.test;

import java.math.BigDecimal;
import java.util.*;

public class Solution2 {


    public static void main(String[] args) {

        Solution2 solution2 = new Solution2();
       int[] a = {0,9,8,7,6,5,4,3,2,1};
       int[] b = solution2.guiBin(a);
       for(int i : b){
           System.out.println(i);
       }


    }

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    //上岸了
                    int a = area(grid, i, j);
                    res = Math.max(res, a);
                }
            }
        }
        return res;
    }

    private int area(int[][] grid, int i, int j) {
        if (notInArea(grid, i, j)) {
            return 0;
        }
        if (grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;

        return 1 + area(grid, i + 1, j)
                + area(grid, i - 1, j)
                + area(grid, i, j + 1)
                + area(grid, i, j - 1);
    }


    private boolean notInArea(char[][] grid, int i, int j) {
        boolean falg = false;
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            falg = true;
        }
        return falg;
    }

    public int numIslands(char[][] grid) {
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == '1') {
                    //上岸了,把岛搞沉
                    downIsland(grid, i, j);
                    num++;
                }
            }
        }
        return num;
    }

    private void downIsland(char[][] grid, int i, int j) {
        if (notInArea(grid, i, j)) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        downIsland(grid, i + 1, j);
        downIsland(grid, i - 1, j);
        downIsland(grid, i, j - 1);
        downIsland(grid, i, j + 1);
    }

    public int countNodes(TreeNode root) {

        return countNodes(root, 0);

    }

    public int countNodes(TreeNode root, int count) {
        if (root == null) {
            return 0;
        }
        count = 1 + countNodes(root.right, count) + countNodes(root.left, count);
        return count;

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack_l1 = new Stack<>();
        Stack<ListNode> stack_l2 = new Stack<>();
        while (l1 != null) {
            stack_l1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack_l2.push(l2);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode head = null;

        while (!stack_l1.empty() && !stack_l2.empty()) {
            int tmp = stack_l1.pop().val + stack_l2.pop().val + carry;
            int yushu = tmp % 10;
            carry = tmp / 10;
            ListNode cur = new ListNode(yushu);
            cur.next = head;
            head = cur;
        }


        if (stack_l1.empty() && !stack_l2.empty()) {
            while (!stack_l2.empty()) {
                int tmp = stack_l2.pop().val + 0 + carry;
                int yushu = tmp % 10;
                carry = tmp / 10;
                ListNode cur = new ListNode(yushu);
                cur.next = head;
                head = cur;
            }


        } else {
            while (!stack_l1.empty()) {
                int tmp = stack_l1.pop().val + 0 + carry;
                int yushu = tmp % 10;
                carry = tmp / 10;
                ListNode cur = new ListNode(yushu);
                cur.next = head;
                head = cur;
            }
        }
        if (carry > 0) {
            int tmp = 0 + 0 + carry;
            int yushu = tmp % 10;
            carry = tmp / 10;
            ListNode cur = new ListNode(yushu);
            cur.next = head;
            head = cur;
        }
        return head;

    }

    public int islandPerimeter(int[][] grid) {
        int zhouchang = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    //上岛，计算周长
                    zhouchang = doZhouChang(grid, i, j);
                }
            }
        }
        return zhouchang;
    }

    private int doZhouChang(int[][] grid, int i, int j) {
        //如果碰到边界了，则周长+1
        if (notInArea(grid, i, j)) {
            return 1;
        }
        //如果碰到水了，周长+1
        if (grid[i][j] == 0) {
            return 1;
        }
        if (grid[i][j] != 1) {
            return 0;
        }
        //将已经遍历后的岛击落
        grid[i][j] = 2;

        return doZhouChang(grid, i - 1, j) + doZhouChang(grid, i + 1, j) + doZhouChang(grid, i, j - 1) + doZhouChang(grid, i, j + 1);
    }


    public boolean checkValidString(String s) {
        //用两个stack分别保存（和*，当遇到）的时候，先弹出（，为空的时候再弹出*，如果最后有）剩余，则表示无效。
        boolean flag = true;
        Stack<Integer> stack1 = new Stack<>();//用来保存（
        Stack<Integer> stack2 = new Stack<>();//用来保存*
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack1.push(i);
            } else {
                if (chars[i] == '*') {
                    stack2.push(i);
                } else {
                    if (stack1.empty()) {
                        //（为空
                        if (stack2.empty()) {
                            //*为空
                            flag = false;
                        } else {
                            stack2.pop();
                            continue;
                        }
                    } else {
                        //(不是空
                        stack1.pop();
                        continue;
                    }
                }
            }
        }

        if (stack1.size() > stack2.size()) {
            flag = false;
        } else {

            while (!stack1.empty() && !stack2.empty()) {
                if (stack1.peek() > stack2.peek()) {
                    flag = false;
                    break;
                } else {
                    stack1.pop();
                    stack2.pop();
                }
            }
        }
        return flag;
    }

    List<Integer> res = new LinkedList<>();

    public List<Integer> rightSideView(TreeNode root) {

        rightlook(root, 0);
        return res;
    }

    private void rightlook(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (res.size() == depth) {
            res.add(root.val);
        }
        depth++;
        rightlook(root.right, depth);
        rightlook(root.left, depth);

    }

    public int rob_1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //设在第i个房子里能抢到的最多的钱是dp[i]
        int[] dp = new int[nums.length];
        //设定初始值
        dp[0] = nums[0];
        if (nums.length == 1) {
            return nums[0];
        } else {
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < nums.length; i++) {
                //计算状态转移方程
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[nums.length - 1];
        }

    }

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(robRange(nums, 0, nums.length - 2), robRange(nums, 1, nums.length - 1));

    }

    private int robRange(int[] nums, int start, int end) {
        int[] tmp = Arrays.copyOfRange(nums, start, end + 1);
        int[] dp = new int[tmp.length];
        dp[0] = tmp[0];
        if (tmp.length == 1) {
            return dp[tmp.length - 1];
        } else {
            dp[1] = Math.max(tmp[0], tmp[1]);
            if (tmp.length == 2) {
                return dp[tmp.length - 1];
            } else {
                for (int i = 2; i < tmp.length; i++) {
                    dp[i] = Math.max(dp[i - 1], dp[i - 2] + tmp[i]);
                }
                return dp[tmp.length - 1];
            }
        }


    }

    public int rob(TreeNode root) {
        Map<TreeNode, Integer> res = new HashMap<>();
        return rob(root, res);


    }

    private int rob(TreeNode root, Map<TreeNode, Integer> res) {
        if (root == null) {
            return 0;
        }
        if (res.containsKey(root)) {
            return res.get(root);
        }

        int money = root.val;
        if (root.left != null) {
            money = money + rob(root.left.right, res) + rob(root.left.left, res);
        }
        if (root.right != null) {
            money = money + rob(root.right.right, res) + rob(root.right.left, res);
        }
        int tmp = Math.max(money, rob(root.left, res) + rob(root.right, res));
        res.put(root, tmp);
        return tmp;

    }

    //前序遍历：根左右
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        preorderTraversal(root, res);
        return res;
    }

    private void preorderTraversal(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorderTraversal(root.left, res);
        preorderTraversal(root.right, res);

    }

    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }


        //状态设计,0为最大值，1为最小值
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                //算出当i的时候的最大值和最小值。
                dp[i][0] = Math.max(nums[i], dp[i - 1][0] * nums[i]);
                dp[i][1] = Math.min(nums[i], dp[i - 1][1] * nums[i]);
            } else {
                dp[i][0] = Math.max(nums[i], dp[i - 1][1] * nums[i]);
                dp[i][1] = Math.min(nums[i], dp[i - 1][0] * nums[i]);
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i][0]);
        }

        return res;

    }

    public int numDistinctIslands(int[][] grid) {
        Set<String> isLands = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    //登录
                    StringBuilder island = new StringBuilder();
                    loadLand(grid, i, j, island, i, j);
                    isLands.add(island.toString());

                }
            }
        }

        return isLands.size();

    }

    private void loadLand(int[][] grid, int i, int j, StringBuilder island, int yuan_i, int yuan_j) {
        //判断i，j是否在范围内
        if (notInArea(grid, i, j)) {
            return;
        }
        if (grid[i][j] == 0) {
            return;
        }

        grid[i][j] = 0;

        //记录岛屿
        island.append(i - yuan_i);
        island.append(j - yuan_j);
        //并把岛沉了


        loadLand(grid, i - 1, j, island, yuan_i, yuan_j);
        loadLand(grid, i + 1, j, island, yuan_i, yuan_j);
        loadLand(grid, i, j - 1, island, yuan_i, yuan_j);
        loadLand(grid, i, j + 1, island, yuan_i, yuan_j);


    }

    private boolean notInArea(int[][] grid, int i, int j) {
        boolean falg = false;
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            falg = true;
        }
        return falg;
    }

    public int trap_1(int[] height) {
        int sum = 0;
        //遍历height数组中所有，找到这个值的左右两边的最高值
        for(int i = 1;i<height.length - 1;i++){
            //找到i左边的最高的柱子
            int left_max = 0;
            int left_index = i -1;
            while (left_index>=0){
                left_max = Math.max(left_max,height[left_index]);
                left_index--;
            }
            //找到i右边的最高的柱子
            int right_max = 0;
            int rigjt_index = i + 1;
            while (rigjt_index <= height.length-1) {
                right_max = Math.max(right_max,height[rigjt_index]);
                rigjt_index++;
            }
            //根据木桶原理，算出高度
            int high = Math.min(right_max,left_max);
            high = high - height[i];
            if(high>0){
                sum = sum + high;
            }

        }

        return sum;

    }

    public int trap_2(int[] height) {
        if(height.length == 0){
            return 0;
        }
        //分别计算第i根柱子，左面最高的柱子的高度、右面最高柱子的高度。
        int[] left_max = new int[height.length];
        int[] right_max = new int[height.length];
        //初始化
        left_max[0] = 0;
        for(int i = 1;i<height.length;i++){
            left_max[i] = Math.max(height[i-1] , left_max[i-1]);
        }

        right_max[height.length - 1] = 0;
        for(int i = height.length - 2;i>=0;i--){
            right_max[i] = Math.max(height[i + 1], right_max[i + 1]);
        }
        int sum = 0;
        for(int i = 1;i<height.length - 1 ;i++){
            int high = Math.min(left_max[i],right_max[i]);
            high = high - height[i];
            if(high > 0){
                sum = sum + high;
            }
        }
        return sum;

    }

    public int firstMissingPositive(int[] nums) {
        //先把所有不可能发生的数都置为0
        for(int i = 0;i<nums.length;i++){
            if(nums[i] < 1 || nums[i] > nums.length ){
                nums[i] =0;
            }
        }
        //从头遍历，为每个值找到对应的位置
        for(int i = 0;i<nums.length;i++){
            if(nums[i] == i + 1){
                continue;
            }else {
                if(nums[i] != 0){
                    int a = nums[i];
                    nums[i] =0;
                    while (nums[a -1] != a){
                        if(nums[a-1] == 0){
                            nums[a-1] = a;
                        }else {
                            int b = nums[a-1];
                            nums[a -1 ] = a;
                            a = b;
                        }
                    }

                }

            }

        }

        int res = nums.length + 1;
        for(int i = 0;i<nums.length;i++){
            if(nums[i] == 0){
                res = i + 1;
                break;
            }
        }

        return res;
    }

    public int trap(int[] height) {
        int sum = 0;
        int left_max = 0;
        int[] right_max = new int[height.length];
        right_max[height.length -1 ] = 0;
        for(int i = height.length - 2;i>=0;i--){
            right_max[i] = Math.max(right_max[i+1],height[i+1]);
        }

        for(int i =1;i<height.length-1;i++){
            left_max = Math.max(left_max,height[i-1]);
            int high = Math.min(left_max,right_max[i]);
            high = high - height[i];
            if(high>0){
                sum = sum + high;
            }
        }
        return sum;
    }

    public int maxProfit(int[] prices) {

        int index = prices[0];

        int max = 0;
        for(int i = 0;i<prices.length;i++){

            max = Math.max(max,prices[i] - index);
            index = Math.min(index,prices[i]);
        }

        return max;

    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
       ListNode res = new ListNode(-1);
       res.next = head;
       ListNode low = res;
       ListNode high = head;
       for(int i = 1;i<m;i++){
            low = low.next;
            high = high.next;
       }
       for(int i = 1;i<n-m;i++){
            ListNode tmp = high.next;
            high.next = tmp.next;
            tmp.next = low.next;
            low.next = tmp;
       }

       return res.next;


    }

    public int[] guiBin(int[] nums){
        if(nums.length == 1){
            return nums;
        }
        int mid = nums.length / 2;
        int[] nums_1 = Arrays.copyOfRange(nums,0,mid);
        int[] nums_2 = Arrays.copyOfRange(nums,mid,nums.length);
        guiBin(nums_1);
        guiBin(nums_2);
        //将两个排好序的数组合并
        return megreArrays(nums_1,nums_2);
    }

    private int[] megreArrays(int[] nums_1, int[] nums_2){
        int[] res = new int[nums_1.length + nums_2.length];
        System.arraycopy(nums_1,0,res,0, nums_1.length);
        System.arraycopy(nums_2,0,res,nums_1.length,nums_2.length);
        Arrays.sort(res);
        return res;
    }
    //两个节点只能是三种情况
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root == q || root == null){
            return root;
        }
        TreeNode tmp1 = lowestCommonAncestor(root.left,p,q);
        TreeNode tmp2 = lowestCommonAncestor(root.right,p,q);
        if(tmp1 != null && tmp2 != null){
            return root;
        }else {
            if(tmp1 == null){
                return tmp2;
            }else {
                return tmp1;
            }
        }

    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer,Integer> map = new HashMap<>();
        //保存前序遍历
        for(int i = 0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return buildTree(map,inorder,postorder,0,inorder.length-1,0,postorder.length-1);
    }

    private TreeNode buildTree(Map<Integer,Integer> map,int[] inorder, int[] postorder,int in_pre,int in_tail ,int post_pre,int post_tail) {
        if(in_pre > in_tail || post_pre > post_tail){
            return null;
        }

        int index = map.get(postorder[post_tail]);
        TreeNode node = new TreeNode(postorder[post_tail]);
        node.left = buildTree(map,inorder,postorder,in_pre,index-1,post_pre,post_tail-(in_tail - index)-1);
        node.right = buildTree(map,inorder,postorder,index+1,in_tail,post_tail-(in_tail - index),post_tail-1);

        return node;

    }


    //动态规划解决拿Nim问题
    public boolean canWinNim(int n) {
        if(n%4 == 0){
            return false;
        }else {
            return true;
        }
    }

    //N皇后问题，追溯法
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new LinkedList<>();
        char[][] chars = new char[n][n];
        //初始化
        for(int i = 0;i<chars.length;i++){
            for(int j = 0;j<chars[0].length;j++){
                chars[i][j] = '.';
            }
        }
        solve(res,chars,0);
        return res;
    }

    private void solve(List<List<String>> res,char[][] chars,int row){
        if(row == chars.length){
            res.add(construct(chars));
            return;
        }
        for(int i = 0;i<chars.length;i++){
            if(valid(chars,row,i)){
                chars[row][i] = 'Q';
                solve(res,chars,row+1);
                chars[row][i] = '.';
            }
        }
    }

    private  boolean valid(char[][] chars,int row,int col){
        //确定row行里没有Q
        for(int i = 0;i<chars.length;i++){
            if(chars[row][i] == 'Q'){
                return false;
            }
        }
        //判断当前col列有没有Q
        for(int i = 0;i<chars.length;i++){
            if(chars[i][col] == 'Q'){
                return false;
            }
        }
        //判断右上角有没有Q
        for(int i = row,j = col;i>=0 && j<chars.length;i--,j++){
            if(chars[i][j] == 'Q'){
                return false;
            }
        }
        //判断左上角线有没有Q
        for(int i =row,j=col;i>=0 && j>=0;i--,j--){
            if(chars[i][j] == 'Q'){
                return false;
            }
        }

        return true;
    }

    private List<String> construct(char[][] chars){
        List<String> tmp = new LinkedList<>();
        for(int i = 0;i<chars.length;i++){
            tmp.add(new String(chars[i]));
        }
        return tmp;
    }

    public void fanZhuan(int[][] nums){
        for(int i = 0;i<nums.length;i++){
            for(int j = 0;j<nums.length;j++){
                int tmp = nums[i][j];
                nums[i][j] = nums[j][i];
                nums[j][i] = tmp;
            }
        }
    }


    //单调栈
    public String removeKdigits(String num, int k) {
        if(num.length() == k){
        return "0";
        }


        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for(int i = 0;i<num.length();i++){
            if(count >= k){
                stack.push(num.charAt(i));
            }else {
                if(stack.empty() && num.charAt(i) != '0'){
                    stack.push(num.charAt(i));
                }else {
                    if(stack.peek() <= num.charAt(i)){
                        stack.push(num.charAt(i));
                    }else {
                        stack.pop();
                        count++;
                        stack.push(num.charAt(i));
                    }
                }
            }
        }

        while (!stack.empty()) {
            sb.append(stack.pop());
        }
        String res = sb.reverse().substring(0,num.length()-k);
        return res;
    }






}
