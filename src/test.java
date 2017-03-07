import java.awt.EventQueue;

public class test {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OknoLogowania();
            }
        });
    }
}