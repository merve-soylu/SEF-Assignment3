import java.util.ArrayList;
import java.time.LocalDate;

//selection of search filters user can select from to sort order of posts in search forum
enum Filter {
    DEFAULT,
    UPVOTES,
    DOWNVOTES,
    OLDEST,
    NEWEST,
}

//mock database which is storing all Posts on the Code QA platform
public static class Database {
    ArrayList<Post> Posts; 
    
    //creates empty list for results
    ArrayList<Post> searchResults = new ArrayList<>();

    //returns whether database found results that are relevant to the inquiry
    public boolean postsFound(ArrayList<Post> findPostInSearchForum) {
        if (findPostInSearchForum != null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    //returns a List of Posts that match a search query
    public ArrayList<Post> findPostInSearchForum(String query, Filter selectedFilter, ArrayList<Post> searchResults) {
        
        //checks through all Posts in the database
        for (Post post : Database.Posts) {
            //if it finds a match to the query
            if (post.content.contains(query)) {
                //adds that post to the list of results
                searchResults.add(post);
            }
        }

        //Sort Search Results according to the users Selected Filter
        if (selectedFilter == Filter.UPVOTES){
            //sort the search results by highest Upvote count to lowest Upvote count
            for (Post post : searchResults){
                //update the searchResults ArrayList
            }
        }
        else if (selectedFilter == Filter.DOWNVOTES){
            //sort the search results by highest Downvote count to lowest Downvote count
            for (Post post : searchResults){
                //update the searchResults ArrayList
            }
        }
        else if (selectedFilter == Filter.OLDEST) {
            //sort the search results showing oldest posts first
            for (Post post : searchResults) {
                //update the searchResults ArrayList
            }
        }

        else if (selectedFilter == Filter.NEWEST) {
            //sort the search results by showing newest posts first
            for (Post post : searchResults) {
                //update the searchResults ArrayList
            }
        }
        
        //return the search results
        return searchResults;
    }
}

//User Class
public class User {
    String username;
    String password;
    ArrayList<Post> UserPosts = new ArrayList<>();

}

//Post Class
public class Post {
    int postID;
    User author;
    String content;
    int upvote;
    int downvote;
    boolean flagged = false;
    // date of creation post date initalised as LocalDate postDate = LocallDate.of(YYYY, MM, DD)
    LocalDate postDate;

    //creates a post object
    Post (int postID, User user, String content, LocalDate postDate){
        this.postID = postID;
        this.author = user;
        this.content = content;
        this.postDate = postDate;
        //stores created Post in Database
        Database.Posts.add(this);
    }

    public void deletePost(){
        //updates content and author in the Database for deleted post and for when it's displayed on Search Forum Overview
        for (Post post : Database.Posts){
            if (this.postID == post.postID){
                post.content = "Content of Question has been removed";
                post.author = Null;
                post.postDate = this.postDate;
            }
        }
    }

    public void flagPost(){
        //updates flagged in the Database for flagged post and for when it's displayed on Search Forum Overview
        for (Post post : Database.Posts){
            if (this.postID == post.postID){
                post.flagged = true;
            }
        }
    }

}

public static class SearchForum {
        //ArrayList of all stored Posts
        public static ArrayList<Post> searchResults = new ArrayList<Post>();

        //gets search results from the Search Forum class
        ArrayList<Post> searchResults = DataBase.findPostInSearchForum(userInput, selectedFilter);
}


public static class WebsiteUI {
    //user inputs a search keyword/phrase and selects a search Filter
    public void searchInquiry(String userInput, Filter selectedFilter){
       
        //displays results to the user
        displayResults(SearchForum.searchResults(userInput, selectedFilter));
    }

    //takes in a list of Posts
    public void displayResults(ArrayList<Post> searchResults){
        
        System.out.println("Search Results: ");
        //if the list of posts is not null
        if (database.postFound(searchInquiry)){
            //Display each Post in the list
            for (Post post : searchResults) {
                System.out.println("Author: " + post.author);
                System.out.println("Content: " + post.content);
                System.out.println("Date Posted: " + post.postDate);
            }
            System.out.println();
        }
        //else returns error message of no results found
        else System.out.println("No Results found! Please try again with more specific keywords");
    }
    
}
