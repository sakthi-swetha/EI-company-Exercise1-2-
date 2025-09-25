package structural.decorator;

public class FileAttachmentDecorator extends MessageDecorator {
    private String fileName;
    public FileAttachmentDecorator(Message message, String fileName) {
        super(message);
        this.fileName = fileName;
    }

    @Override
    public String send() {
        return message.send() + " [Attached file: " + fileName + "]";
    }
}
