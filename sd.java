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
        
        
        String[] regions = {"INDIA", "EUROPE", "ASIA", "AUSTRALIA"};
        int currentRegionIndex = 0;
        String currentRegion = regions[currentRegionIndex];
        
        while (true) {
            LocalTime time = LocalTime.now();
            LocalDate date = LocalDate.now();
            String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String formattedWeekday = date.format(DateTimeFormatter.ofPattern("EEEE"));
            String formattedMonth = date.format(DateTimeFormatter.ofPattern("MMMM"));
            String formattedYear = date.format(DateTimeFormatter.ofPattern("yyyy"));
            panel.setTime(formattedMonth, formattedWeekday, formattedYear, formattedTime, currentRegion);
            TimeUnit.SECONDS.sleep(1);
            panel.repaint();
            
            
            if (time.getMinute() == 0 && time.getSecond() == 0) {
                currentRegionIndex = (currentRegionIndex + 1) % regions.length;
                currentRegion = regions[currentRegionIndex];
            }
        }
    }
}

class ClockPanel extends JPanel {
    private String time;
    private String weekday;
    private String month;
    private String year;
    private String region;

    public ClockPanel() {
        setPreferredSize(new Dimension(400, 190));
        setBackground(Color.BLACK);
        setFont(new Font("Arial", Font.BOLD, 32));
        setForeground(Color.GREEN);
    }

    public void setTime(String weekday, String month, String year, String time, String region) {
        this.weekday = weekday;
        this.month = month;
        this.year = year;
        this.time = time;
        this.region = region;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString(weekday, 50, 50);
        g.drawString(month, 50, 90);
        g.drawString(year, 50, 130);
        g.drawString(time, 200, 90);
        g.setColor(Color.WHITE);
        g.drawLine(0, 0, getWidth(), 0);
        g.drawLine(0, 0, 0, getHeight());
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());
        g.drawString(region, getWidth() - 150, 50);
    }
}
