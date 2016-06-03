/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class ModelClass {
private static String id;
private String firstName;
private String lastName;
private String mobile;
private String email;
private String dateOfBirth;
private String bloodGroup;
private static String joinDate;
private static String memberType;
private static int count = 0;
private String tempId;
private String tempJoinDate;


private static ModelClass uniqueInstance;
         // other useful instance variables

        public ModelClass  ( ) {  }
		public ModelClass  (String tempJoinDate, String tempId ) {
			this.tempJoinDate = tempJoinDate;
			this.tempId = tempId;
		}
        public static ModelClass getInstance  ( ) {
                if (uniqueInstance == null) {
                       
                       uniqueInstance = new ModelClass ( );
                       ModelClass.memberType = "Admin" ;
                       
                        String[] splitJoinDate = joinDate.split("/");
                        char yearSplit1 = splitJoinDate[2].charAt(2);
                        char yearSplit2 = splitJoinDate[2].charAt(3);
                        int b = Integer.parseInt(splitJoinDate[0]);//String to int
                        if(b<9){
                            ModelClass.id = yearSplit1 + "" + yearSplit2 + "0"+splitJoinDate[0]+"-" + "00" + "-" + 1;
                        }
                        else 
                           ModelClass.id = yearSplit1 + "" + yearSplit2 + splitJoinDate[0]+"-" + "00" + "-" + 1;
                             return uniqueInstance;  
                        }
                else{
                        System.out.println("Contact with Admin");
                        return null;
                    }
              
                //return uniqueInstance;
          }

    public ModelClass(int count, String firstName, String lastName, String mobile, String email, String dateOfBirth, String joinDate, String bloodGroup) {
        
        ModelClass.memberType = "Member" ; 
        this.count = count;
        String[] splitJoinDate = joinDate.split("/");
        char yearSplit1 = splitJoinDate[2].charAt(2);
        char yearSplit2 = splitJoinDate[2].charAt(3);
        int b = Integer.parseInt(splitJoinDate[0]);//String to int
        if(b<9 || count<9 ){
            ModelClass.id = yearSplit1 + "" + yearSplit2 + "0"+splitJoinDate[0]+"-" +"0"+ ++count + "-" + 2;
        }
        else 
           ModelClass.id = yearSplit1 + "" + yearSplit2 + splitJoinDate[0]+"-" + ++count + "-" + 2;
                        
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
		this.joinDate = joinDate;
        this.bloodGroup = bloodGroup;
    }
        
        


//Setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public static void setId(String id) {
        ModelClass.id = id;
    }
    
	public void setTempId(String tempId){
		this.tempId = tempId;
	}
	
	public void setTempJoinDate(String tempJoinDate){
		this.tempJoinDate = tempJoinDate;
	}

    
    //getters
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public String getMemberType() {
        return memberType;
    }

	public String getTempId() {
        return tempId;
    }
	
	public String getTempJoinDate() {
        return tempJoinDate;
	}


}
