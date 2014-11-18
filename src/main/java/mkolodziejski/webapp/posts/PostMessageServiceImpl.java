package mkolodziejski.webapp.posts;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PostMessageServiceImpl implements PostMessagesService {

    @Inject
    private Provider<Connection> connectionProvider;

    @Override
    public List<WallPost> getWallPosts(Integer userAccountId) {
        Preconditions.checkNotNull(userAccountId);

        List<WallPost> resultList = Lists.newArrayList();

        Connection connection = connectionProvider.get();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(
                    "select " +
                            "post_id, " +
                            "post_user_id, " +
                            "post_content, " +
                            "post_insert_ts, " +
                            "post_user_full_name, " +
                            "wall_user_id " +
                    "from wall_posts_view " +
                    "where wall_user_id = " + userAccountId.toString() + " " +
                    "order by post_insert_ts desc");

            ResultSet rs = stmt.getResultSet();

            while(rs.next()) {
                WallPost wallPost = new WallPost();
                wallPost.setPostId(rs.getInt("post_id"));
                wallPost.setPostUserId(rs.getInt("post_user_id"));
                wallPost.setPostContent(rs.getString("post_content"));
                wallPost.setPostInsertTs(rs.getTimestamp("post_insert_ts"));
                wallPost.setPostUserFullName(rs.getString("post_user_full_name"));
                wallPost.setWallUserId(rs.getInt("wall_user_id"));

                resultList.add(wallPost);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }

    @Override
    public void insertPostMessage(Integer userAccountId, String content) {
        Preconditions.checkNotNull(userAccountId);
        Preconditions.checkNotNull(content);

        Connection connection = connectionProvider.get();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("insert into post_message (id, user_account_id, content, insert_ts) values " +
                    "(nextval('post_message_seq'), " +
                    userAccountId.toString() + ", " +
                    "'" + content + "', " +
                    "NOW())");

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
