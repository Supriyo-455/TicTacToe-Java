import window.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        Thread mainThread = new Thread(window);
        mainThread.start();
    }
}
