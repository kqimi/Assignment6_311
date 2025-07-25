import java.util.*;

public class WordCloudApp {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        User kimi = new User("kimli", 21, "female", "Hawaii");
        kimi.addPost(new Post("Save water for a better future!", "2025-07-25T14:00:00"));
        kimi.addPost(new Post("Water conservation is important.", "2025-07-26T10:00:00"));
        users.add(kimi);

        User justin = new User("justin", 24, "male", "California");
        justin.addPost(new Post("Rainwater harvesting can help the planet.", "2025-07-25T12:00:00"));
        justin.addPost(new Post("Water is essential for all life on Earth.", "2025-07-27T09:00:00"));
        users.add(justin);

        // Example: filter for posts that include 'water', exclude 'planet', by female users, age 18-30, in Hawaii
        List<String> includeKeywords = Arrays.asList("water");
        List<String> excludeKeywords = Arrays.asList("planet");
        String genderFilter = "male";
        Integer minAge = 18;
        Integer maxAge = 30;
        String locationFilter = "California";

        List<Post> filtered = WordCloudUtils.filterPosts(users, includeKeywords, excludeKeywords, genderFilter, minAge, maxAge, locationFilter);
        Map<String, Integer> freq = WordCloudUtils.countWordFrequencies(filtered);
        WordCloudUtils.printWordCloud(freq);
    }
}
