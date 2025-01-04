public class ContentLengthHandler extends CommentHandler {
    @Override
    public boolean handle(String content) {
        // 内容长度检查
        if (content == null || content.length() < 1 || content.length() > 200) {
            return false;
        }
        return handleNext(content);
    }
} 