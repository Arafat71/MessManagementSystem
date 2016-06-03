/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.util.Date;

public class ModelAccount extends ModelClass{
    double houseRent;
    double gasBill;
    double electricityBill;
    double internetBill;
    double maidBill;
    double additional ;
    double mealCharge;
    double bazarCharge;
    double bazarExpense;
    double total;
    double mealExpense;
    Date DDate;
    Date RDate;
    int breakFast;
    int launch;
    int dinner;
    int totalMeal;
    double cost;
    double pay;
    double get;
	double mealRate;

    public ModelAccount(double mealCharge, double bazarCharge, Date DDate, int breakFast, int launch, int dinner, int totalMeal) {
        this.mealCharge = mealCharge;
        this.bazarCharge = bazarCharge;
        this.DDate = DDate;
        this.breakFast = breakFast;
        this.launch = launch;
        this.dinner = dinner;
        this.totalMeal = totalMeal;
    }
	
	public ModelAccount(double houseRent, double gasBill, double electricityBill, double internetBill, double additional, Date RDate) {
        this.houseRent = houseRent;
        this.gasBill = gasBill;
        this.electricityBill = electricityBill;
        this.internetBill = internetBill;
        this.additional  = additional ;
        this.RDate = RDate;
    }
	
	public ModelAccount (double houseRent){
		this.houseRent =houseRent;
	}

    public ModelAccount() {
    }

    public void setMealRate(double mealRate) {
        this.mealRate = mealRate;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public void setGet(double get) {
        this.get = get;
    }
    
    public void setTotalMeal(int totalMeal) {
        this.totalMeal = totalMeal;
    }
    
    public void setRDate(Date RDate) {
        this.RDate = RDate;
    }
    
    public void setMealExpense(double mealExpense) {
        this.mealExpense = mealExpense;
    }

    public void setHouseRent(double houseRent) {
        this.houseRent = houseRent;
    }

    public void setGasBill(double gasBill) {
        this.gasBill = gasBill;
    }

    public void setElectricityBill(double electricityBill) {
        this.electricityBill = electricityBill;
    }

    public void setInternetBill(double internetBill) {
        this.internetBill = internetBill;
    }

    public void setMaidBill(double maidBill) {
        this.maidBill = maidBill;
    }

    public void setAdditional(double additional) {
        this.additional = additional;
    }

    public void setMealCharge(double mealCharge) {
        this.mealCharge = mealCharge;
    }

    public void setBazarCharge(double bazarCharge) {
        this.bazarCharge = bazarCharge;
    }
	
	 public void setBazarExpense(double bazarExpense) {
        this.bazarExpense = bazarExpense;
    }

    public void setDDate(Date DDate) {
        this.DDate = DDate;
    }

    public void setBreakFast(int breakFast) {
        this.breakFast = breakFast;
    }

    public void setLaunch(int launch) {
        this.launch = launch;
    }

    public void setDinner(int dinner) {
        this.dinner = dinner;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    

    public double getHouseRent() {
        return houseRent;
    }

    public double getGasBill() {
        return gasBill;
    }

    public double getElectricityBill() {
        return electricityBill;
    }

    public double getInternetBill() {
        return internetBill;
    }

    public double getMaidBill() {
        return maidBill;
    }

    public double getAdditional() {
        return additional ;
    }

    public double getMealCharge() {
        return mealCharge;
    }

    public double getBazarCharge() {
        return bazarCharge;
    }
	
	public double getBazarExpense() {
        return bazarExpense;
    }

    public Date getDDate() {
        return DDate;
    }

    public int getBreakFast() {
        return breakFast;
    }

    public int getLaunch() {
        return launch;
    }

    public int getDinner() {
        return dinner;
    }

    public double getTotal() {
        return total;
    }

    public double getMealExpense() {
        return mealExpense;
    }

    public Date getRDate() {
        return RDate;
    }

    public int getTotalMeal() {
        return totalMeal;
    }

    public double getCost() {
        return cost;
    }

    public double getPay() {
        return pay;
    }

    public double getGet() {
        return get;
    }

    public double getMealRate() {
        return mealRate;
    }
    
    
    
    
}
