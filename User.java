import java.util.ArrayList;
import java.util.List;


public class User {
    public String username;
    public int age;
    public String gender;
    public String location;
    public List<Post> posts;

    public User(String username, int age, String gender, String location) {
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.posts = new ArrayList<>();
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}
