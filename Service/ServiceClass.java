/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.ModelAccount;
import Model.ModelClass;
import View.AddMemberScene;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;


public class ServiceClass {
		static ArrayList<ModelClass> memberList;
		static ArrayList<ModelAccount> mealRateList;
		static ArrayList<Double> totalMealRateList;
		static ArrayList<ModelAccount> rentList;
		static ArrayList<ModelAccount> memberRentList;
		static ArrayList<ModelAccount> mealList;
		static ArrayList<ModelAccount> bazarList;
		static ArrayList<ModelAccount> mealChargeList;
		static ArrayList<ModelAccount> bazarChargeList;
		static ArrayList<ModelAccount> mealRateIdList;
		static ArrayList<ModelAccount> totalMemberMealList;
		static ArrayList<ModelAccount> mealDetailsList;
		
		static double totalDeposit = 0.0;
		
                static ArrayList<String> idList;
		AddMemberScene addmemberscene = new AddMemberScene();
		ModelClass model = new ModelClass ();
		public String addMember(ModelClass model, int pass){
				String sql=" ";
				sql+="INSERT INTO ";
				sql+="Member(ID,FirstName,LastName,MOBILENO,EMAIL,PASSWORD,DATEOFBIRTH,JoinDate,BLOODGROUP,MEMBERType) ";
				sql+="values('"+model.getId()+"','"+model.getFirstName()+"','"+model.getLastName()+"','"+model.getMobile()+"','"+model.getEmail()+"',"
					 + "'"+Integer.toString(pass)+"','"+model.getDateOfBirth()+"','"+model.getJoinDate()+"','"+model.getBloodGroup()+"',"
					 + "'"+model.getMemberType()+"')";
				//memberList.add (model);
				return sql;
		}
	

						
							
public boolean edit(ServiceClass memberList){
		 
		 try{
					String sql = "update Member set MOBILENO=model.getMobile(),EMAIL=model.getEmail(), PASSWORD = Integer.toString(getRandomPassword()) ";
			   
					// step1 load the driver class  
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					// step2 create  the connection object  s
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
					// step3 create the statement object  
					Statement stmt=con.createStatement();
								int r = stmt.executeUpdate(sql);
								System.out.println(r);
			}
		catch(Exception ex){
			ex.printStackTrace();
		}
		 
				return true;
    }								
	
	
	
