// By Alana Wesly, ICS 311 Assignment 6 Social Media Networks. 07/25/2025
import java.time.LocalDateTime;
import java.util.*;

public class ImportantDiagram {
    public static void main(String[] args) {
        
        // Test code for the Post and User classes from group member Trevell Pruitt
        // Create a map to hold users
        Map<String, User> users = new HashMap<>();

        // New variables to hold the total view and comment counts, and sorted list added by Alana Wesly
        List<Post> importantPosts = new ArrayList<>();
        // Select which importance mode to use for sorting posts added by Alana Wesly
        String importanceMode = "blend"; // Options: views, comments, blend
    
        // Create users
        users.put("alice", new User("alice", "female", "North"));
        users.put("bob", new User("bob", "male", "South"));
        users.put("carol", new User("carol", "female", "East"));

        // Sample posts with views and comments - edited by Alana Wesly to have more varying view and comment counts, and no more comments than views
        // Sample posts with consistent logic: no more comments than views

        // Sample posts with consistent logic: no more comments than views

        // Post 1: 2 views, 1 comment
        Post p1 = users.get("alice").writePost("Try this new recipe!", "2024-07-01T08:00:00");
        p1.addView("2024-07-01T09:00:00", "bob");
        p1.addView("2024-07-01T09:10:00", "carol");
        p1.addComment("2024-07-01T09:20:00", "bob");

        // Post 2: 3 views, 2 comments
        Post p2 = users.get("bob").writePost("Big game tonight", "2024-07-02T12:00:00");
        p2.addView("2024-07-02T12:30:00", "alice");
        p2.addView("2024-07-02T13:00:00", "carol");
        p2.addView("2024-07-02T13:30:00", "bob");
        p2.addComment("2024-07-02T14:00:00", "carol");
        p2.addComment("2024-07-02T14:30:00", "alice");

        // Post 3: 4 views, 0 comments
        Post p3 = users.get("carol").writePost("Summer travel plans!", "2024-07-03T14:00:00");
        p3.addView("2024-07-03T14:10:00", "alice");
        p3.addView("2024-07-03T14:20:00", "bob");
        p3.addView("2024-07-03T14:30:00", "carol");
        p3.addView("2024-07-03T14:40:00", "bob"); // repeated viewer with new time

        // Simulated "current time"
        LocalDateTime now = LocalDateTime.of(2024, 7, 4, 12, 0, 0);

        // by Alana Wesly
        // Add all the posts to the importantPosts list
        importantPosts.addAll(Arrays.asList(p1, p2, p3));
        // Sort by selected importance metric using algorithm in Post class
        importantPosts.sort((a, b) -> Double.compare(
            b.getImportanceScore(importanceMode, now),
            a.getImportanceScore(importanceMode, now)
        ));
        // Display sorted posts and their users (who posted them, and who viewed and commented)
        System.out.println("Important Posts (sorted by " + importanceMode + "):\n");
        for (Post post : importantPosts) {
            System.out.println(post);
            System.out.println("Views: " + post.viewers.size() + " " + post.viewers);
            System.out.println("Comments: " + post.commenters.size() + " " + post.commenters);
            System.out.println();
        }


    }
}
