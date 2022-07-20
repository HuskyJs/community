package tk.quanjia.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001,"您的问题找不到了..."),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论..."),
    NO_LOGIN(2003,"没有登录，不能评论..."),
    SYS_ERROR(2004,"丢失啦，要不稍等再试..."),
    TARGET_PARAM_WRONG(2005,"评论类型错误或不存在..."),
    COMMENT_NOT_FOUND(2006,"评论不存在..."),
    CONTENT_IS_EMPTY(2007, "输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008, "兄弟你这是读别人的信息呢？"),
    NOTIFICATION_NOT_FOUND(2009, "消息莫非是不翼而飞了？"),
    FILE_UPLOAD_FAIL(2010, "图片上传失败"),
    INVALID_INPUT(2011, "非法输入"),
    INVALID_OPERATION(2012, "兄弟，是不是走错房间了？"),
    USER_DISABLE(2013, "操作被禁用，如有疑问请联系管理员"),
    RATE_LIMIT(2014, "操作太快了，请稍后重试"),
    ;

    private String message;
    private Integer code;
    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
