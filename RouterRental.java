/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routerrental_package;

import java.time.LocalDate; 
import java.util.ArrayList;


/**
 *
 * @author ibrahim
 */

/**A Class RouterRental for Main */
public class RouterRental {
    
    /**@param args  A Class  Main */
    public static void main(String[] args) { //13-the SOLID object-oriented design principles in all project
      
      Customer [ ] c = new Customer [5];
      c[0]= new Residents("Ahmed","Cash");
      c[1]= new Residents("Ali","Cash");
      c[2]= new Foreigners("Britsh","john","Visa");
      c[3]= new Foreigners("Italian","Toty","Master Card");
      
      Router r = new Router ();
      Router [ ] R = new Router [10];
      R[0]=new Router("ABW2Z",4);
      R[1]=new Router("CEQ9C",3);
      R[2]=new Router("IBM2M",8);
      R[3]=new Router("H2HK",5);
      R[4]=new Router("LO8H",2);
     
      LocalDate startdate = LocalDate.now().plusDays(5);
      LocalDate startdate1 = LocalDate.now().plusDays(1);  

      r.hello();
      System.out.println("There are "+r.numofRouter+" Router");
     
      System.out.println("List of Router , Note : I recommend you IBM2M , it's the best.");
      System.out.println("--------------------------------------------------------------");
      
      for(int i =0 ; i<r.numofRouter ; i++)
      {
      R[i].PrintRouter();
      }
      //1-Rent(one customer with multiple invoices.)
     c[0].Rent(R[0], startdate, "daily", 2);
  
     c[0].Invoices.rentingFees("daily", R[0].getModelnum(), 2);
     c[0].Invoices.Printinvoice(((Residents) c[0]).DisCo(c[0].Invoices.RentingFees));
     
     //Residents  discount(105$)
     c[0].Rent(R[1], startdate, "daily", 2);
  
     c[0].Invoices.rentingFees(c[0].reservations.getType(), R[1].getModelnum(), 2);
     c[0].Invoices.Printinvoice(((Residents) c[0]).DisCo(c[0].Invoices.RentingFees));
     
     
     //2-Cancel
     c[1].Rent(R[2], startdate, "weekly", 2);
     c[1].Invoices.rentingFees(c[1].reservations.getType(), R[2].getModelnum(), 2);
     c[1].Cancel(c[1].reservations);
     c[1].Invoices.Printinvoice(((Residents) c[1]).DisCo(c[1].Invoices.RentingFees));
    
     
     //2-Exception Cancel
     c[1].Rent(R[2], startdate1, "weekly", 2);
     c[1].Invoices.rentingFees(c[1].reservations.getType(), R[2].getModelnum(), 2);
     c[1].Cancel(c[1].reservations);
     c[1].Invoices.Printinvoice(((Residents) c[1]).DisCo(c[1].Invoices.RentingFees));
  
     
     //Foreigners un discount(140$)
     c[2].Rent(R[1], startdate, "daily", 3);
 
     c[2].Invoices.rentingFees(c[2].reservations.getType(), R[1].getModelnum(), 3);
     c[2].Invoices.Printinvoice(((Foreigners) c[2]).unDisCo(c[2].Invoices.RentingFees));
     
     //3*Extend
     c[2].Rent(R[1], startdate, "daily", 3);
 
     c[2].Invoices.rentingFees(c[2].reservations.getType(), R[1].getModelnum(), 3);
     c[2].Extend(c[2].reservations, 3);
    // c[2].Extend(c[2].reservations, 3,"day"); / overloaded method
     c[2].Invoices.Printinvoice(((Foreigners) c[2]).unDisCo(c[2].Invoices.RentingFees));
     
     
     
     //4-Change Router
     c[3].Rent(R[4], startdate, "daily", 5);
    
     c[3].Invoices.rentingFees(c[3].reservations.getType(), R[4].getModelnum(), 5);
     c[3].change(R[3], 5);
     c[3].Invoices.Printinvoice(((Foreigners) c[3]).unDisCo(c[3].Invoices.RentingFees));
    //5-FeadBack
     c[3].FeedBack("when i change to the second router , i feel fine.");
     
 
    }

    
}

/**Class for Exceptions*/
class exception extends Exception{

    public exception(String message) {
        super(message);
    }
    
}

/**Class of Router is responsible for show details in Router*/
class Router {
    /**unique serial number of router*/
    public int serialnum ;
    private String Modelnum;
    private int numportes;
    static int numofRouter = 0;  // 8-Static data member


