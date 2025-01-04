public class ProfanityHandler extends CommentHandler {
    @Override
    public boolean handle(String content) {
        // 脏话检查逻辑
        if (containsProfanity(content)) {
            return false;
        }
        return handleNext(content);
    }
    
    private boolean containsProfanity(String content) {
        // 实现脏话检查逻辑
        return false;
    }
} 