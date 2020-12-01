import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class Burger extends CafeMenu {

    private String name;
    private ArrayList<Item> items;

    //Default Constructor
    Burger() {
        name = "";
        items = new ArrayList<>();
    }

    //Parameterised Constructor
    public Burger(String name) {
        this.name = name;
        items = new ArrayList<>();
    }
	//Accessors and Mutators
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    void addItems(Item[] categoryItems) {
        for (Item it1 : categoryItems) {
            items.add(it1);
        }
    }

    @Override
    public String toString() {
        System.out.println("Items: ");
        for (Item it : items) {
            System.out.println(it);
        }
        return ("");
    }

    //String to display the burger category
    @Override
    String displayCategories(Item i) {
        String s = "";
        s += i.displayItems();
        return s;
    }

}
//Abstract class

abstract class CafeMenu {

    //Variable
    private String name;

    //Abstract Methods
    abstract String getName();

    abstract void setName(String name);

    abstract void addItems(Item[] categoryItems);

    abstract String displayCategories(Item i);

}

class Customer implements OrderInterface {

    //Variables
    private String customerName;
    private int tableID;
    private ArrayList<Record> records;

    //Default Constructor
    public Customer() {
        customerName = "";
        tableID = 0;
        records = new ArrayList<>();
    }

    //Parameterised Constructor
    public Customer(String customerName, int TableID) {
        this.customerName = customerName;
        this.tableID = TableID;
        records = new ArrayList<>();
    }

