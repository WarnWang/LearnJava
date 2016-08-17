package LeetCode.DesignPuzzles;

import java.util.*;

/**
 * Created by warn on 17/8/2016.
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
 * <p>
 * postTweet(userId, tweetId): Compose a new tweet.
 * getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
 * follow(followerId, followeeId): Follower follows a followee.
 * unfollow(followerId, followeeId): Follower unfollows a followee.
 * Example:
 * <p>
 * Twitter twitter = new Twitter();
 * <p>
 * // User 1 posts a new tweet (id = 5).
 * twitter.postTweet(1, 5);
 * <p>
 * // User 1's news feed should return a list with 1 tweet id -> [5].
 * twitter.getNewsFeed(1);
 * <p>
 * // User 1 follows user 2.
 * twitter.follow(1, 2);
 * <p>
 * // User 2 posts a new tweet (id = 6).
 * twitter.postTweet(2, 6);
 * <p>
 * // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 * // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.getNewsFeed(1);
 * <p>
 * // User 1 unfollows user 2.
 * twitter.unfollow(1, 2);
 * <p>
 * // User 1's news feed should return a list with 1 tweet id -> [5],
 * // since user 1 is no longer following user 2.
 * twitter.getNewsFeed(1);
 */
public class Twitter {

    private HashMap<Integer, HashSet<Integer>> following;
    private HashMap<Integer, List<Integer>> post;
    private ArrayList<Integer> messege;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        following = new HashMap<>();
        post = new HashMap<>();
        messege = new ArrayList<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        if (post.containsKey(userId)) post.get(userId).add(tweetId);
        else post.put(userId, new ArrayList<>(Collections.singletonList(tweetId)));
        messege.add(tweetId);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        HashSet<Integer> news = new HashSet<>();
        if (following.containsKey(userId)) {
            for (int i : following.get(userId)) if (post.containsKey(i)) news.addAll(post.get(i));
        }
        if (post.containsKey(userId)) news.addAll(post.get(userId));

        List<Integer> newsFeed = new ArrayList<>();
        for (int i = messege.size() - 1; i >= 0; i--) {
            int n = messege.get(i);
            if (news.contains(n)) {
                newsFeed.add(n);
                if (newsFeed.size() == 10) break;
            }
        }
        return newsFeed;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (following.containsKey(followerId)) following.get(followerId).add(followeeId);
        else following.put(followerId, new HashSet<>(Collections.singletonList(followeeId)));
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (following.containsKey(followerId))
            if (following.get(followerId).contains(followeeId)) following.get(followerId).remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */