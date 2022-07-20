package tk.quanjia.community.model;

public class Comment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_COMMENT.ID
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_COMMENT.PARENT_ID
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_COMMENT.TYPE
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_COMMENT.COMMENTATOR
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    private Long commentator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_COMMENT.GMT_CREATE
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_COMMENT.GMT_MODIFIED
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_COMMENT.LIKE_COUNT
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    private Long likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_COMMENT.CONTENT
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_COMMENT.COMMENT_COUNT
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    private Integer commentCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_COMMENT.ID
     *
     * @return the value of TABLE_COMMENT.ID
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_COMMENT.ID
     *
     * @param id the value for TABLE_COMMENT.ID
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_COMMENT.PARENT_ID
     *
     * @return the value of TABLE_COMMENT.PARENT_ID
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_COMMENT.PARENT_ID
     *
     * @param parentId the value for TABLE_COMMENT.PARENT_ID
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_COMMENT.TYPE
     *
     * @return the value of TABLE_COMMENT.TYPE
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_COMMENT.TYPE
     *
     * @param type the value for TABLE_COMMENT.TYPE
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_COMMENT.COMMENTATOR
     *
     * @return the value of TABLE_COMMENT.COMMENTATOR
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public Long getCommentator() {
        return commentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_COMMENT.COMMENTATOR
     *
     * @param commentator the value for TABLE_COMMENT.COMMENTATOR
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public void setCommentator(Long commentator) {
        this.commentator = commentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_COMMENT.GMT_CREATE
     *
     * @return the value of TABLE_COMMENT.GMT_CREATE
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_COMMENT.GMT_CREATE
     *
     * @param gmtCreate the value for TABLE_COMMENT.GMT_CREATE
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_COMMENT.GMT_MODIFIED
     *
     * @return the value of TABLE_COMMENT.GMT_MODIFIED
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_COMMENT.GMT_MODIFIED
     *
     * @param gmtModified the value for TABLE_COMMENT.GMT_MODIFIED
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_COMMENT.LIKE_COUNT
     *
     * @return the value of TABLE_COMMENT.LIKE_COUNT
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public Long getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_COMMENT.LIKE_COUNT
     *
     * @param likeCount the value for TABLE_COMMENT.LIKE_COUNT
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_COMMENT.CONTENT
     *
     * @return the value of TABLE_COMMENT.CONTENT
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_COMMENT.CONTENT
     *
     * @param content the value for TABLE_COMMENT.CONTENT
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_COMMENT.COMMENT_COUNT
     *
     * @return the value of TABLE_COMMENT.COMMENT_COUNT
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_COMMENT.COMMENT_COUNT
     *
     * @param commentCount the value for TABLE_COMMENT.COMMENT_COUNT
     *
     * @mbg.generated Wed Jul 20 14:52:16 CST 2022
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}