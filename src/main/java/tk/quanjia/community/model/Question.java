package tk.quanjia.community.model;

public class Question {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_QUESTION.ID
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_QUESTION.TITLE
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_QUESTION.GMT_CREATE
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_QUESTION.GMT_MODIFIED
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_QUESTION.CREATOR
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    private Long creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    private Integer commentCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_QUESTION.VIEW_COUNT
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    private Integer viewCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_QUESTION.LIKE_COUNT
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    private Integer likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_QUESTION.TAG
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    private String tag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TABLE_QUESTION.DESCRIPTION
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_QUESTION.ID
     *
     * @return the value of TABLE_QUESTION.ID
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_QUESTION.ID
     *
     * @param id the value for TABLE_QUESTION.ID
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_QUESTION.TITLE
     *
     * @return the value of TABLE_QUESTION.TITLE
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_QUESTION.TITLE
     *
     * @param title the value for TABLE_QUESTION.TITLE
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_QUESTION.GMT_CREATE
     *
     * @return the value of TABLE_QUESTION.GMT_CREATE
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_QUESTION.GMT_CREATE
     *
     * @param gmtCreate the value for TABLE_QUESTION.GMT_CREATE
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_QUESTION.GMT_MODIFIED
     *
     * @return the value of TABLE_QUESTION.GMT_MODIFIED
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_QUESTION.GMT_MODIFIED
     *
     * @param gmtModified the value for TABLE_QUESTION.GMT_MODIFIED
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_QUESTION.CREATOR
     *
     * @return the value of TABLE_QUESTION.CREATOR
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_QUESTION.CREATOR
     *
     * @param creator the value for TABLE_QUESTION.CREATOR
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_QUESTION.COMMENT_COUNT
     *
     * @return the value of TABLE_QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_QUESTION.COMMENT_COUNT
     *
     * @param commentCount the value for TABLE_QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_QUESTION.VIEW_COUNT
     *
     * @return the value of TABLE_QUESTION.VIEW_COUNT
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_QUESTION.VIEW_COUNT
     *
     * @param viewCount the value for TABLE_QUESTION.VIEW_COUNT
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_QUESTION.LIKE_COUNT
     *
     * @return the value of TABLE_QUESTION.LIKE_COUNT
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_QUESTION.LIKE_COUNT
     *
     * @param likeCount the value for TABLE_QUESTION.LIKE_COUNT
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_QUESTION.TAG
     *
     * @return the value of TABLE_QUESTION.TAG
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_QUESTION.TAG
     *
     * @param tag the value for TABLE_QUESTION.TAG
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TABLE_QUESTION.DESCRIPTION
     *
     * @return the value of TABLE_QUESTION.DESCRIPTION
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TABLE_QUESTION.DESCRIPTION
     *
     * @param description the value for TABLE_QUESTION.DESCRIPTION
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}