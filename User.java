import java.util.*;

// User class from group member Trevell Pruitt stores user attributes and posts
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
