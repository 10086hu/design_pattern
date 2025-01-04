public class LengthExpression implements Expression {
    private int minLength;
    private int maxLength;

    public LengthExpression(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public boolean interpret(String context) {
        return context.length() >= minLength && context.length() <= maxLength;
    }
} 