public class ContainsWordExpression implements Expression {
    private String word;

    public ContainsWordExpression(String word) {
        this.word = word;
    }

    @Override
    public boolean interpret(String context) {
        return context.contains(word);
    }
} 