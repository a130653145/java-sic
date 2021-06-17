package ntou.cs.java2021.finalproject;

// Displays text using font.
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Color ;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


//全螢幕
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
//改右上角icon 
//import java.awt.Toolkit;
import java.io.File;
//改右上角icon
public class finalProject {

	private JFrame appFrame; // 定義為靜態變數以便main使用 並把這個視窗命名為 appFrame
	private JButton startToChangeButton; // button to start // 這裡定義按鈕元件  以便讓ActionListener使用
	private JButton readOutputFileButton; // button to read  // 這裡定義按鈕元件  以便讓ActionListener使用
	private JButton saveFileButton; // button to read  // 這裡定義按鈕元件  以便讓ActionListener使用
	private JButton openFileButton; // button to read  // 這裡定義按鈕元件  以便讓ActionListener使用
	private JButton readInputFileButton; // button to read  // 這裡定義按鈕元件  以便讓ActionListener使用
	private JButton descriptionButton; // button to read  // 這裡定義按鈕元件  以便讓ActionListener使用
	private JButton upTextsizeButton; // button to read  // 這裡定義按鈕元件  以便讓ActionListener使用
	private JButton downTextsizeButton; // button to read  // 這裡定義按鈕元件  以便讓ActionListener使用
	private JButton urlButton; // button to read  // 這裡定義按鈕元件  以便讓ActionListener使用
	private JButton readOtherFileButton; // button to read  // 這裡定義按鈕元件  以便讓ActionListener使用
	private JLabel label0;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JTextPane text; // displays example text 
	//其實上面最原始 是用	JTextArea text 寫檔
	//但之後改成 JTextPane (因為他能逐一分別上文字顏色)
	//下面的 text.setText("XXXX");改成 addColoredText(text,"XXXX",Color.XXX);
	//JTextPane JTextPane 都能通用 text.setText("XXXX"); 但是是黑的
	private JTextField inputNameToChangeTextBox1;
	private JTextField inputNameToReadTextBox1; 	
	private JTextField inputNameToReadTextBox2;
	private JTextField inputUrlToReadTextBox; 	
	private String textBefore;
	
	private String imgPath;
	private String inputFilePath;
	private String outputFilePath;
	private JFileChooser chooser ;
	private int textSize=20;
	
	public void addColoredText(JTextPane pane, String text, Color Color) {
        StyledDocument doc = pane.getStyledDocument();
		
        Style style = pane.addStyle("Color Style", null);
		
        StyleConstants.setForeground(style, Color);
        try 
		{
            doc.insertString(doc.getLength(), text, style);
        } 
		
        catch (BadLocationException e) {
            e.printStackTrace();
        }
	}
	
