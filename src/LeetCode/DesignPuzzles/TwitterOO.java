package LeetCode.DesignPuzzles;

import java.util.*;

/**
 * Created by warn on 18/8/2016.
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
public class TwitterOO {

    private int time;
    private HashMap<Integer, User> userHashMap;

    /**
     * Initialize your data structure here.
     */
    public TwitterOO() {
        time = 0;
        userHashMap = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        if (!userHashMap.containsKey(userId)) userHashMap.put(userId, new User(userId));
        userHashMap.get(userId).post(tweetId);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users
     * who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        if (!userHashMap.containsKey(userId)) return new ArrayList<>();
        HashSet<Integer> followList = userHashMap.get(userId).follows;
        ArrayList<Integer> newsFeed = new ArrayList<>();
        PriorityQueue<Message> messages = new PriorityQueue<>(followList.size(), (o1, o2) -> o2.timeStamp - o1.timeStamp);
        for (int user : followList) {
            if (userHashMap.get(user).root != null) messages.add(userHashMap.get(user).root);
        }

        while (!messages.isEmpty() && newsFeed.size() < 10) {
            Message topMessage = messages.poll();
            if (topMessage.next != null) messages.add(topMessage.next);
            newsFeed.add(topMessage.messageId);
        }
        return newsFeed;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (followeeId == followerId) return;
        if (!userHashMap.containsKey(followeeId)) userHashMap.put(followeeId, new User(followeeId));
        if (!userHashMap.containsKey(followerId)) userHashMap.put(followerId, new User(followerId));
        userHashMap.get(followerId).follow(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (!userHashMap.containsKey(followerId) || followeeId == followerId) return;
        userHashMap.get(followerId).unfollow(followeeId);
    }

    private class User {
        HashSet<Integer> follows;
        Message root;

        User(int userID) {
            follows = new HashSet<>(Collections.singletonList(userID));
            root = null;
        }

        void post(int messageId) {
            Message newMessage = new Message(messageId);
            newMessage.next = root;
            root = newMessage;
        }

        void follow(int userID) {
            follows.add(userID);
        }

        void unfollow(int userId) {
            if (follows.contains(userId)) follows.remove(userId);
        }
    }

    private class Message {
        int timeStamp;
        int messageId;
        Message next;

        Message(int id) {
            messageId = id;
            timeStamp = time++;
        }
    }
}