	public String getById(String Id){
		String searchById = " ";
                //String serchById=" ";
                searchById+="Select ID,PASSWORD,MEMBERTYPE,Email,MobileNo from MEMBER Where ID = '"+Id+"'";
		return searchById;
		
		
		
	}
	
	
public List<ModelClass> getAll() {
    memberList = new ArrayList<ModelClass>();
   
                try{
			
                String sql = "";
                sql = "select FIRSTNAME,ID,DATEOFBIRTH,BLOODGROUP,MOBILENO,EMAIL,JOINDATE from MEMBER";
			   
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                        
                ResultSet rs = stmt.executeQuery(sql);
        //int g =0;

                while (rs.next()) {
					ModelClass member = new ModelClass(rs.getString("JOINDATE"), rs.getString("ID"));
                    System.out.println(rs.getString("FIRSTNAME"));
					member.setFirstName(rs.getString("FIRSTNAME"));
					System.out.println(rs.getString("ID"));
					member.setTempId(rs.getString("ID"));
					System.out.println(rs.getString("DATEOFBIRTH"));
					member.setDateOfBirth(rs.getString("DATEOFBIRTH"));
					System.out.println(rs.getString("BLOODGROUP"));
					member.setBloodGroup(rs.getString("BLOODGROUP"));
					System.out.println(rs.getString("MOBILENO"));
					member.setMobile(rs.getString("MOBILENO"));
					System.out.println(rs.getString("EMAIL"));
					member.setEmail(rs.getString("EMAIL"));
					System.out.println(rs.getString("JOINDATE"));
					member.setTempJoinDate(rs.getString("JOINDATE"));
            memberList.add(member);
			member = null;
        }

    } catch (Exception e) {
        System.out.println(e);
    }

    return memberList;
}

public List<ModelAccount> getMealRate() throws SQLException {
    mealRateList = new ArrayList<ModelAccount>();
    mealRateIdList = new ArrayList<ModelAccount>();
  
                try{
			
                String sql = "";
                sql = "select FIRSTNAME,ACCOUNTID,BREAKFAST,LAUNCH,DINNER,MEALCHARGE,BAZARCHARGE,Account.ID,DDATE,BAZAREXPENSE from Account,Member,Balance where Member.Id = Account.Id AND Member.Id = Balance.Id";
			   
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                        
                ResultSet rs = stmt.executeQuery(sql);
        //int g =0;

                while (rs.next()) {
                     ModelClass model = new ModelClass();
                    ModelAccount mealRate = new ModelAccount();
                   // System.out.println(rs.getString(8));
                    mealRate.setTempId(rs.getString(8));

            mealRateList.add(mealRate);
			mealRate = null;
        }

    } catch (Exception e) {
        System.out.println(e);
    }
                for(ModelAccount ob : mealRateList){
                ModelAccount mealRateId = new ModelAccount();
                //System.out.println(ob.getTempId());
                String mealRateSql = "select sum(breakFast)+sum(launch)+sum(dinner), sum(MEALCHARGE), sum(BazarExpense),sum(BazarExpense)/ (sum(breakFast)+sum(launch)+sum(dinner)) from Account,Member,Balance where Member.Id = Account.Id AND Member.Id = Balance.Id AND Member.Id='"+ob.getTempId()+"";
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery(mealRateSql);
                mealRateId.setTotalMeal(rs.getInt(1));
                //System.out.println(rs.getInt(1));
               // System.out.println(rs.getString(8));
                mealRateIdList.add(mealRateId);
                }

    return mealRateIdList;
}

public List<String> getAllId() {
    idList = new ArrayList<String>();
   
                try{
			
                String sql = "";
                sql = "select ID, JOINDATE from MEMBER";
			   
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                        
                ResultSet rs = stmt.executeQuery(sql);
        //int g =0;

                while (rs.next()) {
                        ModelClass member = new ModelClass(rs.getString("JOINDATE"), rs.getString("ID"));
                        System.out.println(rs.getString("ID"));
                        member.setTempId(rs.getString("ID"));
					
                        idList.add(member.getTempId());
			member = null;
        }

    } catch (Exception e) {
        System.out.println(e);
    }

    return idList;
}



public List<ModelAccount> getRentInfo() {
    rentList = new ArrayList<ModelAccount>();
   
                try{
			
                String sql = "";
                sql = "select FirstName,HouseRent,GasBill,INTERNETBILL,MAIDBILL, ElectricityBill, ADDITIONAL,RDATE from RentAccount,Member where Member.Id = RentAccount.Id";
			   
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();        
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
			ModelClass model = new ModelClass ();
					    ModelAccount rent = new ModelAccount();
						rent.setFirstName (rs.getString ("FirstName"));
						rent.setHouseRent (rs.getDouble("HouseRent"));
						rent.setGasBill (rs.getDouble("GasBill"));
						rent.setInternetBill (rs.getDouble("INTERNETBILL"));
						rent.setMaidBill (rs.getDouble("MAIDBILL"));
                        rent.setElectricityBill (rs.getDouble("ElectricityBill"));
						rent.setAdditional (rs.getDouble("ADDITIONAL"));
                        rent.setDDate(rs.getDate("RDATE"));
						rent.setTotal (rs.getDouble ("HouseRent")+rs.getDouble ("GasBill")+rs.getDouble ("INTERNETBILL")+rs.getDouble ("MAIDBILL") +rs.getDouble("ElectricityBill")+ rs.getDouble ("ADDITIONAL"));
                       rentList.add(rent); 
			rent = null;
        }
    } catch (Exception e) {
        System.out.println(e);
    }
    return rentList;
}


