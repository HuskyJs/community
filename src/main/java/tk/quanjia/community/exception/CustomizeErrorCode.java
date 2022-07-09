package tk.quanjia.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001,"您的问题找不到了..."),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论..."),
    NO_LOGIN(2003,"没有登录，不能评论..."),
    SYS_ERROR(2004,"丢失啦，要不稍等再试..."),
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
