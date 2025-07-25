import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

// Post class: Stores content, time, views, and comments
class Post {
    String author;
    String text;
    LocalDateTime timePosted;
    List<LocalDateTime> views = new ArrayList<>();
    List<LocalDateTime> comments = new ArrayList<>();

    public Post(String author, String text, String timePosted) {
        this.author = author;
        this.text = text;
        this.timePosted = LocalDateTime.parse(timePosted);
    }

    void addView(String time) {
        views.add(LocalDateTime.parse(time));
    }

    void addComment(String time) {
        comments.add(LocalDateTime.parse(time));
    }

    double getTrendScore(LocalDateTime now) {
        long hours = Duration.between(timePosted, now).toHours();
        if (hours <= 0) hours = 1; // avoid division by zero or negative
        int score = views.size() + 2 * comments.size(); // weighting comments
        return (double) score / hours;
    }
}

// User class: Stores user attributes and posts
class User {
    String name;
    String gender;
    String region;
    List<Post> posts = new ArrayList<>();

    public User(String name, String gender, String region) {
        this.name = name;
        this.gender = gender;
        this.region = region;
    }

    Post writePost(String text, String time) {
        Post post = new Post(name, text, time);
        posts.add(post);
        return post;
    }
}

// Main class: Finds trending posts
public class TrendingFinder {
    public static void main(String[] args) {
        Map<String, User> users = new HashMap<>();

        // Create users
        users.put("alice", new User("alice", "female", "North"));
        users.put("bob", new User("bob", "male", "South"));
        users.put("carol", new User("carol", "female", "East"));

        // Sample posts with views and comments
        Post p1 = users.get("alice").writePost("Try this new recipe!", "2024-07-01T08:00:00");
        p1.addView("2024-07-01T09:00:00");
        p1.addComment("2024-07-01T09:10:00");
        p1.addView("2024-07-02T08:00:00");

        Post p2 = users.get("bob").writePost("Big game tonight", "2024-07-02T12:00:00");
        p2.addView("2024-07-02T12:30:00");
        p2.addView("2024-07-03T10:00:00");

        Post p3 = users.get("carol").writePost("Summer travel plans!", "2024-07-03T14:00:00");
        p3.addComment("2024-07-03T15:00:00");

        // Simulated "current time"
        LocalDateTime now = LocalDateTime.of(2024, 7, 4, 12, 0, 0);

        // Filter and collect trending posts
        List<Post> trending = new ArrayList<>();
        for (User user : users.values()) {
            if (!user.gender.equalsIgnoreCase("female") || !user.region.equalsIgnoreCase("North")) continue;
            for (Post post : user.posts) {
                String text = post.text.toLowerCase();
                if (!text.contains("recipe") || text.contains("sports")) continue;
                trending.add(post);
            }
        }

        // Sort posts by trending score
        trending.sort((a, b) -> Double.compare(b.getTrendScore(now), a.getTrendScore(now)));

        // Print results
        System.out.println("Trending Posts:");
        if (trending.isEmpty()) {
            System.out.println("No trending posts found with the given filters.");
        } else {
            for (Post post : trending) {
                System.out.printf("%s – \"%s\" → score: %.2f\n", post.author, post.text, post.getTrendScore(now));

            }
        }
    }
}
