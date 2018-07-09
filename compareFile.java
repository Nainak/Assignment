import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.text.*;
import java.io.InputStream;
import java.io.IOException;
import java.util.Scanner;

public class compareFile {
    public static void main (String[] args) 
    {
        for (String s: args) {
           if (s.equals("initialize"))
           { 
              Scanner input = new Scanner(System.in);
              System.out.println("Please enter user preference file name : "); 
              String u = input.next();
              System.out.println("Please enter product score file name : ");
              String p = input.next();
              System.out.println("initialize happening");
              initialize(u,p);
              
          } 
          else 
          {
          	Scanner input = new Scanner(System.in);
          	System.out.println("Please enter uid for which you need recommended products : "); 
            String r = input.next();
            System.out.println("Listing top 5 recommended products for  " + r);
            recommend_products(r);
            
          }
        }
    }
	  public static void initialize(String u,String p)
		{
       
      BufferedWriter writer = null;
       try
        {
           
           
           writer = new BufferedWriter(new FileWriter("output_new.txt")); 
           
           writer.write("effective_score");
           writer.write("  ");
           writer.write("uid");
           writer.write("  ");
   		     writer.write("product");
  		     writer.write("  ");
  		     writer.write("value");
   		     writer.write("  ");
  		     writer.write("time");
   		     writer.write("  ");
   		     writer.write("score");
   		     writer.write("  ");
   		     writer.write("effective_value");
   		     
   		     
   		     writer.newLine();			
           
           Scanner scan = new Scanner(new FileReader(u));
           
				    ArrayList<String> uids = new ArrayList<String>();
				    ArrayList<String> products = new ArrayList<String>();
				    ArrayList<String> products1 = new ArrayList<String>();
				    ArrayList<String> scores = new ArrayList<String>();
				    ArrayList<String> values = new ArrayList<String>();
				    ArrayList<String> times = new ArrayList<String>();

				    while(scan.hasNext()){
				        String curLine = scan.nextLine();
				        String[] splitted = curLine.split("     ");
				        String uid = splitted[0].trim();
				        String product1 = splitted[1].trim();
				        String value = splitted[2].trim();
				        String time = splitted[3].trim();
				        
				        
				        if (!"time".equals(time))
				        {
				        		Scanner scanp = new Scanner(new FileReader(p));

				            String score1 = "NONE";
				            while(scanp.hasNext())
				            {
				            	String curLine1 = scanp.nextLine();
				            	String[] splitted1 = curLine1.split("     ");
				            	String product = splitted1[0].trim();
				              String score = splitted1[1].trim();
				              
				            	if (!"score".equals(score))
				            	{
				            		if (product1.equals(product))
				            		{
								            			            		    
				            		   score1 = score; 
				            		}			
				            	}  
				            }
				         
							      double new_score = 0.000;
							      long unixTime = System.currentTimeMillis() / 1000L;
							      long diff = (long) (unixTime - Long.valueOf(time))/(24*60*60);
							      double val = (double)Math.round((Double.valueOf(value)*Math.pow(0.95,diff)) * 1000d) / 1000d;
							      
							      if (!"NONE".equals(score1))
							      {
							        	 new_score = (double)Math.round(Double.valueOf(score1) * val + Double.valueOf(score1));
							      }
							        
							      
				             writer.write(String.valueOf(new_score));
				             writer.write("  ");
				             writer.write(uid);
								     writer.write("  ");
						 		     writer.write(product1);
						         writer.write("  ");
							       writer.write(value);
								     writer.write("  ");
								     writer.write(time);
								     writer.write("  ");
								     writer.write(score1);
								     writer.write("  ");
								     writer.write(String.valueOf(val));
								     
								     

				             writer.newLine();			
				        }
				    }
				    
				    
				    
    				

         
 
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
         try
            {
                if(writer != null)
                {
                    writer.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

  		System.out.println("initialize Done");

		}
   
    public static void recommend_products(String r)
		{
       
       BufferedReader reader = null;
       
       try
        {

           reader = new BufferedReader(new FileReader("output_new.txt"));
           String currentLine = reader.readLine();
           
            ArrayList<String> lines = new ArrayList<String>();

				    while(currentLine != null)
				    {
				        
				        String[] splitted = currentLine.split("  ");
				        String effective_score = splitted[0].trim();
				        String uid = splitted[1].trim();
				        String product1 = splitted[2].trim();
				        String value = splitted[3].trim();
				        String time = splitted[4].trim();
				        String score = splitted[5].trim();
				        String effective_value = splitted[6].trim();
				        
				        
				        
				        	 if (r.equals(uid))
				        	 {
				        		 
				        		 //String[] newString = {effective_score,uid,product1,value,time,score,effective_value};
				        		 lines.add(currentLine);
				        		 
				        		 

				           } 				         
							    
							  currentLine = reader.readLine();
				    }
				    
				    
				   Collections.sort(lines,Collections.reverseOrder());
				   
				    System.out.println("uid" + "  " + "product" + " " + "score");
				    for (int i = 0; i < 5; i++) 
				    {
				   	  //for (String line : lines)
				  	 
                String line = lines.get(i);
                
                String[] spl = line.split("  ");
                System.out.println(spl[1] + "  " + spl[2] + " " + spl[0]);
                 
                System.out.println("\n");
              
            }
				   			   
				    
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
         try
            {
                if(reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

  		

		}
}