	public finalProject() { // 構造器, 建立圖形介面
	
	
		appFrame = new JFrame("Font Test");
		
		
		
	
		chooser = new JFileChooser();
		
		Path path = new Path();//先拿到執行中檔案的位置
		path.takePath();//先拿到執行中檔案的位置
		imgPath=path.getImgPath();
		inputFilePath=path.getInputFilePath();
		outputFilePath=path.getOutputFilePath();
		System.out.println(path);

		//改右上角icon
		//appFrame.setIconImage(Toolkit.getDefaultToolkit().createImage("\\"+System.getProperty("user.dir")+"\\img\\"+"321.png"));
		appFrame.setIconImage(Toolkit.getDefaultToolkit().createImage(imgPath+"321.png"));
		
		appFrame.setVisible(true); // display Fram
		//載入動畫
		try
		{
			//載入畫面-歡迎使用
			//SplashWindow3 a3 = new SplashWindow3("\\"+System.getProperty("user.dir")+"\\img\\"+"SIC.png",appFrame,2000);
			SplashWindow3 a3 = new SplashWindow3(imgPath+"SIC.png",appFrame,2000);
			Thread.sleep(1900);//等他1.9秒
			//載入畫面-LOADING
			//SplashWindow3 a = new SplashWindow3("\\"+System.getProperty("user.dir")+"\\img\\"+"loading.gif",appFrame,2500);
			SplashWindow3 a = new SplashWindow3(imgPath+"loading.gif",appFrame,2500);
			Thread.sleep(2400);//等他2.4秒
		}
		catch(Exception e)
		{
			e.printStackTrace();
			// 能夠捕獲InvocationTargetException
			// 能夠捕獲InterruptedException
		}

		MyEventListner handler = new MyEventListner();//建立一個名為handler的觸發事件並觸發名為MyEventListner的FUNTION(下面實作)
		
		// create buttons and add action listeners
		saveFileButton=new JButton("令存outputFile資料夾內的轉化檔(任意名稱)");
		startToChangeButton = new JButton("開始轉化目地碼"); 
		readOutputFileButton = new JButton("讀取(outputFile資料夾內)"); 
		readInputFileButton = new JButton("讀取(inputFile資料夾內)");
		descriptionButton= new JButton("說明");
		upTextsizeButton = new JButton("放大文字");// 
		downTextsizeButton = new JButton("縮小文字");//
		urlButton = new JButton("開始網路下載");//
		openFileButton = new JButton("開啟檔案(讀取任意地方的檔案並轉化,轉化檔會在同一資料夾)");//
		readOtherFileButton = new JButton("讀取檔案(任意地方的檔案)");//
		readOtherFileButton.addActionListener(handler);//按鈕被出發會做 handler FUNTION
		openFileButton.addActionListener(handler);//按鈕被出發會做 handler FUNTION
		urlButton.addActionListener(handler);//按鈕被出發會做 handler FUNTION
		saveFileButton.addActionListener(handler);//按鈕被出發會做 handler FUNTION
		upTextsizeButton.addActionListener(handler);//按鈕被出發會做 handler FUNTION
		downTextsizeButton.addActionListener(handler);//按鈕被出發會做 handler FUNTION
		startToChangeButton.addActionListener(handler);//按鈕被出發會做 handler FUNTION
		readOutputFileButton.addActionListener(handler);//按鈕被出發會做 handler FUNTION
		readInputFileButton.addActionListener(handler);//按鈕被出發會做 handler FUNTION
		descriptionButton.addActionListener(handler);//按鈕被出發會做 handler FUNTION
		// create text area and set initial font
		text = new JTextPane();
		addColoredText(text,"NOTHING",Color.RED);
		Font font = new Font("細明體", Font.BOLD, textSize);
		text.setFont(font);
		inputNameToChangeTextBox1= new JTextField ("SIC.txt",20);
		inputNameToReadTextBox1= new JTextField ("SIC_Final.txt",20);
		inputNameToReadTextBox2= new JTextField ("SIC.txt",20);
		inputUrlToReadTextBox= new JTextField ("https://raw.githubusercontent.com/andy6804tw/SIC/master/SIC.txt",40);
		// add GUI components to Frame
		JPanel urlPanel = new JPanel(); // used to get proper layout
		JPanel changeInputFilePanel = new JPanel(); // used to get proper layout
		JPanel readOutputFilePanel = new JPanel(); // used to get proper layout
		JPanel readInputFilePanel = new JPanel(); // used to get proper layout
		JPanel textSizeAndDescriptionPanel = new JPanel(); // used to get proper layout
		JPanel otherPathThingPanel = new JPanel(); // used to get proper layout	
		JPanel groupPanels = new JPanel(); // used to get proper layout
		//定義元件 顯示文字按鈕
		label0=new JLabel("輸入網址 如果成功他會下載成\"sicOnline.txt\"in inputFile資料夾");
		urlPanel.add(label0);
		urlPanel.add(inputUrlToReadTextBox);
		urlPanel.add(urlButton);
		label2=new JLabel("輸入你要顯示/讀取在output資料夾中的檔案(包括檔案類別)EX:123.txt/XXX_Final.txt(轉化後)");
		readOutputFilePanel.add(label2);
		readOutputFilePanel.add(inputNameToReadTextBox1);
		readOutputFilePanel.add(readOutputFileButton);

		label3=new JLabel("顯示/讀取檔案在input資料夾中的檔案(包括檔案類別)EX:SIC.txt/SIC.asm/OAO.txt");
		readInputFilePanel.add(label3);
		readInputFilePanel.add(inputNameToReadTextBox2);
		readInputFilePanel.add(readInputFileButton);

		label1=new JLabel("輸入你要轉化的SICCode(含檔案類別)如果沒有顯示write Succse代表你有寫東西但文件格式不是SIC EX:OAO.txt,SIC.txt");
		changeInputFilePanel.add(label1);
		changeInputFilePanel.add(inputNameToChangeTextBox1);
		changeInputFilePanel.add(startToChangeButton);


		
		textSizeAndDescriptionPanel.add(upTextsizeButton);
		textSizeAndDescriptionPanel.add(descriptionButton);
		textSizeAndDescriptionPanel.add(downTextsizeButton);
		
		otherPathThingPanel.add(saveFileButton);
		otherPathThingPanel.add(readOtherFileButton);
		otherPathThingPanel.add(openFileButton);
		GridLayout layout = new GridLayout(6,1);
		groupPanels.setLayout(layout);
		groupPanels.add(urlPanel);
		groupPanels.add(readInputFilePanel);
		groupPanels.add(changeInputFilePanel);
		groupPanels.add(readOutputFilePanel);
		groupPanels.add(otherPathThingPanel);
		groupPanels.add(textSizeAndDescriptionPanel);
		appFrame.add(groupPanels,BorderLayout.NORTH); // add buttons at top
		appFrame.add(new JScrollPane(text)); // allow scrolling 有下拉滾輪
		
		//顯示
		//載入成功畫面
		//SplashWindow3 a2 = new SplashWindow3("\\"+System.getProperty("user.dir")+"\\img\\"+"loadSucess.png",appFrame,2000);
		SplashWindow3 a2 = new SplashWindow3(imgPath+"loadSucess.png",appFrame,2000);
		//appFrame.setUndecorated(true); // 無邊框
		//appFrame.setSize(700, 600);//set size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle bounds = new Rectangle(screenSize);
		appFrame.setBounds(bounds);
		
		//用下面那個//可以全螢幕但是他在彈窗警告時會最小化
		//appFrame.getGraphicsConfiguration().getDevice().setFullScreenWindow(appFrame); // set Frame size
		
	}

