import java.util.TreeSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Notebook extends JFrame implements ActionListener {

    private JLabel lblSurname, lblPhones, lblCount;
    private JTextField txtSurname, txtPhones;
    private JButton btnPrint;
    private TreeSet<String> notes;

    public Notebook() {
        super("Notebook");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        notes = new TreeSet<String>();

        lblSurname = new JLabel("Surname:");
        lblPhones = new JLabel("Phones:");
        lblCount = new JLabel("Number of notes: 0");

        txtSurname = new JTextField();
        txtPhones = new JTextField();

        btnPrint = new JButton("Print");
        btnPrint.addActionListener(this);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout());

        JPanel pnlInput = new JPanel();
        pnlInput.setLayout(new GridLayout(3, 2));
        pnlInput.setBorder(BorderFactory.createTitledBorder("New Note"));

        pnlInput.add(lblSurname);
        pnlInput.add(txtSurname);
        pnlInput.add(lblPhones);
        pnlInput.add(txtPhones);
        pnlInput.add(new JLabel());
        pnlInput.add(btnPrint);

        JPanel pnlOutput = new JPanel();
        pnlOutput.setBorder(BorderFactory.createTitledBorder("Notes"));
        pnlOutput.setLayout(new BoxLayout(pnlOutput, BoxLayout.PAGE_AXIS));

        JScrollPane scroll = new JScrollPane(pnlOutput);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        pnlMain.add(pnlInput, BorderLayout.NORTH);
        pnlMain.add(scroll, BorderLayout.CENTER);
        pnlMain.add(lblCount, BorderLayout.SOUTH);

        setContentPane(pnlMain);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPrint) {
            notes.add(txtSurname.getText() + ": " + txtPhones.getText());
            txtSurname.setText("");
            txtPhones.setText("");
            lblCount.setText("Number of notes: " + notes.size());
            JPanel pnlOutput = (JPanel) ((JViewport) ((JScrollPane) getContentPane().getComponent(0)).getComponent(0)).getComponent(0);
            pnlOutput.removeAll();
            for (String note : notes) {
                JLabel lblNote = new JLabel(note);
                lblNote.setAlignmentX(Component.LEFT_ALIGNMENT);
                pnlOutput.add(lblNote);
            }
            pnlOutput.revalidate();
            pnlOutput.repaint();
        }
    }

    public static void main(String[] args) {
        new Notebook();
    }
}