public List<ModelAccount> getMealInfo(String id) {
    mealList = new ArrayList<ModelAccount>();
   
                try{
			
                String sql = "";
                sql = "select BreakFast,Launch,Dinner,DDATE from Account where Id = '"+id+"'";
			   
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                        
                ResultSet rs = stmt.executeQuery(sql);
        //int g =0;

                while (rs.next()) {
                        model = new ModelClass ();
                        //System.out.println (rs.getDate (5)+ " "+ rs.getInt (2)+ " " +rs.getInt (3));
					
                        ModelAccount meal = new ModelAccount();
                        meal.setBreakFast (rs.getInt(1));
                        meal.setLaunch (rs.getInt(2));
                        meal.setDinner (rs.getInt(3));
                        meal.setDDate (rs.getDate(4));
                        // meal.setBazarCharge (rs.getDouble(4));
                         
                       // meal.setBazarExpense (rs.getDouble(6));
                       // meal.setTotal (rs.getDouble (4) - rs.getDouble (6));
                        System.out.println (meal.getDDate()+ " "+meal.getBreakFast());
                        mealList.add(meal);
					   
					  
			meal = null;
        }

    } catch (Exception e) {
        System.out.println(e);
    }

    return mealList;
}



public List<ModelAccount> getTotalMealInfo(String id) {
    totalMemberMealList = new ArrayList<ModelAccount>();
   
                try{
			
                String sql = "";
                sql = "select sum(BreakFast)+sum(Launch)+sum(Dinner) from Account where Id = '"+id+"'";
			   
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                        
                ResultSet rs = stmt.executeQuery(sql);
        //int g =0;

                
                String sql1 = "";
                sql1 = "select sum(MEALCHARGE) from Balance where Id = '"+id+"'"; 
			   
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                stmt=con.createStatement();
                        
                ResultSet rs1 = stmt.executeQuery(sql1);
        //int g =0;

                rs.next();
                        ModelClass model = new ModelClass ();
                        //System.out.println (rs.getDate (5)+ " "+ rs.getInt (2)+ " " +rs.getInt (3));
					
                        ModelAccount meal = new ModelAccount();
                        meal.setTotalMeal (rs.getInt(1));
                        rs1.next();
                        meal.setMealCharge (rs1.getDouble(1));
						meal.setMealExpense(meal.getTotalMeal ()*(getTotalMealRate ()));
                                                meal.setMealRate(getTotalMealRate ());
                                                System.out.println(meal.getMealExpense());
                                                System.out.println(meal.getMealCharge());
						if (meal.getMealCharge()>meal.getMealExpense()){
							meal.setGet(meal.getMealCharge()-meal.getMealExpense());
							System.out.println (meal.getGet());
							System.out.println (meal.getPay());
							meal.setPay (0.0);
						}
						else if (meal.getMealCharge()<meal.getMealExpense()){
							meal.setPay (meal.getMealExpense()-meal.getMealCharge());
							System.out.println (meal.getGet());
							System.out.println (meal.getPay());
							meal.setGet (0.0);
						}
						else {
							meal.setPay (0.0);
							meal.setGet (0.0);
							System.out.println (meal.getGet());
							System.out.println (meal.getPay());
						}
						
						
						
						//getTotalMealRate ();
                        //meal.setDinner (rs.getInt(3));
                        // meal.setBazarCharge (rs.getDouble(4));
                        // meal.setdDate (rs.getDate(5));
                       // meal.setBazarExpense (rs.getDouble(6));
                       // meal.setTotal (rs.getDouble (4) - rs.getDouble (6));
                        //System.out.println (meal.getdDate()+ " "+meal.getBreakFast());
                        totalMemberMealList.add(meal);
						
					   
					  
			meal = null;
        }

     catch (Exception e) {
        System.out.println(e);
    }

    return totalMemberMealList;
}



