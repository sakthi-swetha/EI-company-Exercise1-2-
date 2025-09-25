package structural.decorator;

public class EmojiDecorator extends MessageDecorator {
    public EmojiDecorator(Message message) { super(message); }
    @Override
    public String send() { return message.send() + " ***"; }
}
