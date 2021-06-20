/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author BATU
 */
public class DatabaseProcess {
    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement =null;
    private PreparedStatement preparedStatementGetId =null;
       static String personId;
    
    
    
       public String getDate(){
           String adressId=null;
           String hangarName =null;
            String arrivalDate=null;
           try {                    
                
               String command = "Select adressId from person where idperson="+personId;
                    preparedStatement = con.prepareStatement(command);
                    ResultSet rs = preparedStatement.executeQuery();
                    if(rs.next()){
                        adressId= rs.getString(1);
                    }
                   String seconCoomand = "Select idhangar from hangar where addressId="+adressId;
                    preparedStatement=con.prepareStatement(seconCoomand);
                    ResultSet rs1 = preparedStatement.executeQuery();
                    
                    if(rs1.next()){
                        hangarName = rs1.getString(1);
                    }
                    
                    String command4 = "Select arrivalDate from giveaway where hangarId="+1;
                    
                  preparedStatement = con.prepareStatement(command4);
                    
                    ResultSet rs4 = preparedStatement.executeQuery();
                   
                    if(rs4.next()){
                        arrivalDate= rs4.getString(1);
                    }
                    System.out.println(arrivalDate);
                    
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(clothStatus.class.getName()).log(Level.SEVERE, null, ex);
                }
         return arrivalDate;
       }
       public void update_clothNumber(){
          String command = "Select * from user where username =?";
           String userid=null;
           String count_number=null;
           try {
          
             preparedStatement = con.prepareStatement(command);      
                preparedStatement.setString(1,LoginPage.userName);       
                ResultSet rs1 = preparedStatement.executeQuery();
                
                while(rs1.next()){
                   
                    userid = rs1.getString("iduser");
                       
                }
                    String secondCommand = "Select count(*) from cloth where userId="+userid;
                     preparedStatement= con.prepareStatement(secondCommand);
                     ResultSet rs2 = preparedStatement.executeQuery();          
                     while(rs2.next()){
                         count_number = rs2.getString(1);
                     }
                            int cou = Integer.valueOf(count_number) +1;
                        String thridCommand = "Update clothgiver set clothGivenCount = "+cou+ " where idclothgiver= "+userid;
                                    preparedStatement = con.prepareStatement(thridCommand);      
                                        preparedStatement.executeUpdate();
                            
                            
           } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    
    
       public Integer cloth_number(){        
           String command = "Select * from user where username =?";
           String userid=null;
           String count_number=null;
           try {
          
             preparedStatement = con.prepareStatement(command);      
                preparedStatement.setString(1,LoginPage.userName);       
                ResultSet rs1 = preparedStatement.executeQuery();
                
                while(rs1.next()){
                   
                    userid = rs1.getString("iduser");
                       
                }
                    String secondCommand = "Select count(*) from cloth where userId="+userid;
                     preparedStatement= con.prepareStatement(secondCommand);
                     ResultSet rs2 = preparedStatement.executeQuery();          
                     while(rs2.next()){
                         count_number = rs2.getString(1);
                     }
           } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.valueOf(count_number);
       }
    
   
        public void insertCloth(String cloth_size, String cloth_type,String hangar_name, String date){
           String getId = "Select * from hangar where hangarName =? ";          
           String hangarId=null;           
           String clothid=null;
           int clothGiverId=5;
           try {
             preparedStatementGetId = con.prepareStatement(getId);
                 preparedStatementGetId.setString(1,hangar_name);
            ResultSet rs = preparedStatementGetId.executeQuery();
           while(rs.next()){
              hangarId= rs.getString(1);
           }
           String command = "Select * from user where username =?";
           String userid =null;
             preparedStatement = con.prepareStatement(command);
           
                preparedStatement.setString(1,LoginPage.userName);
            
                ResultSet rs1 = preparedStatement.executeQuery();
                
                while(rs1.next()){
                   
                    userid = rs1.getString("iduser");
                  
                }
            String insert_command = "INSERT INTO `cloth`(`userId`, `clothSize`, `clothType`, `hangarId`) VALUES (?,?,?,?)";
            preparedStatement = con.prepareStatement(insert_command);
             preparedStatement.setInt(1, Integer.valueOf(userid));
                 preparedStatement.setString(2,cloth_size);
                     preparedStatement.setString(3, cloth_type);
                         preparedStatement.setInt(4, Integer.valueOf(hangarId));
                            preparedStatement.executeUpdate();
                    
                String clothId = "Select * from cloth where userId=?";
                preparedStatement = con.prepareStatement(clothId);
                    preparedStatement.setString(1,userid);
                ResultSet rs2 = preparedStatement.executeQuery();
                while(rs2.next()){
                    clothId = rs2.getString("idcloth");           
                }
    
                String insert_giveaway = "INSERT INTO `giveaway`(`clothId`,`takenDate`, `clothgiverId`,`hangarId` ) VALUES (?,?,?,?)";
                  preparedStatement =con.prepareStatement(insert_giveaway);
                    preparedStatement.setInt(1,Integer.valueOf(clothId));
                        preparedStatement.setString(2, date);
  
                            preparedStatement.setInt(3, Integer.valueOf(userid));
                            clothGiverId++;
                  
                                preparedStatement.setInt(4, Integer.valueOf(hangarId));
                                      preparedStatement.executeUpdate();
                  
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        /*
        public ArrayList<clothCompletedStatus> cloth_ArrayList(String name){
                    String command = "Select * from user where username=?";
                    ArrayList<clothCompletedStatus> arraylist = new ArrayList<>();
                    String temp=null;             
        try {
            preparedStatement = con.prepareStatement(command);
               preparedStatement.setString(1,name);            
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    temp = rs.getString("iduser");
                }
            String getClothInfo = "Select * from cloth where userId="+temp;
             preparedStatement = con.prepareStatement(getClothInfo);
                 ResultSet rs2 = preparedStatement.executeQuery(getClothInfo);
                        String clothSize="";
                        String clothType="";
                        String hangarId="";
                        String hangarName="";
                        
                 if(rs2.next()){
                         clothSize = rs2.getString("clothSize");
                         clothType = rs2.getString("clothType");
                         hangarId = rs2.getString("hangarId");
                    }
                        
                    String hangarNameCommand = "Select hangarName from hangar where idhangar="+hangarId;
                    preparedStatement = con.prepareStatement(hangarNameCommand);
                       ResultSet rs3 = preparedStatement.executeQuery();
                       if(rs3.next()){
                           hangarName = rs3.getString(1);
                       }
                      arraylist.add(new clothCompletedStatus(clothSize, clothType, hangarName));
                      return  arraylist;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
            return null;
        }*/
        
        public ArrayList<clothCompletedStatus> clothArrivedTable(String name){
                    String command = "select * from arrived_cloths where username=?";
                    ArrayList<clothCompletedStatus> arraylist = new ArrayList<clothCompletedStatus>();
                    String temp=null;             
        try {
            preparedStatement = con.prepareStatement(command);
               preparedStatement.setString(1,name);            
                ResultSet rs = preparedStatement.executeQuery();
                    
                String HangarName=null;
                String ClothSize=null;
                String ClothType=null;
                
                while(rs.next()){
                    HangarName=rs.getString("hangarName");
                    ClothType=rs.getString("clothType");
                    ClothSize=rs.getString("clothSize");
                     arraylist.add(new clothCompletedStatus(ClothSize, ClothType, HangarName));
                }
                
                    
                
                     
                      return  arraylist;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
            return null;
        }
        
        
        
        //kopyala ,update transport
         public ArrayList<clothCompletedStatus> clothOnWayTable(String name){
                    String command = "select * from on_the_way_cloths where username=?";
                    ArrayList<clothCompletedStatus> arraylist = new ArrayList<>();
                    String temp=null;             
        try {
            preparedStatement = con.prepareStatement(command);
               preparedStatement.setString(1,name);            
                ResultSet rs = preparedStatement.executeQuery();
                    
                String HangarName=null;
                String ClothSize=null;
                String ClothType=null;
                
                while(rs.next()){
                    HangarName=rs.getString("hangarName");
                    ClothType=rs.getString("clothType");
                    ClothSize=rs.getString("clothSize");
                    arraylist.add(new clothCompletedStatus(ClothSize, ClothType, HangarName));
                }
                
                    
                
                      
                      return  arraylist;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
            return null;
        }
         
         
         public ArrayList<ActiveRoutes> updateTransportationTable(String name){
                    String command = "select * from on_the_way_cloths where hangarName =?";
                    ArrayList<ActiveRoutes> arraylist = new ArrayList<ActiveRoutes>();
                    String temp=null;             
        try {
            preparedStatement = con.prepareStatement(command);
               preparedStatement.setString(1,name);            
                ResultSet rs = preparedStatement.executeQuery();
                    
                int ClothId=-1;
                String ClothSize=null;
                String ClothType=null;
                int TransporterId = -1;
                
                while(rs.next()){
                    ClothId=rs.getInt("clothId");
                    ClothType=rs.getString("clothType");
                    ClothSize=rs.getString("clothSize");
                    TransporterId=rs.getInt("transporterId");
                    arraylist.add(new ActiveRoutes(ClothId,ClothType,ClothSize,TransporterId));
                }
                
                    
                
                      
                  return  arraylist;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
            return null;
        }
         
         
         
         
         
         
         
         
          public ArrayList<ActiveRoutes> updateClothEntranceTable(String name){
                    String command = "select * from on_the_way_cloths where hangarName =?";
                    ArrayList<ActiveRoutes> arraylist = new ArrayList<ActiveRoutes>();
                    String temp=null;             
        try {
            preparedStatement = con.prepareStatement(command);
               preparedStatement.setString(1,name);            
                ResultSet rs = preparedStatement.executeQuery();
                    
                int ClothId=-1;
                String ClothSize=null;
                String ClothType=null;
                int TransporterId = -1;
                
                while(rs.next()){
                    ClothId=rs.getInt("clothId");
                    ClothType=rs.getString("clothType");
                    ClothSize=rs.getString("clothSize");
                    TransporterId=rs.getInt("transporterId");
                    arraylist.add(new ActiveRoutes(ClothId,ClothType,ClothSize,TransporterId));
                }
                
                    
                
                      
                  return  arraylist;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
            return null;
        }
         
         
         
         
         
         
         
         
        
         public ArrayList<clothCompletedStatus> takeCloth(String name){
                    String command = "select * from arrived_cloths where username=?";
                    ArrayList<clothCompletedStatus> arraylist = new ArrayList<clothCompletedStatus>();
                    String temp=null;             
        try {
            preparedStatement = con.prepareStatement(command);
               preparedStatement.setString(1,name);            
                ResultSet rs = preparedStatement.executeQuery();
                    
                String HangarName=null;
                String ClothSize=null;
                String ClothType=null;
                
                while(rs.next()){
                    HangarName=rs.getString("hangarName");
                    ClothType=rs.getString("clothType");
                    ClothSize=rs.getString("clothSize");
                     arraylist.add(new clothCompletedStatus(ClothSize, ClothType, HangarName));
                }
                
                    
                
                     
                      return  arraylist;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
            return null;
        }
         
         
         public ArrayList<clothCompletedStatus> clothAvaliableTable(String name){
                    String command = "select * from cloths_waiting_for_transfer where username=?";
                    ArrayList<clothCompletedStatus> arraylist = new ArrayList<clothCompletedStatus>();
                    String temp=null;             
        try {
                preparedStatement = con.prepareStatement(command);
                preparedStatement.setString(1,name);            
                ResultSet rs = preparedStatement.executeQuery();
                    
                String HangarName=null;
                String ClothSize=null;
                String ClothType=null;
                
                while(rs.next()){
                    HangarName=rs.getString("hangarName");
                    ClothType=rs.getString("clothType");
                    ClothSize=rs.getString("clothSize");
                     arraylist.add(new clothCompletedStatus(ClothSize, ClothType, HangarName));
                }
                
                    
                
                     
                      return  arraylist;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
            return null;
        }
        
         
         
         
         
         
         
         
         public ArrayList<ClothTable> hangarCloth(String name){
                    String command = "select * from avaliable_cloths_in_given_hangar where hangarName = ?";
                    ArrayList<ClothTable> arraylist = new ArrayList<ClothTable>();
                               
        try {
                preparedStatement = con.prepareStatement(command);
                preparedStatement.setString(1,name);            
                ResultSet rs = preparedStatement.executeQuery();
                    
                int ClothId = -1;
                String ClothType=null;
                String ClothSize=null;
                
                
                while(rs.next()){
                    ClothId=rs.getInt("idcloth");
                    ClothType=rs.getString("clothType");
                    ClothSize=rs.getString("clothSize");
                    arraylist.add(new ClothTable(ClothId,ClothType,ClothSize));
                }
                
                    
                
                     
                      return  arraylist;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
            return null;
        }
         
         
         
         
         
         
         
         public ArrayList<ClothTaker> hangarClothTaker(){
                    String command = "select * from clothTaking_person";
                    ArrayList<ClothTaker> arraylist = new ArrayList<ClothTaker>();
                               
        try {
                preparedStatement = con.prepareStatement(command);           
                ResultSet rs = preparedStatement.executeQuery();
                    
                int clothTakerId=-1;
                String Name=null;
                String Surname=null;
                String UpperSize=null;
                String LowerSize=null;
                String City=null;
                String District=null;
                
                
                while(rs.next()){
                    clothTakerId=rs.getInt("idperson");
                    Name=rs.getString("name");
                    Surname=rs.getString("surname");
                    UpperSize=rs.getString("upperSize");
                    LowerSize=rs.getString("lowerSize");
                    City=rs.getString("city");
                    District=rs.getString("district");
                    arraylist.add(new ClothTaker(clothTakerId,Name,Surname,UpperSize,LowerSize,City,District));
                }
                
                    
                
                     
                      return  arraylist;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
            return null;
        }
         
         
         
         
         
         
         public ArrayList<Transporter> hangarTransporter(String name){
                    String command = "select * from transporter_information where hangarName =?";
                    ArrayList<Transporter> arraylist = new ArrayList<Transporter>();
                               
        try {
                preparedStatement = con.prepareStatement(command);
                preparedStatement.setString(1,name); 
                ResultSet rs = preparedStatement.executeQuery();
                    
                int TransporterId = -1;
                String Name=null;
                String Surname=null;
                
                
                
                
                while(rs.next()){
                    TransporterId=rs.getInt("idtransporter");
                    Name=rs.getString("name");
                    Surname=rs.getString("surname");
                    arraylist.add(new Transporter(TransporterId,Name,Surname));
                }
                
                    
                
                     
                      return  arraylist;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
            return null;
        }
        
        
        
        
        
        
    
        public ArrayList<person> comesUser(String getterId){
            String command = "Select * from user where username =?";
            ArrayList<person> person = new ArrayList<person>();
            String temp =null;
        try {            
            preparedStatement = con.prepareStatement(command);
            
                preparedStatement.setString(1,getterId);
            
                ResultSet rs = preparedStatement.executeQuery();
                
                while(rs.next()){
                   
                    temp = rs.getString("personId");
                   
                }
                    personId=temp;
            String secondCommand = "Select * from person where idperson ="+temp;
            preparedStatement = con.prepareStatement(secondCommand);
            ResultSet rs2 = preparedStatement.executeQuery(secondCommand);
                if(rs2.next()){
                    String name = rs2.getString("name");
                    String surname = rs2.getString("surname");
                    String sex = rs2.getString("sex");
                    person.add(new person(name, surname,sex));
                }
            return  person;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return null;
        }
    public void insertTransporter(String name,String surname,String sex,String city,String district,String hangarName){
            
            String getId = "Select idadress from adress where city =? and district =?";
            String getIdPerson = "Select idperson from person where name =? and surname = ?";
            String command = "INSERT INTO `person`(`name`, `surname`, `sex`, `adressId`) VALUES (?,?,?,?)";
            String insertTransporter = "INSERT INTO `transporter`(`personId`, `hangarId`) VALUES (?,?)";
            String addressId = null;
            String personId = null;
            String hangarId = null;
            String getHangarId="Select idhangar from hangar where hangarName =?";
           
            
         try {
            preparedStatementGetId = con.prepareStatement(getId);
            preparedStatementGetId.setString(1,city);
            preparedStatementGetId.setString(2,district);
            ResultSet rs = preparedStatementGetId.executeQuery();
           if(rs.next()){
              addressId= rs.getString(1);
           }
            System.out.println("adress Id " +addressId);
           if(addressId == null){
                String insertCitywithDistrict = "INSERT INTO `adress`(`city`, `district`) VALUES (?,?)";
                preparedStatement = con.prepareCall(insertCitywithDistrict);
                preparedStatement.setString(1, city);
                preparedStatement.setString(2, district);
                preparedStatement.executeUpdate(); 
           }
           
           String CityIdCommand = "Select idadress from adress where city =? and district=?";
           
            preparedStatementGetId = con.prepareStatement(CityIdCommand);
            preparedStatementGetId.setString(1,city);
            preparedStatementGetId.setString(2, district);
            ResultSet rs3 = preparedStatementGetId.executeQuery();
            while(rs3.next()){
               addressId= rs3.getString("idadress");
            }
           
           
           
             preparedStatement = con.prepareStatement(command);
                preparedStatement.setString(1,name);
                    preparedStatement.setString(2, surname);
                        preparedStatement.setString(3, sex);
                            preparedStatement.setInt(4, Integer.parseInt(addressId));
                                preparedStatement.executeUpdate();
             
             
            preparedStatementGetId = con.prepareStatement(getIdPerson);
            preparedStatementGetId.setString(1,name);
            preparedStatementGetId.setString(2,surname);
            ResultSet rs2 = preparedStatementGetId.executeQuery();
           if(rs2.next()){
              personId= rs2.getString(1);
           }              
           
             
           preparedStatementGetId = con.prepareStatement(getHangarId);
            preparedStatementGetId.setString(1,hangarName);
            ResultSet rs4 = preparedStatementGetId.executeQuery();
           if(rs4.next()){
              hangarId= rs4.getString(1);
           }   
                      
            
         
            
            
             preparedStatement = con.prepareStatement(insertTransporter);
             
                     preparedStatement.setInt(1,Integer.parseInt(personId));
                         preparedStatement.setInt(2, Integer.parseInt(hangarId));
                         
             
                             preparedStatement.executeUpdate();
             
                
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           //sho preparedStatementgetAdressId = con
        }
        
public int getHangarId(String hangarName) {
    int hangarId=-1;
        try {
            String command = "Select idhangar from hangar where hangarName =?";
            preparedStatementGetId = con.prepareStatement(command);
            preparedStatementGetId.setString(1,hangarName);
            ResultSet rs4 = preparedStatementGetId.executeQuery();
            if(rs4.next()){
                hangarId= rs4.getInt("idhangar");
            }   
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hangarId;
}
public void insertPersonInNeed(String name,String surname,String sex,String city,String district,String lowerSize,String upperSize){
            
            String getId = "Select idadress from adress where city =? and district =?";
            String getIdPerson = "Select idperson from person where name =? and surname = ?";
            String command = "INSERT INTO `person`(`name`, `surname`, `sex`, `adressId`) VALUES (?,?,?,?)";
            String insertPersonInNeed = "INSERT INTO `clothtaker`(`idClothTaker`,`lowerSize`, `upperSize`, `clothTakenCount`) VALUES (?,?,?,?)";
            String addressId = null;
            String personId = null;
            
         try {
            preparedStatementGetId = con.prepareStatement(getId);
            preparedStatementGetId.setString(1,city);
            preparedStatementGetId.setString(2,district);
            ResultSet rs = preparedStatementGetId.executeQuery();
           if(rs.next()){
              addressId= rs.getString(1);
           }
            System.out.println("adress Id " +addressId);
           if(addressId == null){
                String insertCitywithDistrict = "INSERT INTO `adress`(`city`, `district`) VALUES (?,?)";
                preparedStatement = con.prepareCall(insertCitywithDistrict);
                preparedStatement.setString(1, city);
                preparedStatement.setString(2, district);
                preparedStatement.executeUpdate(); 
           }
           
           String CityIdCommand = "Select idadress from adress where city =? and district=?";
           
            preparedStatementGetId = con.prepareStatement(CityIdCommand);
            preparedStatementGetId.setString(1,city);
            preparedStatementGetId.setString(2, district);
            ResultSet rs3 = preparedStatementGetId.executeQuery();
            while(rs3.next()){
               addressId= rs3.getString("idadress");
            }
           
           
           
             preparedStatement = con.prepareStatement(command);
                preparedStatement.setString(1,name);
                    preparedStatement.setString(2, surname);
                        preparedStatement.setString(3, sex);
                            preparedStatement.setInt(4, Integer.parseInt(addressId));
                                preparedStatement.executeUpdate();
             
             
            preparedStatementGetId = con.prepareStatement(getIdPerson);
            preparedStatementGetId.setString(1,name);
            preparedStatementGetId.setString(2,surname);
            ResultSet rs2 = preparedStatementGetId.executeQuery();
           if(rs2.next()){
              personId= rs2.getString(1);
           }              
                      
            
             preparedStatement = con.prepareStatement(insertPersonInNeed);
                preparedStatement.setInt(1,Integer.parseInt(personId));
                     preparedStatement.setString(2, lowerSize);
                         preparedStatement.setString(3, upperSize);
                         preparedStatement.setInt(4, 0);
             
                             preparedStatement.executeUpdate();
             
                
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           //sho preparedStatementgetAdressId = con
        }

public void insertUser(String username,String pass,String name,String surname,String sex,String city,String district){
            
            String getId = "Select idadress from adress where city =? and district =?";
            String getIdPerson = "Select idperson from person where name =? and surname = ?";
            String command = "INSERT INTO `person`(`name`, `surname`, `sex`, `adressId`) VALUES (?,?,?,?)";
            String insertUser = "INSERT INTO `user`(`personId`,`username`, `password`) VALUES (?,?,?)";
            String addressId = null;
            String personId = null;
            
        try {
            preparedStatementGetId = con.prepareStatement(getId);
            preparedStatementGetId.setString(1,city);
            preparedStatementGetId.setString(2,district);
            ResultSet rs = preparedStatementGetId.executeQuery();
           if(rs.next()){
              addressId= rs.getString(1);
           }
            System.out.println("adress Id " +addressId);
           if(addressId == null){
                String insertCitywithDistrict = "INSERT INTO `adress`(`city`, `district`) VALUES (?,?)";
                preparedStatement = con.prepareCall(insertCitywithDistrict);
                preparedStatement.setString(1, city);
                preparedStatement.setString(2, district);
                preparedStatement.executeUpdate(); 
           }
           
           String CityIdCommand = "Select idadress from adress where city =? and district=?";
           
            preparedStatementGetId = con.prepareStatement(CityIdCommand);
            preparedStatementGetId.setString(1,city);
            preparedStatementGetId.setString(2, district);
            ResultSet rs3 = preparedStatementGetId.executeQuery();
            while(rs3.next()){
               addressId= rs3.getString("idadress");
            }
           
           
           
             preparedStatement = con.prepareStatement(command);
                preparedStatement.setString(1,name);
                    preparedStatement.setString(2, surname);
                        preparedStatement.setString(3, sex);
                            preparedStatement.setInt(4, Integer.parseInt(addressId));
                                preparedStatement.executeUpdate();
             
             
            preparedStatementGetId = con.prepareStatement(getIdPerson);
            preparedStatementGetId.setString(1,name);
            preparedStatementGetId.setString(2,surname);
            ResultSet rs2 = preparedStatementGetId.executeQuery();
           if(rs2.next()){
              personId= rs2.getString(1);
           }              
                      
             preparedStatement = con.prepareStatement(insertUser);
                preparedStatement.setInt(1,Integer.parseInt(personId));
                     preparedStatement.setString(2, username);
                         preparedStatement.setString(3, pass);
             
                             preparedStatement.executeUpdate();
             
                
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           //sho preparedStatementgetAdressId = con
        }

public ArrayList<ComboboxItem> getHangar(){
  
    
     ArrayList<ComboboxItem> combobox = new ArrayList<ComboboxItem>();
    //Collects all the users
    try {
	String command = "Select * from hangar";
            preparedStatement = con.prepareStatement(command);
                    ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                  Integer hangarId= rs.getInt("idhangar");
            String hangarName = rs.getString("hangarName");
            combobox.add(new ComboboxItem(  hangarId.toString(),hangarName ));
        
                }
         
	
       
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,ex.getMessage());
    }
    return combobox;
}
        
        
    
       public boolean loginSystemUSER(String username, String password){
           String command = "SELECT * FROM `user` WHERE `username` = ? AND `password` = ?";

         //  String command = "Select * from workers where username = ? and pass = ?";
        try {
            preparedStatement = con.prepareStatement(command);
             preparedStatement.setString(1,username);
                 preparedStatement.setString(2,password);
                     ResultSet rs = preparedStatement.executeQuery();
            
                if(rs.next() == false){
                    return false;
                }else{  
                    LoginPage.userName = username;
                    
                     String command1 = "Select * from user where username =?";
                        String userid =null;
                         preparedStatement = con.prepareStatement(command1);                     
                             preparedStatement.setString(1,LoginPage.userName);
                                ResultSet rs1 = preparedStatement.executeQuery();
                                while(rs1.next()){
                                    userid = rs1.getString("iduser");
                                }
                                
                    String exist = "Select * from clothgiver where idclothgiver ="+userid;
                        preparedStatement=con.prepareStatement(exist);
                           
                        ResultSet rsfinal = preparedStatement.executeQuery();
                            if(rsfinal.next()==false){
                                String insert_clothgiver = "INSERT INTO `clothgiver`(`idClothGiver`,`clothGivenCount`) VALUES (?,?)";
                    preparedStatement = con.prepareStatement(insert_clothgiver);
                        preparedStatement.setInt(1, Integer.valueOf(userid));
                            preparedStatement.setInt(2, 0);
                                preparedStatement.executeUpdate();
                            }
                   return true;
                }
           
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
           return false;
       }
        public boolean loginSystemADMIN(String username, String password){
           String command = "SELECT * FROM `hangar` WHERE `hangarName` = ? AND `hangarPassword` = ?";
        try {
            preparedStatement = con.prepareStatement(command);
             preparedStatement.setString(1,username);
                 preparedStatement.setString(2,password);
                     ResultSet rs = preparedStatement.executeQuery();
                if(rs.next() == false){
                    return false;
                }else{
                    LoginPage.hangarName = username;
                    return true;
                }
           
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
           return false;
       }
        
    public void insertTakes(int clothId, int personId,int transporterId,int hangarId,String date){
            
            String insertTakes = "INSERT INTO `takes`(`transporterId`,`clothId`, `takenDate`,`clothTakerId`,`hangarId`) VALUES (?,?,?,?,?)";
            
            
        try {
             preparedStatement = con.prepareStatement(insertTakes);
                preparedStatement.setInt(1,transporterId);
                     preparedStatement.setInt(2, clothId);
                         preparedStatement.setString(3, date);
                         preparedStatement.setInt(4, personId);
                         preparedStatement.setInt(5, hangarId);
             
                             preparedStatement.executeUpdate();
             
                
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           //sho preparedStatementgetAdressId = con
        }

    public void insertGiveAway(int clothId,String date){
            
        String command ="Update giveaway set arrivalDate=? where clothId =?";
        try {
             preparedStatement = con.prepareStatement(command);
                preparedStatement.setString(1,date);
                     preparedStatement.setInt(2, clothId);
                             preparedStatement.executeUpdate();
             
                
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           //sho preparedStatementgetAdressId = con
        }
    
    
    
    
    
    
     public void deleteTransporter(int transporterId){
         
            
        String command ="ALTER TABLE transporter DROP FOREIGN KEY transporter_FK1, DROP FOREIGN KEY transporter_FK2";
        String command2 ="DELETE FROM transporter WHERE idtransporter=?";
        String command3 ="ALTER TABLE transporter ADD CONSTRAINT transporter_FK1 FOREIGN KEY (personId) REFERENCES person (idperson) ON DELETE CASCADE, ADD CONSTRAINT transporter_FK2 FOREIGN KEY (hangarId) REFERENCES hangar (idhangar) ON DELETE CASCADE";
        try {
             preparedStatement = con.prepareStatement(command);
              //  preparedStatement.setString(1,date);
                 //    preparedStatement.setInt(2, clothId);
                             preparedStatement.executeUpdate();
                             
                             
                preparedStatement = con.prepareStatement(command2);
               preparedStatement.setInt(1,transporterId);
                 //    preparedStatement.setInt(2, clothId);
                             preparedStatement.executeUpdate();
                             
                             
                              preparedStatement = con.prepareStatement(command3);
              
                 //    preparedStatement.setInt(2, clothId);
                             preparedStatement.executeUpdate();
             
                
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           //sho preparedStatementgetAdressId = con
        }
    
    
    
    
        
    public void updateTakes(int clothId,String date){
            
        String command ="Update takes set arrivalDate=? where clothId =?";
        try {
             preparedStatement = con.prepareStatement(command);
                preparedStatement.setString(1,date);
                     preparedStatement.setInt(2, clothId);
                             preparedStatement.executeUpdate();
             
                
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           //sho preparedStatementgetAdressId = con
        }
        
        
        
        
         public ArrayList<Cloth_Entrance> updateClothEntrance(String name){
                    String command = "select * from cloth_didnt_arrive_hangar where hangarName =?";
                    ArrayList<Cloth_Entrance> arraylist = new ArrayList<Cloth_Entrance>();
                    String temp=null;             
        try {
            preparedStatement = con.prepareStatement(command);
               preparedStatement.setString(1,name);            
                ResultSet rs = preparedStatement.executeQuery();
                    
                int ClothId=-1;
                String ClothType=null;
                String ClothSize=null;
               
                int TransporterId = -1;
                
                while(rs.next()){
                    ClothId=rs.getInt("idcloth");
                    ClothType=rs.getString("clothType");
                    ClothSize=rs.getString("clothSize");
                  
                    arraylist.add(new Cloth_Entrance(ClothId,ClothType,ClothSize));
                }
                
                    
                
                      
                  return  arraylist;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
            return null;
        }
        
        
        
       
        
        
        
        
        
        
        
        
        
        
        
        
        
       
    
      public DatabaseProcess(){
        String url = "jdbc:mysql://" + Database.host +":"+Database.port+"/"+Database.db_name;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        }catch (ClassNotFoundException ex) {
               System.out.println("Driver can't find");
        }
        
            try {
                con = DriverManager.getConnection(url,Database.userName,Database.password);
                System.out.println("Connected to the Database");
            } catch (SQLException ex) {
                System.out.println("Connection Faild");
            }
    }
    public static void main(String[] args) {
        DatabaseProcess dp = new DatabaseProcess();
        
    }
    
}
