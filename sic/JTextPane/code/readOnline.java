package ntou.cs.java2021.finalproject;
import java.net.*;
import java.util.ArrayList;
import java.io.BufferedReader; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.net.URL; 
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.lang.*;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Color ;
import javax.swing.text.BadLocationException;
public class readOnline{

	//private String allDataInUrl =null;
	private PrintWriter Write;
	private	Path path = new Path();//先拿到執行中檔案的位置
	private String inputFilePath;
	private JTextPane textPane;
	private JFrame appFrame;
	private String checkUrl="";
	public readOnline(JFrame inFrame,JTextPane inPane,String URL)
	{
		appFrame=inFrame;
		textPane = inPane;
		checkUrl=URL;
		path.takePath();//先拿到執行中檔案的位置
		inputFilePath=path.getInputFilePath();
		System.out.println(path);
		textPane.setText("");
	}
		
	
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
	
	
	public boolean checkWebsiteHTTPS()//,String checkContent)
	{
		try 
		{
			boolean itHaveThing=false;
			URL url = new URL(checkUrl);
			HttpsURLConnection httpsConn = (HttpsURLConnection)url.openConnection();
			InputStream ins = httpsConn.getInputStream();
			BufferedReader breader = new BufferedReader(new InputStreamReader(ins,"UTF-8"));
			String info = breader.readLine();
			long nowTime =new java.util.Date().getTime();
			
			if (info != null)
			{
					try 
					{
						Write = new PrintWriter(inputFilePath+"sicOnline.txt");
						addColoredText(textPane,"write to sicOnline.txt"+"\n",Color.black);
						System.out.printf("write to sicOnline.txt"+"\n");
					}
					catch( FileNotFoundException ER)
					{
						
						System.out.println("write error");
						JOptionPane.showMessageDialog(appFrame,"write error");
						addColoredText(textPane,"write error\n",Color.red);
						return false;
					}	
			}
			while (info != null)
			{
				if(new java.util.Date().getTime() -nowTime >10000)
					return false;
				if(info != null )//&& info.indexOf(checkContent)!=-1)
				{
					Write.printf("%s\n",info);
					addColoredText(textPane,info+"\n",Color.black);			
					System.out.printf("%s\r\n",info);
					itHaveThing=true;
				}
				
				info = breader.readLine();
			}
			addColoredText(textPane,"write sussces to"+inputFilePath+"sicOnline.txt\n",Color.green);
			Write.close();
			breader.close();
			if (itHaveThing==true)
				return true;
			//allDataInUrl=EncodingUtils.getString(baf.toByteArray(),"UTF-8");
			
		}
		catch (Exception e) 
		{
			//System.out.println("Can't get content:"+checkContent +" from URL:"+checkUrl);
			addColoredText(textPane,"The error is:"+e.getMessage()+"\n",Color.red);
			addColoredText(textPane,"\ncan't get thing in https or it's not https\n",Color.red);
			System.out.println("The error is:"+e.getMessage());
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean checkWebsiteHTTP()//,String checkContent)
	{
		
			boolean itHaveThing=false;
			try
			{
				URL u = new URL(checkUrl);
				URLConnection connection = u.openConnection();
				
				HttpURLConnection htCon = (HttpURLConnection) connection;
				int code = htCon.getResponseCode();
				if (code == HttpURLConnection.HTTP_OK)
				{
					
					System.out.println("find the website in http\n");
					addColoredText(textPane,"find the website in http\n",Color.orange);
					BufferedReader in=new BufferedReader(new InputStreamReader(htCon.getInputStream(),"BIG5"));
					String inputLine;
					
					try 
					{
						Write = new PrintWriter(inputFilePath+"sicOnline.txt");
						addColoredText(textPane,"write to sicOnline.txt"+"\n",Color.black);
						System.out.printf("write to sicOnline.txt"+"\n");
					}
					catch( FileNotFoundException ER)
					{
						
						System.out.println("write error");
						JOptionPane.showMessageDialog(appFrame,"write error");
						//textPane.setText(textPane.getText()+"write error\n");
						addColoredText(textPane,"write error\n",Color.red);
						return false;
					}
					
					while ((inputLine = in.readLine()) != null)
					{
							Write.printf("%s\n",inputLine);
							addColoredText(textPane,inputLine+"\n",Color.black);	
							System.out.println(inputLine+"\n");
							itHaveThing=true;
					}
						addColoredText(textPane,"write sussces to"+inputFilePath+"sicOnline.txt\n",Color.green);
						in.close();
						Write.close();
					if(itHaveThing==true)
						return true;
					else
						return false;
				}
				else
				{
					System.out.println("Can not access the website");
					addColoredText(textPane,"Can not access the websit\n",Color.red);
					return false;
				}
        }
        catch(MalformedURLException e )
        { 
			
			addColoredText(textPane,"Wrong URL\n",Color.red);
            System.out.println("Wrong URL");
			return false;
        }
        catch(IOException e)
        {
			addColoredText(textPane,"Can not connec\n",Color.red);
            System.out.println("Can not connect");
			return false;
        }
		
	}

	
	



}