    //Accessors and Mutators
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int TableID) {
        this.tableID = TableID;
    }

    @Override
    public ArrayList<Record> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return ("customerName=" + customerName + "\t TableID=" + tableID);
    }

    @Override
    public void addRecord(Record r) {
        records.add(r);
    }

    @Override
    public Record getRecord(int r) {
        Record retrievedRecord = records.get(r);
        return retrievedRecord;
    }

    //Write customer name and table id in text file
    public String writeFileCustomer(String fileName) {
        Formatter f = new Formatter();//Use Formatter
        f.format("Customer Name: %s;  Table ID: %d", customerName, tableID);//Format Student Output
        //System.out.println(f);
        BufferedWriter out = null; //BufferedWriter
        PrintWriter out1 = null; //Use PrintWriter
        //Try and Catch method to create the txt file, flush and close
        try {

            out = new BufferedWriter(new FileWriter(fileName, true));
            out1 = new PrintWriter(out);
            out1.write(f.toString());
            out1.flush();//Flush text file
        } catch (IOException e) {
            System.out.println("Exception ");
        } finally {
            try {
                out.close();
                out1.close();
            } catch (IOException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }
}

class Item {

    //Variables
    private String iName;
    private SizePrice iSize;
    private String iDescription;
    private int quantity;
    private String itemType;
    private double totalPrice;

    public Item() {
        iName = "";
        iSize = new SizePrice();
        iDescription = "";
        quantity = 0;
        itemType = " ";
        totalPrice = 0.0;
    }

    //Constructor if item is vegetarian
    public Item(String iName, SizePrice iSize, String iDescription, String itemType) {
        this.iName = iName;
        this.iSize = iSize;
        this.iDescription = iDescription;
        this.itemType = itemType;
        this.quantity = 0;
        this.totalPrice = 0.0;
    }

    //Constructor for non-vegetarian items
    public Item(String iName, SizePrice iSize, String iDescription) {
        this.iName = iName;
        this.iSize = iSize;
        this.iDescription = iDescription;
        this.itemType = "";
        this.quantity = 0;
        this.totalPrice = 0.0;
    }

    //Accessors and Mutators
    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public SizePrice getiSize() {
        return iSize;
    }

    public void setiSize(SizePrice iSize) {
        this.iSize = iSize;
    }

    public String getiDescription() {
        return iDescription;
    }

    public void setiDescription(String iDescription) {
        this.iDescription = iDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //Print the subjects
    @Override
    public String toString() {
        String s = "";
        s += iName + "\t$" + iSize + ", " + iDescription;
        return s;
    }

    public String displayItems() { //Display item name and price
        String s = "";
        s += this.iName;
        s += "\t";
        s += "$" + this.iSize.getPrice();
        return s;
    }

    public String displayDescription() { //Display the item description
        String s = "";
        s += this.iDescription;
        return s;
    }

    public String displayItemAndQuantity() {//Display the item name, item quantity and item type 
        String s = "";
        s += this.iName;
        s += "\t";
        s += "Quantity: " + this.quantity;
        s += "\t";
        s += "$" + this.iSize.getPrice();
        s += "\t" + this.itemType;
        s += "\n";
        return s;
    }
}
//OrderInterface interface

interface OrderInterface {

    public void addRecord(Record r);//Add record

    public Record getRecord(int r);//Get record

    public ArrayList<Record> getRecords();//Get Records
}

class Pizza extends CafeMenu {

    //Variables
    private String name;
    private ArrayList<Item> items;

    //Default Constructor
    Pizza() {
        name = "";
        items = new ArrayList<>();
    }

    //Parameterised Constructor
    public Pizza(String name) {
        this.name = name;
        items = new ArrayList<>();
    }

    //Accessors and Mutators
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    //Add the items to category
    @Override
    void addItems(Item[] categoryItems) {
        for (Item it1 : categoryItems) {
            items.add(it1);
        }
    }

    //toString method
    @Override
    public String toString() {
        System.out.println("Items: ");
        for (Item it : items) {
            System.out.println(it);
        }
        return ("");
    }

    //String to display the pizza category
    @Override
    String displayCategories(Item i) {
        String s = "";
        s += i.displayItems();
        return s;
    }

}

class Record {

    //Variables
    private String cName;
    private ArrayList<Item> eItems;
    private double totalPrice;

    //Default constructor
    public Record() {
        cName = "";
        eItems = new ArrayList<>();
        totalPrice = 0;
    }

    //Paramterised Constructor
    public Record(String cName) {
        this.cName = cName;
        this.eItems = new ArrayList<>();
        this.totalPrice = 0;
    }

    //Accessors and Mutators
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public ArrayList<Item> geteItems() {
        return eItems;
    }

    public void seteItems(ArrayList<Item> eItems) {
        this.eItems = eItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //Record all items
    public void recordItems(ArrayList<Item> items) {
        for (Item it1 : items) {
            eItems.add(it1);
        }
    }

    //Record each individual item
    public void recordItem(Item i) {
        eItems.add(i);
    }

    @Override
    public String toString() {
        String s = "";
        s += "\nItems:\n";
        for (Item it1 : eItems) {
            s += it1;
            s += "\n";
        }
        return s;
    }

    //Display the total price
    public String displayTotalPrice() {
        String s = "";
        s += this.getTotalPrice();
        return s;
    }

    //Write the record into a text file
    public String writeFileRecord(String fileName) {
        Formatter f = new Formatter();//Use Formatter
        String s = "";
        s += "\n";
        for (Item it1 : eItems) {
            s += it1.getiName() + "\t";
            s += "Quantity: " + it1.getQuantity() + "\t";
            s += "$" + it1.getiSize().getPrice();
            s += "\t" + it1.getItemType();
            s += "\n";
        }
        s += "----------" + "\n" + "Total Price: $" + this.totalPrice;
        f.format("%s", s);//Format Student Output
        //System.out.println(f);
        BufferedWriter out = null; //BufferedWriter
        PrintWriter out1 = null; //Use PrintWriter
        //Try and Catch method to create the txt file, flush and close
        try {

            out = new BufferedWriter(new FileWriter(fileName, true));
            out1 = new PrintWriter(out);
            out1.write(f.toString());
            out1.flush();//Flush text file
        } catch (IOException e) {
            System.out.println("Exception ");
        } finally {
            try {
                out.close();
                out1.close();
            } catch (IOException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";

    }

}

class Sides extends CafeMenu {

    //Variables
    private String name;
    private ArrayList<Item> items;

    //Default Constructor
    Sides() {
        name = "";
        items = new ArrayList<>();
    }

//Parameterised Constructor
    public Sides(String name) {
        this.name = name;
        items = new ArrayList<>();
    }

    //Accessors and Mutators
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    //add items to the side category
    @Override
    void addItems(Item[] categoryItems) {
        for (Item it1 : categoryItems) {
            items.add(it1);
        }
    }

    //toString method
    @Override
    public String toString() {
        System.out.println("Items: ");
        for (Item it : items) {
            System.out.println(it);
        }
        return ("");
    }

    //String to display the side category
    @Override
    String displayCategories(Item i) {
        String s = "";
        s += i.displayItems();
        return s;
    }
}

class SizePrice {//Class to create the size and price of an item

    //Variables
    private String sizeType;
    private double price;

    //Default Constructor
    public SizePrice() {
        sizeType = "";
        price = 0;
    }

    //Parameterised Constructors
    public SizePrice(double price) {
        this.sizeType = sizeType;
        this.price = price;
    }

    public SizePrice(String sizeType, double price) {
        this.sizeType = sizeType;
        this.price = price;
    }

    //Accessors and Mutators
    public String getSizeType() {
        return sizeType;
    }

    public void setSizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //toString method
    @Override
    public String toString() {
        return ("" + price);
    }

}

public class CafeOrderSystem extends javax.swing.JFrame {

    private static Customer c1;
    private static CafeMenu pizza, burger, sides = null;
    private static Item[] pizzaItems, burgerItems, sidesItems;
    private static Record record1;
    private static int quantity;
    private static double totalItemPrice, totalPrice;
    private static ArrayList<Customer> customerList;
    private static ArrayList<Record> records;
    private static Item tropicanaPizza, wagyuBurger;

    public CafeOrderSystem() {
        initComponents();
        this.descriptionTextArea.setEditable(false);
        this.orderDisplayArea.setEditable(false);
        this.orderTextArea2.setEditable(false);

        DefaultListModel<String> model = new DefaultListModel<>();
        this.categoryList.setModel(model);
        model.addElement(pizza.getName());
        model.addElement(burger.getName());
        model.addElement(sides.getName());
    }

    public void checkExceptions(ActionEvent e) {
        try {
            if (this.customerNameField.getText().equals("")) {
                throw new InputMismatchException("Student name is not inputted.");
            }
        } catch (InputMismatchException ie) {
            JOptionPane.showMessageDialog(null, ie, "Validation Checking", JOptionPane.ERROR_MESSAGE);
        }

        try {
            if (this.tableIDField.getText().equals("")) {
                throw new InputMismatchException("Student number is not inputted.");
            }
            String s = tableIDField.getText();
            int tID = Integer.parseInt(s);
        } catch (InputMismatchException | NumberFormatException ie) {
            JOptionPane.showMessageDialog(null, ie, "Validation Checking", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void checkQuantityException(ActionEvent e) {
        try {
            if (this.quantityField.getText().equals("")) {
                throw new InputMismatchException("Quantity is not inputted.");
            }
            String s = quantityField.getText();
            int qField = Integer.parseInt(s);
        } catch (InputMismatchException | NumberFormatException ie) {
            JOptionPane.showMessageDialog(null, ie, "Validation Checking", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        javax.swing.JPanel backgroundPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        customerNameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tableIDField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        categoryList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        itemList = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        quantityField = new javax.swing.JTextField();
        addQuantityButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        orderDisplayArea = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        submitButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        orderTextArea2 = new javax.swing.JTextArea();

        jTextField3.setText("jTextField3");

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cafe Order System");

        backgroundPanel.setBackground(new java.awt.Color(0, 153, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Customer Name: ");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        customerNameField.setMaximumSize(new java.awt.Dimension(100, 100));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Table ID: ");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(tableIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Quantity: ");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Item:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Description:");

        categoryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        categoryList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                categoryListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(categoryList);

        itemList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        itemList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                itemListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(itemList);

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        jScrollPane3.setViewportView(descriptionTextArea);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Category:");

        addQuantityButton.setBackground(new java.awt.Color(255, 255, 255));
        addQuantityButton.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        addQuantityButton.setText("Add");
        addQuantityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addQuantityButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Order:");

        orderDisplayArea.setColumns(20);
        orderDisplayArea.setRows(5);
        jScrollPane4.setViewportView(orderDisplayArea);

        jPanel2.setBackground(new java.awt.Color(255, 153, 0));

        submitButton.setBackground(new java.awt.Color(255, 255, 255));
        submitButton.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        jPanel2.add(submitButton);

        resetButton.setBackground(new java.awt.Color(255, 255, 255));
        resetButton.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        jPanel2.add(resetButton);

        orderTextArea2.setColumns(20);
        orderTextArea2.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        orderTextArea2.setRows(5);
        jScrollPane5.setViewportView(orderTextArea2);

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                .addGap(214, 214, 214)))
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                .addGap(437, 437, 437))
                            .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addContainerGap())))
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addQuantityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8))
                                .addGap(0, 610, Short.MAX_VALUE))
                            .addComponent(jScrollPane5))
                        .addContainerGap())))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addQuantityButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void categoryListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_categoryListValueChanged
        // TODO add your handling code here:
        DefaultListModel<String> model = new DefaultListModel<>();
        String text = categoryList.getSelectedValue();
        this.itemList.setModel(model);
        for (Item i : pizzaItems) {
            if (text.equals("Pizza")) {
                model.addElement(pizza.displayCategories(i));//Add pizza items when pizza is selected to item list
            }
        }

        for (Item i : burgerItems) {
            if (text.equals("Burger")) {
                model.addElement(burger.displayCategories(i));//Add burger items when burger is selected to item list
            }
        }

        for (Item i : sidesItems) {
            if (text.equals("Sides")) {
                model.addElement(sides.displayCategories(i));//Add side items when side is selected to item list
            }
        }

    }//GEN-LAST:event_categoryListValueChanged

    private void itemListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_itemListValueChanged
        // TODO add your handling code here:
        //DISPLAY DESCRIPTION CODE

        /*
        String text = this.itemList.getSelectedValue();
        if (text.contains("Tropicana Pizza")) {
            this.descriptionTextArea.setText(tropicanaPizza.displayDescription());
        }
        if (text.contains("Wagyu Burger")) {
            this.descriptionTextArea.setText(wagyuBurger.displayDescription());
        }*/

    }//GEN-LAST:event_itemListValueChanged

    private void addQuantityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addQuantityButtonActionPerformed
        this.checkQuantityException(evt);//Check for exception when add button action is performed
        String quantityInput = this.quantityField.getText();
        String itemValue = this.itemList.getSelectedValue();
        quantity = Integer.valueOf(quantityInput);
        for (Item i : pizzaItems) {
            String text = this.itemList.getSelectedValue();
            if (text.equals(i.displayItems())) {
                //totalPrice = quantity * i.getiSize().getPrice();
                i.setQuantity(quantity);
                totalItemPrice = quantity * i.getiSize().getPrice(); //Total item price is the quantity multipleied with the price of the item
                SizePrice iSize = new SizePrice(totalItemPrice);//Set SizePrice to the double variable totalItemPrice
                i.setiSize(iSize);
                this.orderDisplayArea.append(i.displayItemAndQuantity() + "\n");//Append the items chosen with the information
                record1.recordItem(i);//Add the item chosen and added by the customer
            }
        }

        for (Item i : burgerItems) {
            String text = this.itemList.getSelectedValue();
            if (text.equals(i.displayItems())) {
                //totalPrice = quantity * i.getiSize().getPrice();
                i.setQuantity(quantity);
                totalItemPrice = quantity * i.getiSize().getPrice();//Total item price is the quantity multipleied with the price of the item
                SizePrice iSize = new SizePrice(totalItemPrice);
                i.setiSize(iSize);
                this.orderDisplayArea.append(i.displayItemAndQuantity() + "\n");
                record1.recordItem(i);
            }
        }

        for (Item i : sidesItems) {
            String text = this.itemList.getSelectedValue();
            if (text.equals(i.displayItems())) {
                //totalPrice = quantity * i.getiSize().getPrice();
                i.setQuantity(quantity);
                totalItemPrice = quantity * i.getiSize().getPrice();//Total item price is the quantity multipleied with the price of the item
                SizePrice iSize = new SizePrice(totalItemPrice);
                i.setiSize(iSize);
                this.orderDisplayArea.append(i.displayItemAndQuantity() + "\n");
                record1.recordItem(i);
            }
        }

        totalPrice += totalItemPrice;//Calculate the total price
        String s1 = Double.toString(totalPrice);//Convert total price to a string
        this.orderTextArea2.setText("Total Price: $" + s1);//Display the total price in orderTextArea2 text area
    }//GEN-LAST:event_addQuantityButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:
        // record1.setTotalPrice(totalPrice);
        // record1.writeRecordFileCustomer(studentName + "_bcs.txt");//Write UnderGrad file
        this.checkExceptions(evt);//Check for customer and table id exceptions

        //Add Customer record
        String custName = this.customerNameField.getText();
        String tableID = this.tableIDField.getText();
        int tID = Integer.parseInt(tableID);
        c1 = new Customer(custName, tID);//Create a customer using customer input
        c1.addRecord(record1);//Add record to the customer
        customerList.add(c1);//Add customer to the customer list
        for (Customer c : customerList) {
            records = c.getRecords();
            c.writeFileCustomer(custName + "_" + tableID + ".txt");//Write the file of customer to a text file which includes their name and table id
            for (Record r : records) {
                r.setTotalPrice(totalPrice);//Set the record total price to total price variable value calculated earlier
                r.writeFileRecord(custName + "_" + tableID + ".txt");//Write the record in the text file
            }
        }
        JOptionPane.showMessageDialog(null, "The customers order has been saved.", "Save Order", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_submitButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        //Reset everything but the category list
        this.customerNameField.setText(null);
        this.tableIDField.setText(null);
        this.quantityField.setText(null);
        this.descriptionTextArea.setText(null);
        this.orderDisplayArea.setText(null);
        this.orderTextArea2.setText(null);
        DefaultListModel<String> model1 = new DefaultListModel<>();
        model1.addElement(null);
        this.itemList.setModel(model1);
        totalPrice = 0;

    }//GEN-LAST:event_resetButtonActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CafeOrderSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CafeOrderSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CafeOrderSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CafeOrderSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CafeOrderSystem().setVisible(true);
            }
        });

        customerList = new ArrayList<>();//Initialise customer list
        //Creating pizza Items
        SizePrice mSizePrice = new SizePrice("M", 10.0);
        tropicanaPizza = new Item("Tropicana Pizza", mSizePrice, "Ham and Pineapple served on a tomato base with mozarella cheese");
        SizePrice nmSizePrice = new SizePrice("NM", 13.0);
        Item tropicanaPizzaNM = new Item("Tropicana Pizza (NM)", nmSizePrice, "Ham and Pineapple served on a tomato base with mozarella cheese");

        SizePrice mSizePrice2 = new SizePrice("M", 16.0);
        Item bbqMeatLoversPizza = new Item("BBQ Meat Lovers Pizza", mSizePrice2, "Beef, bacon, ham, pepperoni, spanish onion, cherry tomatoes, and mozzarella cheese");
        SizePrice nmSizePrice2 = new SizePrice("NM", 19.0);
        Item bbqMeatLoversPizzaNM = new Item("BBQ Meat Lovers Pizza(NM)", nmSizePrice2, "Beef, bacon, ham, pepperoni, spanish onion, cherry tomatoes, and mozzarella cheese");

        SizePrice mSizePrice3 = new SizePrice("M", 9.0);
        Item pepperoniPizza = new Item("Pepperoni Pizza", mSizePrice3, "Tomato base, chilli flakes, pepperoni, spinach, and mozzarrella cheese");
        SizePrice nmSizePrice3 = new SizePrice("NM", 12.0);
        Item pepperoniPizzaNM = new Item("Pepperoni Pizza (NM)", nmSizePrice3, "Tomato base, chilli flakes, pepperoni, spinach, and mozzarrella cheese");

        SizePrice mSizePrice4 = new SizePrice("M", 14.0);
        Item chickenMushroomBriePizza = new Item("Chicken, Mushroom and Brie Pizza", mSizePrice4, "Tomato base, chicken, mushroom, spinach, brie and mozzarella cheese");
        SizePrice nmSizePrice4 = new SizePrice("NM", 17.0);
        Item chickenMushroomBriePizzaNM = new Item("Chicken, Mushroom and Brie Pizza (NM)", nmSizePrice4, "Tomato base, chicken, mushroom, spinach, brie and mozzarella cheese");

        SizePrice mSizePrice5 = new SizePrice("M", 8.0);
        Item garlicPizza = new Item("Garlic Pizza", mSizePrice5, "Confit garlic, mozzarella cheese, rosemary topped with smoked salt", "Vegetarian");
        SizePrice nmSizePrice5 = new SizePrice("NM", 11.0);
        Item garlicPizzaNM = new Item("Garlic Pizza (NM)", nmSizePrice5, "Confit garlic, mozzarella cheese, rosemary topped with smoked salt", "Vegetarian");

        SizePrice mSizePrice6 = new SizePrice("M", 12.0);
        Item slowRoastedLambPizza = new Item("Slow-Roasted Lamb Pizza", mSizePrice6, "Tomato base with slow-roasted lamb, rocket, sumac, tzatziki, and mozzarella cheese");
        SizePrice nmSizePrice6 = new SizePrice("NM", 15.0);
        Item slowRoastedLambPizzaNM = new Item("Slow-Roasted Lamb Pizza (NM)", nmSizePrice6, "Tomato base with slow-roasted lamb, rocket, sumac, tzatziki, and mozzarella cheese");

        SizePrice mSizePrice7 = new SizePrice("M", 12.0);
        Item greenPizza = new Item("Green Pizza", mSizePrice7, "Basil pesto base topped with rocket, broccolo, green olives, and bocconcini", "Vegetarian");
        SizePrice nmSizePrice7 = new SizePrice("NM", 15.0);
        Item greenPizzaNM = new Item("Green Pizza (NM)", nmSizePrice7, "Basil pesto base topped with rocket, broccolo, green olives, and bocconcini", "Vegetarian");

        //Create category items
        pizzaItems = new Item[]{tropicanaPizza, tropicanaPizzaNM, bbqMeatLoversPizza, bbqMeatLoversPizzaNM, pepperoniPizza,
            pepperoniPizzaNM, chickenMushroomBriePizza, chickenMushroomBriePizzaNM, garlicPizza,
            garlicPizzaNM, slowRoastedLambPizza, slowRoastedLambPizzaNM, greenPizza, greenPizzaNM};

        //Create Pizza Menu
        pizza = new Pizza("Pizza");
        pizza.addItems(pizzaItems);
        record1 = new Record(pizza.getName());
        //ArrayList<Item> pizzaItems1 = new ArrayList<>(Arrays.asList(pizzaItems));
        //record1.recordItems(pizzaItems1);

        //Creating Burger Items
        SizePrice mSizePrice8 = new SizePrice("M", 12.0);
        wagyuBurger = new Item("Wagyu Burger", mSizePrice8, "Wagyu beef, bacon, tomato, mesculin, beetroot, and aioli on a lightly toasted brioche bun, served with chips");
        SizePrice nmSizePrice9 = new SizePrice("NM", 15.0);
        Item wagyuBurgerNM = new Item("Wagyu Burger (NM)", nmSizePrice9, "Wagyu beef, bacon, tomato, mesculin, beetroot, and aioli on a lightly toasted brioche bun, served with chips");

        SizePrice mSizePrice10 = new SizePrice("M", 16.5);
        Item cheeseBurger = new Item("Cheese Burger", mSizePrice10, "Milk bun topped with a beef patty, cheese, tomato, and mustard served with chips.");
        SizePrice mSizePrice11 = new SizePrice("NM", 19.5);
        Item cheeseBurgerNM = new Item("Cheese Burger(NM)", mSizePrice11, "Milk bun topped with a beef patty, cheese, tomato, and mustard served with chips.");

        SizePrice mSizePrice12 = new SizePrice("M", 10.0);
        Item halloumiBurger = new Item("Halloumi Burger", mSizePrice12, "Milk bun topped with rocket, halloumi,egg, and tomato relish, served with chips", "Vegetarian");
        SizePrice mSizePrice13 = new SizePrice("NM", 13.0);
        Item halloumiBurgerNM = new Item("Halloumi Burger(NM)", mSizePrice13, "Milk bun topped with rocket, halloumi,egg, and tomato relish, served with chips", "Vegetarian");

        SizePrice mSizePrice14 = new SizePrice("M", 18.5);
        Item steakSandwich = new Item("Steak Sandwich", mSizePrice14, "120g rib fillet steak with caramelised onion, lettuce, cheese, tomato, beetroot, and barbeque sauce on a toasted sandwich ciabatta, served with chips");
        SizePrice mSizePrice15 = new SizePrice("NM", 21.5);
        Item steakSandwichNM = new Item("Steak Sandwich(NM)", mSizePrice15, "120g rib fillet steak with caramelised onion, lettuce, cheese, tomato, beetroot, and barbeque sauce on a toasted sandwich ciabatta, served with chips");

        SizePrice mSizePrice16 = new SizePrice("M", 9.9);
        Item chickenWrap = new Item("Chicken Wrap", mSizePrice16, "Southern chicken tenders wrapped in a soft tortilla with sweet chilli aioli, lettuce, cheese, tomato, and carrot, served with chips");
        SizePrice mSizePrice17 = new SizePrice("NM", 12.9);
        Item chickenWrapNM = new Item("Chicken Wrap(NM)", mSizePrice17, "Southern chicken tenders wrapped in a soft tortilla with sweet chilli aioli, lettuce, cheese, tomato, and carrot, served with chips");

        //Create category items
        burgerItems = new Item[]{wagyuBurger, wagyuBurgerNM, cheeseBurger, cheeseBurgerNM, halloumiBurger, halloumiBurgerNM, steakSandwich, steakSandwichNM, chickenWrap, chickenWrapNM};

        //Create Burger Menu
        burger = new Burger("Burger");
        burger.addItems(burgerItems);
        record1 = new Record(burger.getName());

        //Create side Items
        SizePrice mSizePrice18 = new SizePrice("M", 6.5);
        Item sweetPotatoFriesBowl = new Item("Sweet Potato Fries(Bowl)", mSizePrice18, "Served with tomato relish.", "Vegetarian");
        SizePrice mSizePrice19 = new SizePrice("NM", 9.5);
        Item sweetPotatoFriesBowlNM = new Item("Sweet Potato Fries(Bowl)(NM)", mSizePrice19, "Served with tomato relish.", "Vegetarian");

        SizePrice mSizePrice20 = new SizePrice("M", 5.0);
        Item chipsBowl = new Item("Chips(Bowl)", mSizePrice20, "Served with aioli,tomato sauce, and barbeque sauce.", "Vegetarian");
        SizePrice mSizePrice21 = new SizePrice("NM", 8.0);
        Item chipsBowlNM = new Item("Chips(Bowl)(NM)", mSizePrice21, "Served with aioli,tomato sauce, and barbeque sauce.", "Vegetarian");

        SizePrice mSizePrice22 = new SizePrice("M", 6.5);
        Item wedgesBowl = new Item("Wedges(Bowl)", mSizePrice22, "Served with sweet chilli sauce and sour cream", "Vegetarian");
        SizePrice mSizePrice23 = new SizePrice("NM", 9.5);
        Item wedgesBowlNM = new Item("Wedges(Bowl)(NM)", mSizePrice23, "Served with sweet chilli sauce and sour cream", "Vegetarian");

        SizePrice mSizePrice24 = new SizePrice("M", 5.0);
        Item sideSalad = new Item("Side Salad", mSizePrice24, "", "Vegetarian");
        SizePrice mSizePrice25 = new SizePrice("NM", 8.0);
        Item sideSaladNM = new Item("Side Salad(NM)", mSizePrice24, "", "Vegetarian");

        //Create category items
        sidesItems = new Item[]{sweetPotatoFriesBowl, sweetPotatoFriesBowlNM, chipsBowl, chipsBowlNM, wedgesBowl, wedgesBowlNM, sideSalad, sideSaladNM};

        //Create Sides Menu
        sides = new Sides("Sides");
        sides.addItems(sidesItems);
        record1 = new Record(sides.getName());

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addQuantityButton;
    private javax.swing.JList<String> categoryList;
    private javax.swing.JTextField customerNameField;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JList<String> itemList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextArea orderDisplayArea;
    private javax.swing.JTextArea orderTextArea2;
    private javax.swing.JTextField quantityField;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextField tableIDField;
    // End of variables declaration//GEN-END:variables
}