public Double getTotalMealRate() {
    //totalMealRateList = new ArrayList<Double>();
				
				ModelAccount modelAccount = new ModelAccount ();
                try{
			
                String sql = "";
                sql= "select sum(breakFast)+sum(launch)+sum(dinner), sum(MEALCHARGE), sum(BazarExpense),sum(BazarExpense)/ (sum(breakFast)+sum(launch)+sum(dinner)) from Account,Member,Balance where Member.Id = Account.Id AND Member.Id = Balance.Id AND TRUNC(ddate) BETWEEN SYSDATE - 20 AND SYSDATE";
			   
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
				
				System.out.println (rs.getDouble(4));
				//modelAccount.setMealRate (rs.getDouble(4));
				//totalMealRateList.add (rs.getDouble(4));
                                modelAccount.setMealRate(rs.getDouble(4));
				//modelAccount = null;
				
				
                } 
        catch (Exception e) {
            System.out.println(e);
    }

    return modelAccount.getMealRate();
}




public List<ModelAccount> getBazarHistoryInfo(String id) {
    bazarList = new ArrayList<ModelAccount>();
    
   
                try{
			
                String sql = "";
                sql = "select sum(BAZARCHARGE),sum(BAZAREXPENSE) from Balance where Id = '"+id+"'";
			   
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                        
                ResultSet rs = stmt.executeQuery(sql);
        //int g =0;

						rs.next();
                        ModelClass model = new ModelClass ();
                        //System.out.println ("sadfasdf");
			ModelAccount bazar = new ModelAccount();
			System.out.println(bazar.getTotal());			
                        bazar.setBazarCharge (rs.getDouble(1));
                        bazar.setBazarExpense (rs.getDouble(2));
                        bazar.setTotal(rs.getDouble(1)-rs.getDouble(2));
                        System.out.println (rs.getDouble(2));
                        bazarList.add(bazar);
					   
					  
			bazar = null;
        }

     catch (Exception e) {
        System.out.println(e);
    }

    return bazarList;
}

public List<ModelAccount> getMemberRentInfo(String id) {
    memberRentList = new ArrayList<ModelAccount>();
   
                try{
			
                String sql = "";
                sql = "select FirstName,HouseRent,GasBill,INTERNETBILL,MAIDBILL, ElectricityBill, ADDITIONAL,RDATE from RentAccount,Member where Member.Id = RentAccount.Id AND Member.Id='"+id+"'";
			   
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                        
                ResultSet rs = stmt.executeQuery(sql);
        //int g =0;

                while (rs.next()) {
			ModelClass model = new ModelClass ();
					
                        ModelAccount memberRent = new ModelAccount();
						memberRent.setFirstName (rs.getString ("FirstName"));
						memberRent.setHouseRent (rs.getDouble("HouseRent"));
						memberRent.setGasBill (rs.getDouble("GasBill"));
						memberRent.setInternetBill (rs.getDouble("INTERNETBILL"));
						memberRent.setMaidBill (rs.getDouble("MAIDBILL"));
                                                memberRent.setAdditional (rs.getDouble("ElectricityBill"));
						memberRent.setAdditional (rs.getDouble("ADDITIONAL"));
                                                memberRent.setDDate(rs.getDate("RDATE"));
						memberRent.setTotal (rs.getDouble ("HouseRent")+rs.getDouble ("GasBill")+rs.getDouble ("INTERNETBILL")+rs.getDouble ("MAIDBILL") +rs.getDouble("ElectricityBill")+ rs.getDouble ("ADDITIONAL"));
                      //memberRent.setTempId(rs.getString("ID"));
					  
					
                       memberRentList.add(memberRent);
					   
					  
			memberRent = null;
        }

    } catch (Exception e) {
        System.out.println(e);
    }

    return memberRentList;
}
	
	
	
	public List<ModelAccount> getMealChargeHistory() {
    mealChargeList = new ArrayList<ModelAccount>();
   
                try{
			
                String sql = "";
                sql = "select FirstName,MEALCHARGE from Member,Balance where Member.Id = Balance.Id AND MEALCHARGE is not NULL";
			   
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                        
                ResultSet rs = stmt.executeQuery(sql);
        //int g =0;

                while (rs.next()) {
			ModelClass model = new ModelClass ();
					
                        ModelAccount mealCharge = new ModelAccount();
						mealCharge.setFirstName (rs.getString ("FirstName"));
						mealCharge.setMealCharge (rs.getDouble("MEALCHARGE"));
						mealChargeList.add(mealCharge);
						mealCharge = null;
        }

    } catch (Exception e) {
        System.out.println(e);
    }

    return mealChargeList;
}




	public List<ModelAccount> getBazarChargeHistory() {
    bazarChargeList = new ArrayList<ModelAccount>();
   
                try{
			
                String sql = "";
                sql = "select FirstName,BAZARCHARGE,BAZAREXPENSE from Member,Balance where Member.Id = Balance.Id AND BAZARCHARGE is not NULL OR BAZAREXPENSE is not NULL";
			   
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                        
                ResultSet rs = stmt.executeQuery(sql);
        //int g =0;

                while (rs.next()) {
			ModelClass model = new ModelClass ();
					
                        ModelAccount bazarCharge = new ModelAccount();
						bazarCharge.setFirstName (rs.getString ("FirstName"));
						bazarCharge.setBazarCharge (rs.getDouble("BAZARCHARGE"));
						bazarCharge.setBazarExpense (rs.getDouble("BAZAREXPENSE"));
						bazarChargeList.add(bazarCharge);
						bazarCharge = null;
        }

    } catch (Exception e) {
        System.out.println(e);
    }

    return bazarChargeList;
}
	
	
	
