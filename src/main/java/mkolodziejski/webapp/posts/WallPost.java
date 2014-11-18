package mkolodziejski.webapp.posts;

import java.sql.Timestamp;

public class WallPost {

    private Integer postId;
    private Integer postUserId;
    private String postContent;
    private Timestamp postInsertTs;
    private String postUserFullName;
    private Integer wallUserId;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(Integer postUserId) {
        this.postUserId = postUserId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Timestamp getPostInsertTs() {
        return postInsertTs;
    }

    public void setPostInsertTs(Timestamp postInsertTs) {
        this.postInsertTs = postInsertTs;
    }

    public String getPostUserFullName() {
        return postUserFullName;
    }

    public void setPostUserFullName(String postUserFullName) {
        this.postUserFullName = postUserFullName;
    }

    public Integer getWallUserId() {
        return wallUserId;
    }

    public void setWallUserId(Integer wallUserId) {
        this.wallUserId = wallUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WallPost wallPost = (WallPost) o;

        if (!postContent.equals(wallPost.postContent)) return false;
        if (!postId.equals(wallPost.postId)) return false;
        if (!postInsertTs.equals(wallPost.postInsertTs)) return false;
        if (!postUserFullName.equals(wallPost.postUserFullName)) return false;
        if (!postUserId.equals(wallPost.postUserId)) return false;
        if (!wallUserId.equals(wallPost.wallUserId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = postId.hashCode();
        result = 31 * result + postUserId.hashCode();
        result = 31 * result + postContent.hashCode();
        result = 31 * result + postInsertTs.hashCode();
        result = 31 * result + postUserFullName.hashCode();
        result = 31 * result + wallUserId.hashCode();
        return result;
    }
}