    public Router( String Modelnum , int numportes ) {
        
        this.Modelnum = Modelnum;
        this.numportes=numportes;
        numofRouter++;
        serialnum = numofRouter; 
    }

    public Router() {
    }


    public void setModelnum(String Modelnum) {
        this.Modelnum = Modelnum;
    }

    public void setNumportes(int numportes) {
        this.numportes = numportes;
    }

     public static int getNumofRouter() { // 9-Static method
        return numofRouter;
    }
  

    public String getModelnum() {
        return Modelnum;
    }

    public int getNumportes() {
        return numportes;
    }
  


     public void PrintRouter(){
     
    System.out.println("serial Number : "+serialnum);
    System.out.println("Model Name : "+Modelnum);
    System.out.println("Number of Portes : "+numportes);
    System.out.println("--------------------------------");
    System.out.println("");


     
     }
     
      public final void hello (){
    
    System.out.println("Hello our Dear Business");
    }
    
     
    
}

/**Class of Reservation is responsible for show details in Reservation*/
class Reservation {
      /**unique serial number of Reservation*/
    public int Reservationnum; //11- Different Access modifiers
    private LocalDate Reservationdate = LocalDate.now();  //11- Different Access modifiers
    private LocalDate StartDate;
    private String Type;
    private LocalDate DueTime;
    protected Router Routers; //11- Different Access modifiers
    public static int numofReservation =0;

    public Reservation( LocalDate StartDate, String Type, Router Routers) {
        
        this.StartDate = StartDate;
        this.Type = Type;
        this.Routers = Routers;
         numofReservation++;
     Reservationnum = numofReservation;
    }


    public Reservation() {
         numofReservation++;
     Reservationnum = numofReservation;
    }

    public LocalDate getReservationdate() {
        return Reservationdate;
    }

    public void setReservationdate(LocalDate Reservationdate) {
        this.Reservationdate = Reservationdate;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }

    public void setStartDate(LocalDate StartDate) {
        this.StartDate = StartDate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public LocalDate getDueTime() {
        return DueTime;
    }

    public void setDueTime(LocalDate DueTime) {
        this.DueTime = DueTime;
    }

    public Router getRouters() {
        return Routers;
    }

    public void setRouters(Router Routers) {
        this.Routers = Routers;
    }
    
    public LocalDate DueDATE(int d) //12-Calculated data members
            
{ 
       
        
    if(Type=="daily" | Type == "Daily")
    {
      
        
        DueTime = StartDate.plusDays(d)  ;
    }
    
    else if(Type=="weekly" | Type == "Weekly")
    {
                DueTime = StartDate.plusWeeks(d)  ;

    }
     
    else if(Type=="monthly" | Type == "Monthly")
    {
                DueTime = StartDate.plusMonths(d)  ;
    }
    else
    {
              System.out.println("Please choice the Right type in Reservation ");
              

    }
    setDueTime(DueTime);
    return (DueTime);

}
}

/**Class of Invoice is responsible for show details in Invoice*/
class Invoice {
    
   
     double RentingFees;
    private int CustomerNum;
    int dur ;
    /**object from Reservation class*/
    public  Reservation reservations;

    public Invoice(Reservation reservations) {
        this.reservations = reservations;
       //reservations.Reservationnum++;
    }

    
    
    public Invoice() {
        
       
    }

    public int getCustomerNum() {
        return CustomerNum;
    }

    public void setCustomerNum(int CustomerNum) {
        this.CustomerNum = CustomerNum;
    }
    
    

 
    
