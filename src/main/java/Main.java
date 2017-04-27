import java.util.List;

/**
 * Created by Murzynas on 2017-04-27.
 */
public class Main {

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        ApiConnector apiConnector = new ApiConnector();

        int offset = 0;
        List<Post> postPack;
        do {
            postPack = dbManager.getPosts(offset);
            for(Post post : postPack) {
                apiConnector.postText(post);
            }
            offset += DatabaseManager.SELECT_LIMIT;
        } while(!postPack.isEmpty());


    }

}
