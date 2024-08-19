package com.app.helper;

import java.time.LocalDate;

public class CancellationLogic 
{
   
	public static int ticketCancellation(LocalDate currentDate,LocalDate bookingDate,int m)
	{
		
		 //m--> booking cancellation on m or after m--th day not allowed
		
	  //valid cancellation -->return 1 
      //invalid cancellation --> return 0
	   System.out.println("booking cancellation is not allowed after when"+ m + " days are  remaining before journey date");
	   if(bookingDate.getYear()-currentDate.getYear()==0)//same year
	   {
		   //A
		   if(bookingDate.getMonthValue()-currentDate.getMonthValue()==0)//same month
		   {
			//same year + same month                                   //**<2
			   if(bookingDate.getDayOfMonth()-currentDate.getDayOfMonth()<=m) //ticket cancellation only before 2 days of travel date
			   {
				   System.out.println("bookingDate.getDayOfMonth()"+bookingDate.getDayOfMonth());
				   System.out.println("currentDate.getDayOfMonth()"+currentDate.getDayOfMonth());
				   System.out.println(LocalDate.now());
				   System.out.println("date difference="+(bookingDate.getDayOfMonth()-currentDate.getDayOfMonth()));
				  return 0; //invalid cancellation   
			   }
			   else
			   {
				   return 1;//valid cancellation
				   
			   }
			   
		   }
		   //B      
		   else if(bookingDate.getMonthValue()-currentDate.getMonthValue()==1) //checking if booking is start of month and current date is end of month
		   {
		     
			 
			   
			   if(currentDate.getMonthValue()==1 && bookingDate.getMonthValue()==2)  //jan(31) and feb(1)  
			   {														   //**==30
				 if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()>=31-m )
				 {
					 System.out.println("logic is working fine");
				    return 0;//invalid cancellation	 
				 }
				 else
				 {
					 return 1;
				 }
			   }
			   else if(currentDate.getMonthValue()==2 && bookingDate.getMonthValue()==3)//28 feb and 1 march
			   {
				   if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()>=28-1)
					 {
					   //CD --> 2024-02-28      BD --> 2024-03-01
					   System.out.println("28 feb and 1 march logic executed");
					   
					    return 0;//invalid cancellation	 
					 }
					 else
					 {
						   System.out.println("28 feb and 1 march 'else' logic executed");
						 return 1;
					 }
			   }

			   else if(currentDate.getMonthValue()==3 && bookingDate.getMonthValue()==4)
			   {
				   if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()>=31-m )
					 {
					    return 0;//invalid cancellation	 
					 }
					 else
					 {
						 return 1;
					 }
			   }

			   else if(currentDate.getMonthValue()==4 && bookingDate.getMonthValue()==5)
			   {
				   if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()>=30-m )
					 {
					    return 0;//invalid cancellation	 
					 }
					 else
					 {
						 return 1;
					 }
			   }    

			   else if(currentDate.getMonthValue()==5 && bookingDate.getMonthValue()==6)
			   {
				   if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()>=31-m )
					 {
					    return 0;//invalid cancellation	 
					 }
					 else
					 {
						 return 1;
					 }
			   }
			   else if(currentDate.getMonthValue()==6 && bookingDate.getMonthValue()==7)
			   {
				   if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()>=30-m )
					 {
					    return 0;//invalid cancellation	 
					 }
					 else
					 {
						 return 1;
					 }
			   }
			   else if(currentDate.getMonthValue()==7 && bookingDate.getMonthValue()==8)
			   {
				   if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()>=31-m )
					 {
					    return 0;//invalid cancellation	 
					 }
					 else
					 {
						 return 1;
					 }
			   }
			   else if(currentDate.getMonthValue()==8 && bookingDate.getMonthValue()==9)
			   {
				   if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()>=31-m )
					 {
					    return 0;//invalid cancellation	 
					 }
					 else
					 {
						 return 1;
					 }
			   }
			   else if(currentDate.getMonthValue()==9 && bookingDate.getMonthValue()==10)
			   {
				   if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()>=30-m )
					 {
					    return 0;//invalid cancellation	 
					 }
					 else
					 {
						 return 1;
					 }
			   }
			   else if(currentDate.getMonthValue()==10 && bookingDate.getMonthValue()==11)
			   {
				   if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()>=31-m )
					 {
					    return 0;//invalid cancellation	 
					 }
					 else
					 {
						 return 1;
					 }
			   }
			   else if(currentDate.getMonthValue()==11 && bookingDate.getMonthValue()==12)
			   {
				   if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()>=30-m)
					 {
					    return 0;//invalid cancellation	 
					 }
					 else
					 {
						 return 1;
					 }
			   }
			   
	    	 }
		   else
		   {
			   return 1;  //difference between the months(booking month-current month) is grater than 1  --> valid cancellation 
			   
		   }
			   	
	   }
	   else if(bookingDate.getYear()-currentDate.getYear()==1)//cancellation in case of booking year month January and current year month december
	   {
		 if(currentDate.getMonthValue()-bookingDate.getMonthValue()==11)
		 {															//**==30
			if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()>=31-m) //31 dec and 1 jan 31-1=30(1 day -->should not be allowed)
			{
				System.out.println("next year if branch executed");
			   return 0;  //invalid cancellation	
			}
			else
			{
				System.out.println("next year logic else branch executed");
				return 1;
			}
		 }
		 else
		 {
			 return 1;//valid
		 }
	   }
	   
	   
	   else   //booking year is 2 year ahead of current year --> valid cancellation
	   {
		   
		   System.out.println("logic of gap between years of more than 1 executed");
		   return 1; //valid cancellation   
	   }
     
	   return 1;
	}
	
}






//else if(currentDate.getMonthValue()==3 || currentDate.getMonthValue()==5 || currentDate.getMonthValue()==7 ||currentDate.getMonthValue()==8 ||currentDate.getMonthValue()==10)
//{
//	 if(bookingDate.getMonthValue()==3 || bookingDate.getMonthValue()==5 || bookingDate.getMonthValue()==7 ||bookingDate.getMonthValue()==8 ||bookingDate.getMonthValue()==10 
//			 ||  bookingDate.getMonthValue()==4 ||bookingDate.getMonthValue()==6 ||bookingDate.getMonthValue()==9 ||bookingDate.getMonthValue()==11)
//	 {
//		 
//	 }
//}
























//if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()==29)
//{
//	return 1;//valid cancellation   
//}
//else if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()==26)
//{
//	if(bookingDate.getMonthValue()==3 && currentDate.getMonthValue()==2) //booking month is march and current month is february
//	{
//	  return 1;	
//	}
//	else
//	{
//		return 0;
//	}
//}
//else if(currentDate.getDayOfMonth()-bookingDate.getDayOfMonth()==28)
//{
//	if(currentDate.getMonthValue()==4 || currentDate.getMonthValue()==6 || currentDate.getMonthValue()==9 ||currentDate.getMonthValue()==11)
//	{
//		 return 1;
//	}
//	else
//	{
//		return 0;
//	}
//}
//
//}
//else
//{
//  return 1; //difference between the months in the same year is grater than 1(valid cancellation)
//}