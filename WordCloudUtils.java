import java.util.*;


/**
 * Utility class for generating word clouds from social media posts.
 */
public class WordCloudUtils {


    /**
     * Filters posts by multiple criteria: inclusion/exclusion keywords, gender, age, and location.
     * @param users List of users
     * @param includeKeywords List of keywords to include (null or empty for no filter)
     * @param excludeKeywords List of keywords to exclude (null or empty for no filter)
     * @param genderFilter Gender to filter (null for no filter)
     * @param minAge Minimum age (null for no filter)
     * @param maxAge Maximum age (null for no filter)
     * @param locationFilter Location to filter (null for no filter)
     * @return List of filtered posts
     */
    public static List<Post> filterPosts(List<User> users, List<String> includeKeywords, List<String> excludeKeywords,
                                        String genderFilter, Integer minAge, Integer maxAge, String locationFilter) {
        List<Post> filtered = new ArrayList<>();

        for (User user : users) {
            if (genderFilter != null && !user.gender.equalsIgnoreCase(genderFilter)) continue;
            if (minAge != null && user.age < minAge) continue;
            if (maxAge != null && user.age > maxAge) continue;
            if (locationFilter != null && !user.location.equalsIgnoreCase(locationFilter)) continue;

            for (Post post : user.posts) {
                String content = post.content.toLowerCase();
                boolean include = (includeKeywords == null || includeKeywords.isEmpty()) ||
                        includeKeywords.stream().anyMatch(k -> content.contains(k.toLowerCase()));
                boolean exclude = (excludeKeywords != null && !excludeKeywords.isEmpty()) &&
                        excludeKeywords.stream().anyMatch(k -> content.contains(k.toLowerCase()));
                if (include && !exclude) {
                    filtered.add(post);
                }
            }
        }
        return filtered;
    }

    /**
     * Counts word frequencies in a list of posts, ignoring common stop words.
     * @param posts List of posts
     * @return Map of word to frequency
     */
    public static Map<String, Integer> countWordFrequencies(List<Post> posts) {
        Set<String> stopWords = Set.of("the", "is", "a", "and", "of", "to", "in", "for", "with");
        Map<String, Integer> freq = new HashMap<>();

        for (Post post : posts) {
            String[] words = post.content.toLowerCase().split("\\W+");
            for (String word : words) {
                if (!stopWords.contains(word) && !word.isEmpty()) {
                    freq.put(word, freq.getOrDefault(word, 0) + 1);
                }
            }
        }
        return freq;
    }

    /**
     * Prints a simple word cloud to the console, with word size proportional to frequency.
     * @param wordFreq Map of word to frequency
     */
    public static void printWordCloud(Map<String, Integer> wordFreq) {
        wordFreq.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .forEach(entry -> {
                    System.out.printf("%-15s : %s%n", entry.getKey(), "*".repeat(entry.getValue()));
                });
    }
}
