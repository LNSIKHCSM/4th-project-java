import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class sd {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        ClockPanel panel = new ClockPanel();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
      
        
        while (true) {
            LocalTime time = LocalTime.now();
            LocalDate date = LocalDate.now();
            String formattedTime = time.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
            String formattedWeekday = date.format(DateTimeFormatter.ofPattern("EEEE"));
            String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            panel.setTime(formattedDate, formattedWeekday, formattedTime);
            TimeUnit.SECONDS.sleep(1);
            panel.repaint();
            
            
           
        }
    }
}

class ClockPanel extends JPanel {
    private String time;
    private String weekday;
    private String date;
    

    public ClockPanel() {
        setPreferredSize(new Dimension(400, 190));
        setBackground(Color.BLACK);
        setFont(new Font("Arial", Font.BOLD, 32));
        setForeground(Color.GREEN);
    }

    public void setTime(String weekday, String date, String time) {   
       
        this.weekday = "Todays date is " + weekday;
        this.date = "Today's day is: " + date;
        this.time = time;
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString(date, 50, 50);
        g.drawString(time, 50, 80);
        g.drawString(weekday, 50, 110);
        g.setColor(Color.GREEN);
        g.drawLine(0, 0, getWidth(), 0);
        g.drawLine(0, 0, 0, getHeight());
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());
        g.setFont(new Font("Arial", Font.BOLD, 18));
       
    }
}
