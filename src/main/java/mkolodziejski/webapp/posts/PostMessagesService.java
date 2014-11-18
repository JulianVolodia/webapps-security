package mkolodziejski.webapp.posts;

import java.util.List;

public interface PostMessagesService {

    List<WallPost> getWallPosts(Integer userAccountId);

    void insertPostMessage(Integer userAccountId, String content);
}
