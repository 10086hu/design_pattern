public abstract class CommentHandler {
    protected CommentHandler nextHandler;

    public void setNext(CommentHandler handler) {
        this.nextHandler = handler;
    }

    public abstract boolean handle(String content);

    protected boolean handleNext(String content) {
        if (nextHandler != null) {
            return nextHandler.handle(content);
        }
        return true;
    }
} 