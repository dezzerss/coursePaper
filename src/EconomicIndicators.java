import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EconomicIndicators {
    private JFrame frame;
    private JPanel displayPanel;
    private JTextField priceField, quantityField, netProfitField, totalExpensesField, totalRevenueField, currentCPIField, previousCPIField, unemployedField, laborForceField;
    private JTextArea resultArea;

    public EconomicIndicators() {
        frame = new JFrame("Расчет основных показателей");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        // панель для отображения данных
        displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(0, 2)); // 0 строк, 2 колонки

        // элементы управления
        String[] indicators = {"Выручка", "Рентабельность", "Чистая прибыль", "Уровень инфляции", "Уровень безработицы"};
        JComboBox<String> indicatorComboBox = new JComboBox<>(indicators);

        // добавляем слушатель для обработки выбора показателя
        indicatorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = indicatorComboBox.getSelectedIndex();
                updateDisplayPanel(selectedIndex);
            }
        });

        // создаем текстовую область для отображения результатов
        resultArea = new JTextArea(2, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // кнопка для расчета показателей
        JButton calculateButton = new JButton("Рассчитать");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResults();
            }
        });

        // панель для размещения кнопки и текстовой области
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(calculateButton, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        // добавляем элементы в фрейм
        frame.add(indicatorComboBox, BorderLayout.NORTH);
        frame.add(displayPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // устанавливаем видимость фрейма
        frame.setVisible(true);
    }

    private void updateDisplayPanel(int selectedIndicator) {
        displayPanel.removeAll(); // удаляем предыдущие компоненты

        switch (selectedIndicator) {
            case 0: // выручка
                displayPanel.add(new JLabel("Цена товара:"));
                priceField = new JTextField(5);
                displayPanel.add(priceField);
                displayPanel.add(new JLabel("Количество проданных единиц:"));
                quantityField = new JTextField(5);
                displayPanel.add(quantityField);
                break;

            case 1: // рентабельность
                displayPanel.add(new JLabel("Чистая прибыль:"));
                netProfitField = new JTextField(5);
                displayPanel.add(netProfitField);
                displayPanel.add(new JLabel("Общие расходы:"));
                totalExpensesField = new JTextField(5);
                displayPanel.add(totalExpensesField);
                break;

            case 2: // чистая прибыль
                displayPanel.add(new JLabel("Общие доходы:"));
                totalRevenueField = new JTextField(5);
                displayPanel.add(totalRevenueField);
                displayPanel.add(new JLabel("Общие расходы:"));
                totalExpensesField = new JTextField(5);
                displayPanel.add(totalExpensesField);
                break;

            case 3: // уровень инфляции
                displayPanel.add(new JLabel("Текущий CPI:"));
                currentCPIField = new JTextField(5);
                displayPanel.add(currentCPIField);
                displayPanel.add(new JLabel("Предыдущий CPI:"));
                previousCPIField = new JTextField(5);
                displayPanel.add(previousCPIField);
                break;

            case 4: // уровень безработицы
                displayPanel.add(new JLabel("Количество безработных:"));
                unemployedField = new JTextField(5);
                displayPanel.add(unemployedField);
                displayPanel.add(new JLabel("Количество рабочей силы:"));
                laborForceField = new JTextField(5);
                displayPanel.add(laborForceField);
                break;

            default:
                break;
        }
        // обнова интерфейса
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    private void calculateResults() {
        // очистка текстовой области перед выводом новых результатов
        resultArea.setText("");

        // выбранный показатель
        String selectedIndicator = (String) ((JComboBox<?>) frame.getContentPane().getComponent(0)).getSelectedItem();

        try {
            switch (selectedIndicator) {
                case "Выручка":
                    double price = Double.parseDouble(priceField.getText());
                    double quantity = Double.parseDouble(quantityField.getText());
                    double revenue = price * quantity;
                    resultArea.append(revenue + "\n");
                    break;

                case "Рентабельность":
                    double netProfit = Double.parseDouble(netProfitField.getText());
                    double totalExpenses = Double.parseDouble(totalExpensesField.getText());
                    double profitability = (netProfit / totalExpenses) * 100;
                    resultArea.append(profitability + "%\n");
                    break;

                case "Чистая прибыль":
                    double totalRevenue = Double.parseDouble(totalRevenueField.getText());
                    totalExpenses = Double.parseDouble(totalExpensesField.getText());
                    double netIncome = totalRevenue - totalExpenses;
                    resultArea.append(netIncome + "\n");
                    break;

                case "Уровень инфляции":
                    double currentCPI = Double.parseDouble(currentCPIField.getText());
                    double previousCPI = Double.parseDouble(previousCPIField.getText());
                    double inflationRate = ((currentCPI - previousCPI) / previousCPI) * 100;
                    resultArea.append(inflationRate + "%\n");
                    break;

                case "Уровень безработицы":
                    double unemployed = Double.parseDouble(unemployedField.getText());
                    double laborForce = Double.parseDouble(laborForceField.getText());
                    double unemploymentRate = (unemployed / laborForce) * 100;
                    resultArea.append(unemploymentRate + "%\n");
                    break;

                default:
                    break;
            }
        } catch (NumberFormatException e) {
            resultArea.append("Ошибка: Введите корректные числовые значения.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EconomicIndicators::new);
    }
}