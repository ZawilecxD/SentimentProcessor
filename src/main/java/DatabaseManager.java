import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murzynas on 2017-04-28.
 */
public class DatabaseManager {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/huffington";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "";

    public static final int SELECT_LIMIT = 1000;

    private final String SELECT_POSTS = "select id, content from posts order by id";


    public Connection createDatabaseConnection() {
        Connection c;
        try {
            Class.forName(DB_DRIVER);
            c = DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASS
            );

            c.setAutoCommit(false);
            return c;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    private String queryWithLimitAndOffset(String query, int offset) {
        return query + " limit "+SELECT_LIMIT + " OFFSET " + offset;
    }

    public List<Post> getPosts(int offset) {
        Instant startTime = Instant.now();
        String query = queryWithLimitAndOffset(SELECT_POSTS, offset);
        System.out.println("Executing: "+query);
        List<Post> posts = new ArrayList<>(SELECT_LIMIT);
        Connection c = createDatabaseConnection();
        try {
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                Post post = null;
                while(rs.next()){
                    post =  new Post(rs.getInt("id"), rs.getString("content"));
                    posts.add(post);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        Instant endTime = Instant.now();
        System.out.println("Collecting posts data, for offset='"+offset+"' took "+
                Duration.between(startTime, endTime).toMillis()+"ms. "+SELECT_LIMIT+" were collected.");
        return posts;
    }


}