public boolean changePassword(String Id, String passWord){
			
		
			
             try{
                String sql = "";
                 sql = "update Member set PASSWORD = '"+passWord+"' where ID = '"+Id+"' ";
			   
                // step1 load the driver class  
                Class.forName("oracle.jdbc.driver.OracleDriver");  
                // step2 create  the connection object  s
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                // step3 create the statement object  
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                     //System.out.println(r);
            }
            catch(Exception ex){
                    ex.printStackTrace();
                        }
            
            return true;
        }
		
		
		
		public boolean update(String Id, String email, String mobile){
			
             try{
                String sql = "";
                 sql = "update Member set EMAIL = '"+email+"', MOBILENO = '"+mobile+"' where ID = '"+Id+"' ";
			  // System.out.println (email);
                // step1 load the driver class  
                Class.forName("oracle.jdbc.driver.OracleDriver");  
                // step2 create  the connection object  s
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                // step3 create the statement object  
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
				
                     
            }
            catch(Exception ex){
                    ex.printStackTrace();
                        }
            
            return true;
        }
		
		
		
		
		 public boolean delete(String Id){
			
			
			
             try{
                String sql = "";
                 sql = "delete from Member  where ID = '"+Id+"' ";
			   
                // step1 load the driver class  
                Class.forName("oracle.jdbc.driver.OracleDriver");  
                // step2 create  the connection object  s
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                // step3 create the statement object  
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                     //System.out.println(r);
            }
            catch(Exception ex){
                    ex.printStackTrace();
                        }
            
            return true;
        }
	
	
	public List<ModelAccount> getMealDetails() {
    mealDetailsList = new ArrayList<ModelAccount>();
    DatePicker addMealTime = new DatePicker(LocalDate.now());
                try{
			
                String sql = "";
                sql = "select FIRSTNAME,BREAKFAST,LAUNCH,DINNER from MEMBER,Account where MEMBER.ID = ACCOUNT.ID and ddate = TO_DATE('"+addMealTime.getValue()+"', 'YYYY-MM-DD')" ;
			   
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                        
                ResultSet rs = stmt.executeQuery(sql);
        //int g =0;

                while (rs.next()) {
					ModelAccount mealDetails = new ModelAccount();
                    System.out.println(rs.getString(1));
					mealDetails.setFirstName(rs.getString(1));
					System.out.println(rs.getInt(2));
					mealDetails.setBreakFast(rs.getInt(2));
					System.out.println(rs.getInt(3));
					mealDetails.setLaunch(rs.getInt(3));
					System.out.println(rs.getInt(4));
					mealDetails.setDinner(rs.getInt(4));
					
            mealDetailsList.add(mealDetails);
			mealDetails = null;
        }

    } catch (Exception e) {
        System.out.println(e);
    }

    return mealDetailsList;
}
		
		
		
		
}