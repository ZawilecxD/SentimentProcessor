import java.util.List;

/**
 * Created by Murzynas on 2017-04-27.
 */
public class Main {


    public static void main(String[] args) {
        int port = Integer.valueOf(args[0]);
        int offset = Integer.valueOf(args[1]);
        System.out.println("Starting for port "+args[0]+" and offset "+offset);
        DatabaseManager dbManager = new DatabaseManager();
        ApiConnector apiConnector = new ApiConnector(port);

//        int offset = 0;
//        List<Post> postPack;
//        do {
//            postPack = dbManager.getPosts(offset);
//            for(Post post : postPack) {
//                apiConnector.postText(post);
//            }
//            offset += DatabaseManager.SELECT_LIMIT;
//        } while(!postPack.isEmpty());

        List<Post> postPack = dbManager.getPosts(offset);
        for(Post post : postPack) {
            apiConnector.postText(post);
        }

    }

}
