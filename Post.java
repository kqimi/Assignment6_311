import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

// Post class from group member Trevell Pruitt stores content, time, views, and comments
class Post {
    String author;
    String text;
    LocalDateTime timePosted;
    List<LocalDateTime> views = new ArrayList<>();
    List<LocalDateTime> comments = new ArrayList<>();
    // added by Alana Wesly to track who viewed and commented
    List<String> viewers = new ArrayList<>();
    List<String> commenters = new ArrayList<>();

    public Post(String author, String text, String timePosted) {
        this.author = author;
        this.text = text;
        this.timePosted = LocalDateTime.parse(timePosted);
    }

    void addView(String time, String viewer) {
        views.add(LocalDateTime.parse(time));
        viewers.add(viewer); // added by Alana Wesly to track viewers
    }

    void addComment(String time, String commenter) {
        comments.add(LocalDateTime.parse(time));
        commenters.add(commenter); // added by Alana Wesly to track commenters
    }
    // used for Trevell Pruitt's algorithm
    double getTrendScore(LocalDateTime now) {
        long hours = Duration.between(timePosted, now).toHours();
        if (hours <= 0) hours = 1; // avoid division by zero or negative
        int score = views.size() + 2 * comments.size(); // weighting comments
        return (double) score / hours;
    }
    // for Alana Wesly's algorithm 
    double getImportanceScore(String mode, LocalDateTime now) {
        // add up views and comments totals
        int viewCount = views.size();
        int commentCount = comments.size();
        // calculate importance based on either views, comments, or both
        switch (mode) {
            case "views":
                return viewCount;
            case "comments":
                return commentCount;
            case "blend":
            default:
                long hours = Duration.between(timePosted, now).toHours();
                if (hours <= 0) hours = 1;
                return (viewCount + 2.0 * commentCount);  // Comments are weighted more heavily
        }
    }
}
