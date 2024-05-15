import java.util.ArrayList;

class SearchForum {

    ArrayList<Post> searchInquiry(String query){

        ArrayList<Post> searchResults = new ArrayList<>();
        
        for (Post post : Database.Posts) {
            if (post.content.contains(query)) {
                searchResults.add(post);
            }
        }

        return searchResults;
    }


    void filterResults(){
        //add filtering here
    }

}

class User {

    String username;
    String password;
    ArrayList<Post> UserPosts = new ArrayList<>();

}

class Post {

    int postID;
    User author;
    String content;
    int upvote;
    int downvote;
    boolean flagged = false;

    Post (int postID, User user, String content){
        this.postID = postID;
        this.author = user;
        this.content = content;

        Database.Posts.add(this);
    }

    void deletePost(int postID){
        content = "Content has been removed";
        author = null;
    }

    void flagPost(int postID){
        flagged = true;
    }

}

class Database {

    static ArrayList<Post> Posts = new ArrayList<>();

}

class WebsiteUI {

    void displayResults(ArrayList<Post> results){
        System.out.println("Search Results: ");
        if (results != null){
            for (Post post : results) {
                System.out.println("Author: " + post.author);
                System.out.println("Content: " + post.content);
            }
            System.out.println();
        }
        else System.out.println("No Results found! Please try again with more specific keywords");
    }
    
    
}