    public double rentingFees(String type , String routermodel , int dur) //12-Calculated data members
    {
     if(type=="daily" | type == "Daily")
    {
        if(routermodel=="CEQ9C")
        {
        RentingFees = 70 *dur ;
        }
        else if(routermodel=="ABW2Z")
        {
        RentingFees = 100*dur;
        }
        else if(routermodel=="IBM2M")
        {
        RentingFees = 120*dur;
        }
         else if(routermodel=="H2HK")
        {
        RentingFees = 105*dur;
        }
          else if(routermodel=="LO8H")
        {
        RentingFees = 30*dur;
        }
        
        
        else
        {
              System.out.println("Please choice the  Model Nume in list of Router");

        }
        
    }
    
    else if(type=="weekly" | type == "Weekly")
    {
              if(routermodel=="CEQ9C")
        {
        RentingFees = 490*dur;
        }
        else if(routermodel=="ABW2Z")
        {
        RentingFees = 700*dur;
        }
        else if(routermodel=="IBM2M")
        {
        RentingFees = 800*dur;
        }
        
         else if(routermodel=="H2HK")
        {
        RentingFees = 735*dur;
        }
          else if(routermodel=="LO8H")
        {
        RentingFees = 210*dur;
        }
          
        else
        {
              System.out.println("Please choice the  Model Nume in list of Router");

        } 
    }
     
    else if(type=="monthly" | type == "Monthly")
    {
        if(routermodel=="CEQ9C")
        {
        RentingFees = 2100*dur;
        }
        else if(routermodel=="ABW2Z")
        {
        RentingFees = 3000*dur;
        }
        else if(routermodel=="IBM2M")
        {
        RentingFees = 3500*dur;
        }
         else if(routermodel=="H2HK")
        {
        RentingFees = 3150*dur;
        }
          else if(routermodel=="LO8H")
        {
        RentingFees = 900*dur;
        }
        else
        {
              System.out.println("Please choice the  Model Nume in list of Router");

        }
    }
    
    
     else
    {
              System.out.println("Please choice the Right type in Reservation ");
              

    }
     this.dur=dur;
     return RentingFees;
    }
   
    public void  Printinvoice(double Rant){
     RentingFees= Rant;
    System.out.println("Reservation Number : "+reservations.Reservationnum);   
    System.out.println("Customer Number : "+CustomerNum);    
    System.out.println("serial Number of Router : "+reservations.getRouters().serialnum);
    System.out.println("Model Name of Router : "+reservations.getRouters().getModelnum());
    System.out.println("Number of Portes of Router : "+reservations.getRouters().getNumportes());
    System.out.println("Start date of Reservation : "+reservations.getStartDate());
    System.out.println("Due Date of Reservation : "+reservations.getDueTime());
    System.out.println("RentingFees : "+RentingFees+" Dollars ");
    System.out.println("--------------------------------");
    System.out.println();


     
     }
}


/**A parent abstract Class of Customer is responsible for show details in Customer*/
 abstract class Customer { //1-Inheritance tree

   /**unique serial number of Customer*/ 
    public int customernum;//11- Different Access modifiers
    private String Name;//11- Different Access modifiers
    private String Payment;
    private String Feedback;
    static int numofCustomer =0;
    protected Reservation reservations; //11- Different Access modifiers
    protected Invoice Invoices; 
    protected ArrayList<Invoice> invoices;

    public Customer(String Name, String Payment) {
        this.Name = Name;
        this.Payment = Payment;
        invoices = new ArrayList<Invoice>();
        numofCustomer++;
        customernum=numofCustomer;
        
    }

    
      

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPayment(String Payment) {
        this.Payment = Payment;
    }
        

    public String getName() {
        return Name;
    }

    public String getPayment() {
        return Payment;
    }

    public String getFeedback() {
        return Feedback;
    }
    
   public void Rent(Router Routers ,LocalDate StartDate, String Type , int r)
   {
   reservations = new Reservation();
   reservations.setRouters(Routers);
   reservations.setStartDate(StartDate);
   reservations.setType(Type);
   reservations.DueDATE(r);
   Invoices = new Invoice(reservations);
   Invoices.setCustomerNum(customernum);
   invoices.add(Invoices);
    System.out.println("Your Rent is added successfully");
   
   }
    
   public void Cancel(Reservation res)
    {
    try{ //10-Exception handling
    if(reservations.getStartDate().getDayOfMonth()-reservations.getReservationdate().getDayOfMonth() <2)
    {
        throw new exception("You Can't Cancel Rent , Becasue accepted only before the start date at least by 2 day");
    }
    
    }catch(exception ex)
    {
        System.out.println("Error "+ex.getMessage());
      
    }
    
    if(reservations.getStartDate().getDayOfMonth()-reservations.getReservationdate().getDayOfMonth() >=2)
    {
    for(int i= 0; i < invoices.size();i++)
        {
             if(Invoices.reservations == res)
                 {
                   invoices.remove(Invoices);
                   break;
                 }
        }
      
   
      System.out.println("Your Rent is Cancelled successfully");
    
    }
    }
    
