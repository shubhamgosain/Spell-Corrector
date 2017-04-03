package spellcheker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Spellcheker {
      ArrayList<String>collection = new ArrayList<String>();
    ArrayList<Integer>occurence = new ArrayList<Integer>();
String final_word;
float final_prob;
double total_no_of_words;
    void read() throws IOException
{
        String line;
     String[] Words = null;
     
  FileReader fileReader = new FileReader("C:\\Users\\Family\\Documents\\NetBeansProjects\\spellcheker\\pr.txt ");
  String content = "";
  int reading ;
 while((reading =  fileReader.read())!=-1){
   char ch = (char)reading;
 content = content+ ch; 
  }
content=content.replaceAll("\\.", "");
content=content.replaceAll("\"", "");
content=content.replaceAll("\\-", " ");
content=content.replaceAll("\\_", " ");
content=content.replaceAll("\\(", "");
content=content.replaceAll("\\)", "");
content=content.replaceAll("\\,", "");
content=content.replaceAll("\\?", "");
content=content.replaceAll("\\;","");
content=content.replaceAll("\\!","");
content=content.replaceAll("\\:","");
content=content.replaceAll("\\'","");
content=content.replaceAll("\\:","");

Words = content.toLowerCase().split(" ");
    for(int i=0;i<Words.length;i++)   
      { int j=0;
        if(check_avail(collection,occurence,Words[i])==0)
        {   collection.add(j,Words[i]);
             occurence.add(j,1);
             j++;
        }
      } 
    total_no_of_words=occurence.size();

}

   int check_avail(ArrayList<String> collection, ArrayList<Integer> occurence, String word)
   {    int i=0;
   while(i<collection.size())
        {
                if(collection.get(i).equals(word))
                {
               occurence.set(i,occurence.get(i)+1);
               return 1;
                }   
            i++;
        }   
       return 0;
   }
   
   int check_word(String word)
   {
      
  for(int i=0;i<collection.size();i++)  
          {if(collection.get(i).equals(word.toLowerCase()))
           {
             if(final_prob<occurence.get(i))
                {
                        final_prob=occurence.get(i);
                        final_word=collection.get(i);
              
                }
               return 1;
           }
          }
          return 0;
   }

String input(String str) throws IOException
   {
       
       StringTokenizer st=new StringTokenizer(str);
          String final_line ="";
       while(st.hasMoreTokens())
       {
           
           /*           System.out.print("Enter the word=");
                           final_prob=0;
               String word=sc.nextLine();
*/
           final_prob=0;
          String word =st.nextToken();
               final_word=word;
               word=word.toLowerCase();
                    if(check_word(word)==0)
                    {                deletion(word);
                                   transpose(word);
                                     replace(word);
                                    insert(word);
                    }
                    //float probablity=(float)final_prob/(float)total_no_of_words;
                      //     System.out.println("Word is \""+final_word+"\" with probablity "+probablity*100+"%");
       
           final_line+=final_word+" ";
             
       }                   
       return final_line;
    }

  void  deletion(String word)
    {
        int i=0;
    
    String del;
     while(i<word.length())
           {
           del=word;
           del = del.substring(0, i) + del.substring(i+1);       
           i++;
               check_word(del);
                
           }
    }

  
void transpose(String word)
{

int i=0;
    String del;
    int word_length=word.length();
     while(i<word_length/2)
           {
           del=word;
      char temp=del.charAt(i);
           del=del.replace(del.charAt(i),del.charAt(word_length-1-i));
        char[] myNameChars = del.toCharArray();
           myNameChars[word_length-1-i]=temp;
           del = String.valueOf(myNameChars);

           i++;
               check_word(del);
               
           }
}

void replace(String word)
{
int i=0;
    String del;
    int word_length=word.length();
    String alph1="abcdefghijklmnopqrstuvwxyz";
    char[] alph2=alph1.toCharArray();
     while(i<word_length)
           {
                for(int j=0;j<26;j++)
                   {    del=word;
                       char[] myNameChars = del.toCharArray();
                     myNameChars[i]=alph2[j];
                     del = String.valueOf(myNameChars);

                          check_word(del);
                 
                   }
              i++;
           }
    }

void insert(String word)
    {
        int i=0;
        String del;
        String alph1="abcdefghijklmnopqrstuvwxyz";
        char[] alph2=alph1.toCharArray();
     while(i<=word.length())
           {
               for(int j=0;j<26;j++)
               {
                       del=word;
                       del = del.substring(0, i) +alph2[j]+ del.substring(i);  
                            check_word(del);
                             }
           i++;
           }
    }

public static void main(String[] args) throws IOException {
                        Spellcheker spc=new Spellcheker();
                        spc.read();
                       
    
    }

    
}
