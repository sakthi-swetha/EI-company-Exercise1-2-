package structural.decorator;

public class BasicMessage implements Message {
    private String content;
    public BasicMessage(String content) { this.content = content; }
    @Override
    public String send() { return content; }
}
