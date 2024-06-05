import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class BillingApp extends JFrame {
    private Map<String, Double> priceList;
    private Map<String, Integer> bill;
    private JComboBox<String> itemComboBox;
    private JTextField quantityField;
    private JTextArea billArea;

    public BillingApp() {
        priceList = new HashMap<>();
        bill = new HashMap<>();
        for (int i = 1; i <= 20; i++) {
            priceList.put("Item" + i, i * 10.0);
        }

        setLayout(new FlowLayout());
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        itemComboBox = new JComboBox<>(priceList.keySet().toArray(new String[0]));
        add(itemComboBox);

        quantityField = new JTextField(5);
        add(quantityField);

        JButton addButton = new JButton("Add to Bill");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String) itemComboBox.getSelectedItem();
                int quantity = Integer.parseInt(quantityField.getText());
                bill.put(item, bill.getOrDefault(item, 0) + quantity);
                updateBillArea();
            }
        });
        add(addButton);

        billArea = new JTextArea(20, 20);
        billArea.setEditable(false);
        add(new JScrollPane(billArea));

        setVisible(true);
    }

    private void updateBillArea() {
        billArea.setText("");
        double total = 0;
        for (Map.Entry<String, Integer> entry : bill.entrySet()) {
            double cost = priceList.get(entry.getKey()) * entry.getValue();
            total += cost;
            billArea.append(entry.getKey() + ": " + entry.getValue() + " x " + priceList.get(entry.getKey()) + " = " + cost + "\n");
        }
        billArea.append("\nTotal: " + total);
    }

    public static void main(String[] args) {
        new BillingApp();
    }
}
