//Alice Zhou
//May 21st, 2018
//Weather Summary: this program allows the users to search for the weather of a Canadian city in April 2018.
//Revision: None


//import all the modules needed for the program (gui, images...)
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.Arrays;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class AliceZU3A1Q1 implements ActionListener {
    private Button bt1, bt2, bt3;                                   //set up all the needed components and global variables (buttons, labels, textfields, strings, etc.)
    private Label lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9;
    private TextField tf1;
    private String textFieldValue;
    private String x = "";
    private int y = 0;
    private BufferedImage image1 = null;

    public static String[][] alldata = new String[1082][25];    //set up the array "alldata" for storing all the data from the csv file

    public static void main(String[] args) {
        AliceZU3A1Q1 gui = new AliceZU3A1Q1 ();     //create and execute the gui display of the program
        gui.go();

        //the following 10 lines are borrowed from:
        //https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
        String csvFile = "C:\\Users\\14396\\IdeaProjects\\AliceZU3A1\\src\\weather.csv";   //set up a string variable for storing the path of the file
        BufferedReader br = null;     //create a new bufferedreader
        String line = "";             //set up variables to split the lines
        String cvsSplitBy = ",";
        Integer x = 0;

        try {
            br = new BufferedReader(new FileReader(csvFile));    //read in the file
            for (int i = 0; i <32; i++) {          //skip the first 32 lines (they are all header lines)
                br.readLine();
            }
            while ((line = br.readLine()) != null) {   //for the rest of the lines:
                String[] data = line.split(cvsSplitBy);  //read in each line and split each cell by a comma
                for(String c: data) {    //remove the quotation marks around each cell's value
                    //this line is borrowed from Jack Bishop
                    data[Arrays.asList(data).indexOf(c)] = c.replaceAll("\"", "");
                }
                alldata[x] = data;   //store each line into the big array "alldata"
                x = x + 1;
            }
        } catch (FileNotFoundException e) {  //catch if the file is not found
            e.printStackTrace();
        } catch (IOException e) {    //catch if the input or output operation fails
            e.printStackTrace();
        } finally {
            if (br != null) {     //close the file when done
                try {
                    br.close();
                } catch (IOException e) {   //catch if the input or output operation fails
                    e.printStackTrace();
                }
            }
        }
    }

    public void go() {  //here is the gui part of the program
        JFrame frame = new JFrame();          //create a new Jframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //set default to exit on close

        JPanel panel1 = new panel();           //create a new Jpanel using the method panel()
        panel1.setBackground(Color.cyan);      //set background color to cyan
        panel1.setLayout(null);                //set layout to null

        try {    //read in the snowflake image for later use
            image1 = ImageIO.read(new File("C:\\Users\\14396\\IdeaProjects\\AliceZU3A1\\src\\snowflake-2910087_960_720.png"));
        } catch (IOException e) {  //catch if the input operation fails
            e.printStackTrace();
        }

        bt1 = new Button(" Enter ");      //create a new button with text "Enter"
        bt1.setBounds(670,240,70,50);
        panel1.add(bt1);                        //add this button to the panel
        bt1.addActionListener(this);         //add an action listener to this button

        tf1 = new TextField();                  //create a new textfield tf1
        tf1.setBounds(320,240,300,50);
        panel1.add(tf1);                        //add this textfield to the panel

        lb1 = new Label(" Welcome! ");     //create a new label with text " Welcome! "
        lb1.setAlignment(1);                    //set alignment to center
        lb1.setFont(new Font("Serif", Font.BOLD, 44));  //set up the font properties
        lb1.setBounds(350,80,250,50);          //set up the size and location
        panel1.add(lb1);                         //add this label to the panel

        lb9 = new Label("Please type in a Canadian city name (the station name) below to search for its weather! ");  //create a new label with the text of instructions
        lb9.setFont(new Font("Serif", Font.PLAIN, 18));           //set up the font properties
        lb9.setBounds(120,170,670,50);                   //set up the size and location
        panel1.add(lb9);                                                     //add this label to the panel

        bt2 = new Button(" Exit");                          //create another button bt2, set up the size and location, then add it to the panel
        bt2.setBounds(900,600,70,40);
        panel1.add(bt2);
        bt2.addActionListener(this);                           //add an action listener to this button

        lb8 = new Label( "");                                //create another label lb8 for displaying invalid inputs, set up the size and location, then add it to the panel
        lb8.setFont(new Font("Serif", Font.BOLD, 18));
        lb8.setBounds(270,300,450,40);
        lb8.setAlignment(1);
        lb8.setBackground(Color.CYAN);
        panel1.add(lb8);

        frame.getContentPane().add(panel1);                       //add panel to the frame
        frame.setSize(1000, 700);                   //set the frame size
        frame.setVisible(true);                                   //make the frame visible
    }


    @Override
    public void actionPerformed(ActionEvent e) {             //here're the action performance codes
        if(e.getSource()==bt1){     //if bt1 (the enter button) is pressed:
            textFieldValue = tf1.getText();    //get the textfield input value
            y = 0;                             //let integer y = 0

            for (int i = 0; i < alldata.length; i++) {    //for every data in array "alldata":

                if (textFieldValue.toLowerCase().equals(alldata[i][0].toLowerCase()) ) {  //if the input matches one of the city names (not case sensitive)

                    y = 1;   //let integer y = 1

                    JFrame frame2 = new JFrame();   //create another Jframe frame2
                    JPanel panel2 = new panel2();  //create a new Jpanel panel2 using the method panel2()
                    panel2.setLayout(null);

                    lb2 = new Label(alldata[i][9]+"°C");      //create another label lb2 for displaying the minimum temperature, set up the size and location, then add it to panel2
                    if (alldata[i][9].equals("")){   //if there is no data for the minimum temperature, print "NA" onto the screen
                        lb2.setText("NA");
                    }
                    lb2.setFont(new Font("Serif", Font.BOLD, 32));
                    lb2.setBounds(60,240,100,50);
                    panel2.add(lb2);

                    lb3 = new Label(alldata[i][7]+"°C");     //create another label lb3 for displaying the maximum temperature, set up the size and location, then add it to panel2
                    if (alldata[i][7].equals("")){  //if there is no data for the maximum temperature, print "NA" onto the screen
                        lb3.setText("NA");
                    }
                    lb3.setFont(new Font("Serif", Font.BOLD, 32));
                    lb3.setBounds(400,240,100,50);
                    panel2.add(lb3);


                    lb4 = new Label( alldata[i][0]);             //create another label lb4 for displaying the city name, set up the size and location, then add it to panel2
                    lb4.setFont(new Font("Serif", Font.BOLD, 24));
                    lb4.setBounds(170,20,250,40);
                    lb4.setAlignment(1);
                    lb4.setBackground(Color.PINK);
                    panel2.add(lb4);

                    lb5 = new Label("Monthly Values for April - 2018");  //create another label lb5 for displaying the time frame of the data, set up the size and location, then add it to panel2
                    lb5.setFont(new Font("Serif", Font.BOLD, 24));
                    lb5.setBounds(120,60,380,50);
                    lb5.setAlignment(1);
                    lb5.setBackground(Color.PINK);
                    panel2.add(lb5);

                    lb6 = new Label(alldata[i][11] + "cm");      //create another label lb6 for displaying the snowfall value, set up the size and location, then add it to panel2
                    if (alldata[i][11].equals("")){     //if there is no data for the snowfall value, print "NA" onto the screen
                        lb6.setText("NA");
                    }
                    lb6.setBounds(35,135,80,27);
                    lb6.setFont(new Font("Serif", Font.BOLD, 24));
                    lb6.setBackground(Color.PINK);
                    panel2.add(lb6);

                    lb7 = new Label(alldata[i][14] + "mm");     //create another label lb7 for displaying the precipitation value, set up the size and location, then add it to panel2
                    if (alldata[i][14].equals("")){     //if there is no data for the precipitation value, print "NA" onto the screen
                        lb7.setText("NA");
                    }
                    lb7.setBounds(495,135,100,27);
                    lb7.setFont(new Font("Serif", Font.BOLD, 24));
                    lb7.setBackground(Color.PINK);
                    panel2.add(lb7);

                    bt3 = new Button(" See Summary");         //create a new button bt3 with text "See Summary", set up the size and location, then add it to panel2
                    bt3.setBounds(230,300,120,50);
                    panel2.add(bt3);
                    bt3.addActionListener(this);               //add an action listener to this button

                    x = "Station Name: " + alldata[i][0] +        //give the string variable x the value of all the data for the specific city along with their data titles, this is the summary which is to be printed later
                            "\nLatitude (North + , degrees): " + alldata[i][1] +
                            "\nLongitude (West - , degrees): " + alldata[i][2] +
                            "\nProvince: " + alldata[i][3] +
                            "\nMean Temperature (°C): " + alldata[i][4] +
                            "\nDays without Valid Mean Temperature: " + alldata[i][5] +
                            "\nMean Temperature difference from Normal (1981-2010) (°C): " + alldata[i][6] +
                            "\nHighest Monthly Maximum Temperature (°C): " + alldata[i][7] +
                            "\nDays without Valid Maximum Temperature: " + alldata[i][8] +
                            "\nLowest Monthly Minimum Temperature (°C): " + alldata[i][9] +
                            "\nDays without Valid Minimum Temperature: " + alldata[i][10] +
                            "\nSnowfall (cm): " + alldata[i][11] +
                            "\nDays without Valid Snowfall: " + alldata[i][12] +
                            "\nPercent of Normal (1981-2010) Snowfall: " + alldata[i][13] +
                            "\nTotal Precipitation (mm): " + alldata[i][14] +
                            "\nDays without Valid Precipitation: " + alldata[i][15] +
                            "\nPercent of Normal (1981-2010) Precipitation: " + alldata[i][16] +
                            "\nSnow on the ground at the end of the month (cm): " + alldata[i][17] +
                            "\nNumber of days with Precipitation 1.0 mm or more: " + alldata[i][18] +
                            "\nBright Sunshine (hours): " + alldata[i][19] +
                            "\nDays without Valid Bright Sunshine: " + alldata[i][20] +
                            "\nPercent of Normal (1981-2010) Bright Sunshine: " + alldata[i][21] +
                            "\nDegree Days below 18 °C: " + alldata[i][22] +
                            "\nDegree Days above 18 °C: " + alldata[i][23] +
                            "\nClimate Identifier: " + alldata[i][24];

                    frame2.getContentPane().add(panel2);      //add panel2 to frame2
                    frame2.setSize(630, 400);   //set the frame size
                    frame2.setVisible(true);                  //make the frame visible
                }
            }
            if (y == 0){    //if the y value remains to be 0
                lb8.setText("Sorry, no city found. Please try enter another city name. ");  //print that the input was invalid
            }else {    //else (y is 1)
                lb8.setText ("");    //remove the text
            }
        }else if(e.getSource()==bt2){    //if bt2 (the exit button) is pressed:
            System.exit(0);          //exit the program
        }else if(e.getSource()==bt3){    //if bt3 (the summary button) is pressed:
            JFrame frame3 = new JFrame();    //create a new Jframe
            JOptionPane.showMessageDialog(frame3, x);  //show the summary info to the frame
        }
    }

    class panel extends JPanel {              //this is the method for drawing the first panel
        public void paint(Graphics display) {
            display.setColor(Color.cyan);     //draw the background with color cyan
            display.fillRect(0, 0, 1000, 700);

            display.setColor(Color.green);    //draw the grass
            display.fillRect(0, 550, 1000, 150);

            display.setColor(Color.yellow);   //draw the sun
            display.fillOval(770, 50, 100, 100);

            display.setColor(Color.white);    //draw three white clouds in three different locations
            display.fillOval(70, 65, 70, 50);
            display.fillOval(110, 50, 70, 70);
            display.fillOval(150, 65, 70, 50);

            display.fillOval(180, 245, 70, 50);
            display.fillOval(220, 230, 70, 70);
            display.fillOval(260, 245, 70, 50);

            display.fillOval(810, 155, 70, 50);
            display.fillOval(850, 140, 70, 70);
            display.fillOval(890, 155, 70, 50);
        }
    }

    class panel2 extends JPanel {              //this is the method for drawing the second panel
        public void paint(Graphics display) {
            display.setColor(Color.PINK);      //draw the background with color pink
            display.fillRect(0,0,630,400);

            for (int i = 0; i < 40; i ++) {     //draw a bunch of rectangles in a row from blue to red, indicating the temperature from the lowest to the highest
                display.setColor( new Color(6*i, 0, 240-6*i));
                display.fillRect(80 + 10*i,200 - 2*i,8 ,30 + 2*i);
            }

            display.drawImage(image1, 35, 80, 70, 50, null);  //add the snowflake image, indicating the snowfall

            display.setColor(Color.gray);                          //draw a gray cloud
            display.fillOval(500, 90, 35, 25);
            display.fillOval(520, 80, 35, 35);
            display.fillOval(540, 90, 35, 25);
            display.setColor(Color.blue);                         //draw three raindrops, together this indicates the rainfall
            display.fillOval(510, 120, 5, 15);
            display.fillOval(530, 110, 5, 15);
            display.fillOval(560, 120, 5, 15);
        }
    }
}
