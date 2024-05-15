import java.util.ArrayList;

class SearchForum {

    //returns a List of Posts that match a search query
    ArrayList<Post> searchInquiry(String query){
        
        //creates empty list for results
        ArrayList<Post> searchResults = new ArrayList<>();
        
        //checks through all Posts in the database
        for (Post post : Database.Posts) {
            //if it finds a match to the query
            if (post.content.contains(query)) {
                //adds that post to the list of results
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

    //creates a post object and stores it in the Database
    Post (int postID, User user, String content){
        this.postID = postID;
        this.author = user;
        this.content = content;

        Database.Posts.add(this);
    }

    void deletePost(){
        
        content = "Content has been removed";
        author = null;

        //updates content and author in the Database
        for (Post post : Database.Posts){
            if (this.postID == post.postID){
                post.content = this.content;
                post.author = this.author;
            }
        }
    }

    void flagPost(){
        flagged = true;

        //updates flagged in the database
        for (Post post : Database.Posts){
            if (this.postID == post.postID){
                post.flagged = this.flagged;
            }
        }
    }

}

//mock database which is storing all Posts on the Code QA platform
class Database {
    //list of all stored Posts
    static ArrayList<Post> Posts = new ArrayList<>();

}

class WebsiteUI {

    //takes in a list of results from a searchInquiry
    void displayResults(ArrayList<Post> searchResults){
        System.out.println("Search Results: ");
        //if the results are not null
        if (searchResults != null){
            //returns each result
            for (Post post : searchResults) {
                System.out.println("Author: " + post.author);
                System.out.println("Content: " + post.content);
            }
            System.out.println();
        }
        //else returns error message of no results found
        else System.out.println("No Results found! Please try again with more specific keywords");
    }
    
    
}
