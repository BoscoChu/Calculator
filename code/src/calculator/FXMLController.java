
package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.EmptyStackException;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class FXMLController implements Initializable {
             @FXML
       private TextField process1;
             @FXML
       private TextArea history;
             @FXML
       private Label ans1;
       private Stack<BigDecimal> numberStack;       //using two stack method 
       private Stack<Character> operatorStack;    //using two stack method
       
       
        @FXML
      public void buttonbrackets(ActionEvent e){ //the button of brackets
          if(process1.getText().equalsIgnoreCase((String.valueOf(0)))){ 
              process1.setText(((Button)e.getSource()).getText());}
 
         else{
           String var = process1.getText();
           process1.setText(var+((Button)e.getSource()).getText());
              }
                                                }
          @FXML
          //button of numbers
    public void numbers(ActionEvent e){    
        if(process1.getText().equalsIgnoreCase((String.valueOf(0)))){  //if initial cases: process1=0
            process1.setText(((Button)e.getSource()).getText());}     
         else{
             String var = process1.getText();                            // if not 
             process1.setText(var+((Button)e.getSource()).getText());}     // add the number to label of process1
    }
     
       @FXML
       //button of operator
      public void operator(ActionEvent e){ 
          String var1 = process1.getText();  
          if(process1.getText().equalsIgnoreCase((String.valueOf(0)))){  //initial cases: process1=0
             process1.setText("0"); }  
          
         if(process1.getText().equalsIgnoreCase((String.valueOf(0)))&&((Button)e.getSource()).getText().equals("(")){
          process1.setText(((Button)e.getSource()).getText());}
         
         
         if(process1.getText().equalsIgnoreCase((String.valueOf(0)))&&((Button)e.getSource()).getText().equals("+")){
          process1.setText(((Button)e.getSource()).getText());}
            
       
         if(process1.getText().equalsIgnoreCase((String.valueOf(0)))&&((Button)e.getSource()).getText().equals("-")){
          process1.setText(((Button)e.getSource()).getText());}
        
         if(var1.substring(var1.length()-1).equalsIgnoreCase("(")&&(((Button)e.getSource()).getText().equals("-"))){
              process1.setText(var1+((Button)e.getSource()).getText());
         }
          if(var1.substring(var1.length()-1).equalsIgnoreCase(")")&&(((Button)e.getSource()).getText().equals("-"))){
              process1.setText(var1+((Button)e.getSource()).getText());
         }
                    if(var1.substring(var1.length()-1).equalsIgnoreCase(")")&&(((Button)e.getSource()).getText().equals("+"))){
              process1.setText(var1+((Button)e.getSource()).getText());
         }
              if(var1.substring(var1.length()-1).equalsIgnoreCase("*")&&(((Button)e.getSource()).getText().equals("-"))){
              process1.setText(var1+"("+((Button)e.getSource()).getText());
         }
                       if(var1.substring(var1.length()-1).equalsIgnoreCase("/")&&(((Button)e.getSource()).getText().equals("-"))){
              process1.setText(var1+"("+((Button)e.getSource()).getText());
         }
         
         
         else{              
                    String var = process1.getText();       
             if(numbersOrNot(var.substring(var.length()-1))==true||var.substring(var.length()-1).equalsIgnoreCase(")")){  //if last string is number or ")"     
             process1.setText(var+((Button)e.getSource()).getText());}  // add the operator to label of process1                  
             
                 else{
                    return;}
      
            }
                                        }
       @FXML
       // button of allclean
      public void allClean(ActionEvent e){      
      process1.setText("0");            //reset to 0
      }
        
      @FXML
      // button of cleanentry
      public void cleanEntry(ActionEvent e) throws StringIndexOutOfBoundsException {      
       if(process1.getText().equalsIgnoreCase((String.valueOf(0)))){
           return;   
       }
       else if(process1.getText().length()==1){
            process1.setText("0");
       }
       else{
           process1.setText(process1.getText().substring(0, process1.getText().length() - 1));    //remove the last string
       }
      }
      
       @FXML
      public void cleanHistory(ActionEvent e){ 
      history.setText("");
      }
      
      public boolean numbersOrNot(String str){      //Check If a String Is Numeric
    return str.matches("^[0-9]*$");}        //if match a number->return true

            @FXML
            // button of calculate
      public void buttonCalculate(ActionEvent e){
            StringBuffer sb = new  StringBuffer(process1.getText());
            int last = sb.length()-1;
            if (sb.charAt(last)=='+'||sb.charAt(last)=='-'||sb.charAt(last)=='*'||sb.charAt(last)=='/'||sb.charAt(last)=='.'||sb.charAt(last)=='('){
            history.appendText("\nPlease check your expression!");
               return;
            }
              ans1.setText("");
              calculate(sb);
              if(ans1.getText()!=""){
              process1.setText("0");
              }
      }
                
            //the method of adding zero and * 
       public StringBuffer addZeroAndOperator(StringBuffer numberString){  
           StringBuffer newStr = new StringBuffer();
           if (numberString.charAt(0)=='+'||numberString.charAt(0)=='-'){   //if the first string is '+' ot '-'
               newStr.append("0");      // add the zero in the first position
           }
            for (int i = 0; i < numberString.length(); i++) {
               char c=numberString.charAt(i);
                
                if  (c=='('&&numberString.charAt(i+1)=='-'){
                      newStr.append("(0");      // add the zero:(-9+1) --->(0-9+1)
                      continue;
                  }
                if  (i!=numberString.length()-1&&c==')'&&(numbersOrNot(numberString.substring(i+1,i+2))==true||numberString.charAt(i+1)=='(')){
                      newStr.append(")*");          // add *:(-9+1)(2+3) --->(-9+1)*(2+3)
                      continue;
                }
               if  (i!=0&&c=='('&&(numbersOrNot(numberString.substring(i-1,i))==true||numberString.charAt(i+1)==')')){
                      newStr.append("*(");          // add *:9(-9+1) --->9*(-9+1)
                      continue; 
               }
                       newStr.append(c);
               }
                return newStr;   
           }

       //the method of testing standard
       public int matchBrackets(StringBuffer newStr){
           int match = 0;
           if (newStr == null||newStr.isEmpty()){
               return 2;  //2:empty entry
           }
           for (int i = 0; i <newStr.length(); i++) {
               if(newStr.charAt(i)=='('){
                   match+=1;
               }
                if(newStr.charAt(i)==')'){
                   match-=1;
               }
           }
           if(match==0){
               return 0;       //0: matched
           }
           else{
               return 1;        //1:unmatchable
           }
       }
       
       public boolean isNumber(char ch){
           return(ch>='0'&&ch<='9')||ch == '.';
       }
          public boolean isoperator(char ch){
           return(ch=='+')||(ch=='-')||(ch=='*')||(ch=='/');
       }      
       
       
       
        public BigDecimal calculate (StringBuffer numberString)throws EmptyStackException{

             StringBuffer  show = numberString; // 
             numberString = addZeroAndOperator(numberString); //the method of adding zero and * : (2)1 ---->(2)*1  or  -1*2---->0-1*2
             numberString.append("=");  //add "=" to the last postion

             if(matchBrackets(numberString)==2){ //case 2:empty entry
                 history.appendText("\nThe entry field is empty!"+numberString);
                 return null;
             }
             if(matchBrackets(numberString)==1){ //case 1:unmatchable
                 history.appendText("\nBrackets in an expression is not balanced!");
                 return null;
             }
             
              if (numberStack == null) {//avoid null stack
            numberStack = new Stack<>();
        }
        numberStack.clear();
        if (operatorStack == null) {        
            operatorStack = new Stack<>();//avoid null stack
        }
        operatorStack.clear();
        
        StringBuilder temp = new StringBuilder();
       
        for (int i = 0; i < numberString.length(); i++) {
            char ch = numberString.charAt(i); 
            if (isNumber(ch)) { //if number or dot
                temp.append(ch); //add to temp
            } else { //if not number or dot
                String tempStr = temp.toString(); 
                if (!tempStr.isEmpty()) {//if temp is not empty
                    BigDecimal num = new BigDecimal(tempStr);
                    numberStack.push(num); //send num to the top of numberstack
                    temp = new StringBuilder(); 
                }
                
                while (!comparePrior(ch) && !operatorStack.isEmpty()) { 
                 
                    BigDecimal b = numberStack.pop();
                    BigDecimal a = numberStack.pop();
                    //calculate the value 
                    switch (operatorStack.pop()) {
                        case '+':
                            numberStack.push(a.add(b));
                            break;
                        case '-':
                            numberStack.push(a.subtract(b));
                            break;
                        case '*':
                            numberStack.push(a.multiply(b));
                            break;
                        case '/':
                           
                            numberStack.push(a.divide(b, 10, RoundingMode.HALF_UP));
                            break;
                        default:
                            break;
                    }
                } 
                if (ch != '=') {    //if not last position
                    operatorStack.push(ch); //add to operatorstack
                    if (ch == ')') {    //if the string is )
                        operatorStack.pop();    //delete the top from operatorstack
                        operatorStack.pop();       //delete the top from operatorstack
                    }
                }
            }
        }


                BigDecimal a =numberStack.pop();    //set a = the final answer 
                history.appendText("\n"+show+"="+a);    //show to the history box
                ans1.setText(a.toString());      //show answer to answer field 
                return a;

 }
             
        
             
       public boolean comparePrior (char c){
           if(operatorStack.empty()){
               return true;
           }
           
           char top = operatorStack.peek();
           if(top=='('){
               return true;
           }
           
           switch (c) {
               case '(':
                   return true;     
               case '*':
                    if(top == '+'||top=='-'){
                         return true;       //true only if (the top of operatorstack = + or - and string is * or /)
                    }                           //so we need to calculate
                    else{
                        return false;
                    }
               case '/':
                    if(top=='+'||top=='-'){//true only if (the top of operatorstack = + or - and string is * or /)
                         return true; //so we need to calculate
                    }
                    else{
                        return false;
                    }
               case '+':
                   return false;  
               case '-':
                   return false;
               case ')':
                   return false;   
               case '=':
                   return false;    
               default:
                  break;
           }
           
           return true;
       }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        history.setEditable(false);     //unable to edit history box(read only)
        process1.setEditable(false);        //unable to edit process box (only accept click button)
    }    
    
}
