import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HexMinesweeperGUI {
    HexagonMinesweeperPanel HexPanel;
    private Timer gameTimer;
    private int secondsElapsed = 0;
    private JLabel timerLabel;
    private HexMineManager HMM;


    public HexMinesweeperGUI() {
        createAndShowGUI();
    }

    public static void main(String[] args) {
        new HexMinesweeperGUI();
    }

    private void createAndShowGUI() {
        JFrame mineFrame = new JFrame("Hexagonal Minesweeper");
        mineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mineFrame.setSize(1500, 900);
        mineFrame.setLocationRelativeTo(null);
        mineFrame.setResizable(false);
        mineFrame.getContentPane().setBackground(Color.WHITE);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(100, 600));
        rightPanel.setBackground(Color.GRAY.brighter());
        rightPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
                Color.BLACK, Color.BLACK));

        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.Y_AXIS));
        timerPanel.setPreferredSize(new Dimension(100, 50));
        timerPanel.setBackground(Color.GRAY.brighter());
        timerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
                Color.BLACK, Color.BLACK));

        JPanel flagsPanel = new JPanel();
        flagsPanel.setLayout(new BoxLayout(flagsPanel, BoxLayout.Y_AXIS));
        flagsPanel.setPreferredSize(new Dimension(100, 50));
        flagsPanel.setBackground(Color.GRAY.brighter());
        flagsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.BLACK));

        JPanel minesPanel = new JPanel();
        minesPanel.setLayout(new BoxLayout(minesPanel, BoxLayout.Y_AXIS));
        minesPanel.setPreferredSize(new Dimension(100, 50));
        minesPanel.setBackground(Color.GRAY.brighter());
        minesPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.BLACK));

        JPanel modePanel = new JPanel();
        modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));
        modePanel.setPreferredSize(new Dimension(100, 100));
        modePanel.setBackground(Color.GRAY.brighter());
        modePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.BLACK));

        HexPanel = new HexagonMinesweeperPanel();
        HexPanel.setPreferredSize(new Dimension(1250, 1000));
        HexPanel.setBackground(Color.WHITE);

        //Creates the radio buttons.
        JRadioButton easyMode = new JRadioButton("Easy");
        easyMode.setBackground(Color.GRAY.brighter());
        JRadioButton mediumMode = new JRadioButton("Medium");
        mediumMode.setBackground(Color.GRAY.brighter());
        JRadioButton hardMode = new JRadioButton("Hard");
        hardMode.setBackground(Color.GRAY.brighter());

        //Creates listeners for the radio buttons.
        easyMode.addActionListener(new ModeListener("Easy"));
        mediumMode.addActionListener(new ModeListener("Medium"));
        hardMode.addActionListener(new ModeListener("Hard"));

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(easyMode);
        modeGroup.add(mediumMode);
        modeGroup.add(hardMode);

        JLabel timerLabel = new JLabel("Timer: ");
        timerLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        JLabel flagsLabel = new JLabel("Flags: " + flagsPanel);
        flagsLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        HexPanel.setFlagsLabel(flagsLabel);

        JLabel minesLabel = new JLabel("Mines: " +  minesPanel);
        minesLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        HexPanel.setMinesLabel(minesLabel);

        modePanel.add(easyMode);
        modePanel.add(mediumMode);
        modePanel.add(hardMode);

        timerPanel.add(timerLabel);
        flagsPanel.add(flagsLabel);
        minesPanel.add(minesLabel);

        rightPanel.add(timerPanel);
        rightPanel.add(flagsPanel);
        rightPanel.add(minesPanel);
        rightPanel.add(modePanel);

        mineFrame.add(HexPanel, BorderLayout.WEST);
        mineFrame.add(rightPanel, BorderLayout.EAST);

        mineFrame.setVisible(true);
    }

    private class ModeListener implements ActionListener {
        private final String mode;

        public ModeListener(String mode) {
            this.mode = mode;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            HexPanel.setMode(mode);
        }
    }

    private static class HexagonMinesweeperPanel extends JPanel {
        private HexLayout hexLayout;
        private HexMineManager hexMineManager;
        private String mode;
        private JLabel flagsLabel, minesLabel;
        private int hexSize = 25;
        private int radius;

        public HexagonMinesweeperPanel(){
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    handleMouseClick(e);
//                    Point clickPixel = new Point(e.getX(), e.getY());
//                    Hex clickedHex = hexLayout.pixelToHex(clickPixel);
//                    System.out.println("Clicked hex: " + clickedHex.q+","+clickedHex.r+","+clickedHex.s);
                }
            });
        }

        public void setMode(String mode) {
            this.mode = mode;
            Point origin = new Point((double) getWidth() / 2,
                    (double) getHeight() / 2);
            this.hexLayout = new HexLayout(HexLayout.FlatTop,
                    new Point(hexSize, hexSize),
                    origin);


            switch (mode){
                case "Easy" -> {
                    this.radius = 2;
                    this.hexMineManager = new HexMineManager(radius, 5);
                    }
                case "Medium" -> {
                    this.radius = 6;
                    this.hexMineManager = new HexMineManager(radius, 15);}
                case "Hard" -> {
                    this.radius = 10;
                    this.hexMineManager = new HexMineManager(radius, 30);}
            }
            this.repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            System.out.println("Painting component");
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            //Debugging
            System.out.println("HexLayout: " + hexLayout);
            System.out.println("HexMineManager: " + hexMineManager);
            if (hexMineManager == null || hexLayout == null){
                System.out.println("HexMineManager or HexLayout is null");
                return;
            }

            int radius = hexMineManager.getRadius();

            for (int q = -radius; q <= radius; q++) {
                int r1 = Math.max(-radius, -q - radius);
                int r2 = Math.min(radius, -q + radius);
                for (int r = r1; r <= r2; r++) {
                    Hex cell = hexMineManager.getCell(q, r, -q - r);
                    if (cell == null) {
                        continue;
                    }

                    Point center = hexLayout.hexToPixel(cell);
                    Point corneroffset = hexLayout.hexCornerOffset(6);
                    Point polygonCorners = hexLayout.polygonCorners(cell);

                    Color fillColor;
                    if (cell.isFlagged()) {
                        fillColor = Color.BLUE;
                    } else if (!cell.isUncovered()) {
                        fillColor = Color.BLACK;
                    } else if (cell.isMine()) {
                        fillColor = Color.RED;
                    } else if (cell.getMinesNearby() == 0) {
                        fillColor = Color.WHITE;
                    }else {fillColor = new Color(83, 38, 193);
                    }

                    int circleRadius = 19;
                    g2d.setColor(fillColor);
                    g2d.fillOval((int) (center.x() - circleRadius), (int)
                            (center.y() - circleRadius), 2 * circleRadius,
                            2 * circleRadius);
                    g2d.setColor(Color.BLACK);
                    g2d.drawOval((int) (center.x() - circleRadius), (int)
                            (center.y() - circleRadius), 2 * circleRadius,
                            2 * circleRadius);



                    if (cell.isUncovered() && cell.getMinesNearby() > 0) {
                        String text = String.valueOf(cell.getMinesNearby());
                        FontMetrics metrics = g2d.getFontMetrics();
                        int x = (int) center.x() - metrics.stringWidth(text) / 2;
                        int y = (int) center.y() + metrics.getAscent() / 2;
                        g2d.setColor(Color.WHITE);
                        if (cell.isMine()){
                            g2d.drawString("",x,y);
                        } else if (cell.isFlagged()){
                            g2d.drawString("",x,y);
                        }else {
                            g2d.drawString(text, x, y);
                        }
                    }
                    else if (cell.isFlagged()){
                        FontMetrics metrics = g2d.getFontMetrics();
                        int x = (int) center.x() - metrics.stringWidth("F") / 2;
                        int y = (int) center.y() + metrics.getAscent() / 2;
                        g2d.setColor(Color.white.brighter());
                        g2d.drawString("F",x,y);
                    }
                }
            }
        }

        public void setFlagsLabel(JLabel label){
            this.flagsLabel = label;
        }

        public void setMinesLabel(JLabel label){
            this.minesLabel = label;
        }

        private void handleMouseClick(MouseEvent e){
            if (e.getButton() == MouseEvent.BUTTON1){
                Point clickPixel = new Point(e.getX(), e.getY());
                Hex clickedHex = hexLayout.pixelToHex(clickPixel);
                hexMineManager.uncoverCell(clickedHex.q,clickedHex.r,clickedHex.s);

                if (flagsLabel != null){
                    flagsLabel.setText("Flags: " + hexMineManager.getFlagCount());
                }
//                System.out.println("left click at " + e.getX() +","+e.getY());
                repaint();
            } else if (e.getButton() == MouseEvent.BUTTON3){
                Point clickPixel = new Point(e.getX(), e.getY());
                Hex clickedHex = hexLayout.pixelToHex(clickPixel);
                hexMineManager.flagCell(clickedHex.q,clickedHex.r,clickedHex.s);

                if (minesLabel != null){
                    int remain = hexMineManager.getMineCount() -
                            hexMineManager.getFlagCount();
                    minesLabel.setText("Mines: " + remain);
                }
//                System.out.println("right click at " + e.getX() +","+e.getY());
                repaint();
            }
        }
    }
}
