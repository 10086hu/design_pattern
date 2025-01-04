public class SensitiveWordHandler extends CommentHandler {
    @Override
    public boolean handle(String content) {
        // 处理当前逻辑
        if (content.contains("敏感词")) {
            return false; // 验证失败，直接返回
        }
        // 传递给下一个处理器
        return handleNext(content);
    }
} 