   public void Extend(Reservation res,int r)
   {
       int k=r;
       r=r+Invoices.dur;
        if(reservations.getType()=="daily" | reservations.getType() == "Daily")
    {
      
       reservations.setDueTime( reservations.getDueTime().plusDays(k));
        Invoices.rentingFees(reservations.getType(), reservations.Routers.getModelnum(), r);
    }
    
    else if(reservations.getType()=="weekly" | reservations.getType() == "Weekly")
    {
        Invoices.rentingFees(reservations.getType(), reservations.Routers.getModelnum(), r);
        reservations.setDueTime( reservations.getDueTime().plusWeeks(k));
    }
     
    else if(reservations.getType()=="monthly" | reservations.getType() == "Monthly")
    {
        Invoices.rentingFees(reservations.getType(), reservations.Routers.getModelnum(), r);
              reservations.setDueTime(   reservations.getDueTime().plusMonths(k));
    }
              System.out.println("Your Rent is Extended successfully ");
   }
  //Polymorphism & Overloading Method
    public void Extend(Reservation res,int r ,String s)
   {
        int k=r;
       r=r+Invoices.dur;
       
        if(s=="day" | s == "Day"|s=="days" | s == "Days")
    {
        reservations.setDueTime( reservations.getDueTime().plusDays(k));
        Invoices.rentingFees(reservations.getType(), reservations.Routers.getModelnum(), r);
    }
    
    else if(s=="week" | s == "Week"|s=="weeks" | s == "Weeks")
    {
       Invoices.rentingFees(reservations.getType(), reservations.Routers.getModelnum(), r);
        reservations.setDueTime( reservations.getDueTime().plusWeeks(k));
    }
     
    else if(s=="month" | s == "Month"|s=="months" | s == "Months")
    {
       Invoices.rentingFees(reservations.getType(), reservations.Routers.getModelnum(), r);
              reservations.setDueTime(   reservations.getDueTime().plusMonths(k));
    }
              System.out.println("Your Rent is Extended successfully ");
   } 
   
    public void change(Router Routers,int r)
   {
       
   Rent(Routers,reservations.getStartDate(),reservations.getType(), r);
   Invoices.rentingFees(reservations.getType(), Routers.getModelnum(), r);
   System.out.println("Your change Rent is added successfully");
   }
    
    public void FeedBack(String Feedback)
   {
   this.Feedback=Feedback;
    System.out.println("Your Feedback is added successfully");
   }
    public void show() 
    { 
        System.out.println("Parent's show()"); 
    } 
    
    }


 
/**A  interface of Customer Residents */
  interface retaialitems //5-Interface
{
 public double getDiscount()  ;
 
       
}
/**A child  Class of Customer Residents is responsible for show details in Customer Residents*/
    class Residents extends Customer implements retaialitems{ //1-Inheritance tree //5-Interface
        private double discount ;
        

    public Residents( String Name,  String Payment ) {
        super(Name, Payment);
        
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

        @Override //3-Overriding
    public double getDiscount() {
        return discount;
    }
    
       @Override //3-Overriding 
    public void show() 
    { 
        super.show(); 
        System.out.println("Child's show()"); 
    } 
    
    
        
   public double DisCo(double fea){
   
   discount = 0.75 * fea;
    System.out.println();
      System.out.println("The Invoice");
      System.out.println("-----------");
   System.out.println("Residents Reservation ");
   return discount;
   }
   
}

/**A  interface of Customer Foreigners */
 interface retaialitem //5-Interface
{
 public String getNationalty() ;
 
       
}

/**A child  Class of Customer Foreigners is responsible for show details in Customer Foreigners*/
   class Foreigners extends Customer implements retaialitem { //1-Inheritance tree //5-Interface
       private final String nationalty; //6-Final data member

    public Foreigners(String nationalty, String Name,  String Payment) {
        super(Name, Payment);
        this.nationalty = nationalty;
    }

   

       @Override //3-Overriding
    public final String getNationalty() { //7-Final method
        return nationalty;
    }
    
      @Override //3-Overriding
    public void show() 
    { 
        super.show(); 
        System.out.println("Child's show()"); 
    } 
    
         public double unDisCo(double fea){
   
   double undis = 1 * fea;
    System.out.println();
      System.out.println("The Invoice");
      System.out.println("-----------");
      System.out.println("Foreigners Reservation ");
      System.out.println("Customer Nationalty : "+nationalty);

   return undis;
   }
    
    
   }



  