	private class MyEventListner implements ActionListener  
	{
		// TODO
		public void actionPerformed(ActionEvent e)
		{
			 
				String buttonName = e.getActionCommand();
				if (buttonName.equals("開始轉化目地碼"))
				{
					text.setText("");
					Assamble textWrite =new Assamble(appFrame,text,inputNameToChangeTextBox1.getText());
					textWrite.write();
				}
				else if (buttonName.equals("讀取(outputFile資料夾內)"))
				{
					text.setText("");
					readFile read = new readFile(appFrame,text,outputFilePath+inputNameToReadTextBox1.getText());
					read.displayFileText();
				}
				else if (buttonName.equals("讀取(inputFile資料夾內)"))
				{
					text.setText("");
					readFile read = new readFile(appFrame,text,inputFilePath+inputNameToReadTextBox2.getText());
					read.displayFileText();
				}
				else if (buttonName.equals("說明"))
				{
					
						 SplashWindow3 a2 = new SplashWindow3(imgPath+"SICADDR.jpg",appFrame,6000);
				}
				else if (buttonName.equals("縮小文字"))
				{
					if(textSize>15)
						textSize--;
					Font font = new Font("細明體", Font.BOLD, textSize);
					text.setFont(font);
				}
				else if (buttonName.equals("放大文字"))
				{
					if(textSize<30)
					textSize++;
					Font font = new Font("細明體", Font.BOLD, textSize);
					text.setFont(font);
				}	
				else if (buttonName.equals("開始網路下載"))
				{
					text.setText("");
					inputNameToReadTextBox2.setText("sicOnline.txt");
					inputNameToReadTextBox1.setText("sicOnline_Final.txt");
					inputNameToChangeTextBox1.setText("sicOnline.txt");
					readOnline read =new readOnline(appFrame,text,inputUrlToReadTextBox.getText());
					if(read.checkWebsiteHTTPS()!=true)
						read.checkWebsiteHTTP();
				}
				else if (buttonName.equals("令存outputFile資料夾內的轉化檔(任意名稱)"))
				{
					
					int flag = chooser.showOpenDialog(appFrame);
					//若選擇了檔案，則列印選擇了什麼檔案
					if (flag == JFileChooser.APPROVE_OPTION) 
					{
						text.setText("");
						System.out.println("使用者選擇了位置"+"：" + chooser.getCurrentDirectory());
						addColoredText(text,"使用者選擇了位置：" + chooser.getCurrentDirectory()+"\n",Color.BLACK);
						Assamble textWrite =new Assamble(appFrame,text,inputNameToChangeTextBox1.getText(),chooser.getCurrentDirectory().toString(),chooser.getSelectedFile().getName());
						textWrite.write();
					}
		
				}
				else if (buttonName.equals("開啟檔案(讀取任意地方的檔案並轉化,轉化檔會在同一資料夾)"))
				{
					int flag = chooser.showOpenDialog(appFrame);
					//若選擇了檔案，則列印選擇了什麼檔案
					if (flag == JFileChooser.APPROVE_OPTION) 
					{
						text.setText("");
						System.out.println("使用者選擇了檔案"+"：" + chooser.getSelectedFile().getAbsolutePath());
						addColoredText(text,"使用者選擇了檔案：" + chooser.getSelectedFile().getAbsolutePath()+"\n",Color.BLACK);
						Assamble textWrite =new Assamble(appFrame,text,chooser.getSelectedFile().getName(),chooser.getCurrentDirectory().toString());
						textWrite.write();
					}
				}
				else if (buttonName.equals("讀取檔案(任意地方的檔案)"))
				{
					int flag = chooser.showOpenDialog(appFrame);
					//若選擇了檔案，則列印選擇了什麼檔案
					if (flag == JFileChooser.APPROVE_OPTION) 
					{
						text.setText("");
						System.out.println("使用者選擇了檔案"+"：" + chooser.getSelectedFile().getAbsolutePath());
						addColoredText(text,"使用者選擇了檔案：" + chooser.getSelectedFile().getAbsolutePath()+"\n",Color.BLACK);
						readFile read = new readFile(appFrame,text,chooser.getSelectedFile().getAbsolutePath());
						read.displayFileText();
					}
				}
		}

	}
	public JFrame getFrame() {
		return appFrame;
	}

} // end class EcofontFrame
