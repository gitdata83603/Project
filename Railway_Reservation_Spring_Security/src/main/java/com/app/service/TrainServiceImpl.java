package com.app.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.PriceDao;
import com.app.dao.RouteDao;
import com.app.dao.StationDao;
import com.app.dao.TrainDao;
import com.app.dao.TravelDateDao;
import com.app.dto.ApiResponse;
import com.app.dto.OneTrainRespDto;
import com.app.dto.RouteRespDto;
import com.app.dto.TrainReqDto;
import com.app.dto.TrainRespDto;
import com.app.dto.TravelDateBookingRespDto;
import com.app.dto.TravelDateRespDto;
import com.app.dto.UpdateTrainNoNameSeatsDTO;
import com.app.dto.UpdatetrainTimeDTO;
import com.app.entities.PricePerKm;
import com.app.entities.Route;
import com.app.entities.Station;
import com.app.entities.Train;
import com.app.entities.TravelDate;

@Service
@Transactional
public class TrainServiceImpl implements TrainService
{

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	TrainDao traindao;
	
	@Autowired
	StationDao stationdao;
	
	@Autowired
	RouteDao routedao;
	
	@Autowired
	PriceDao pricedao;
	
	@Autowired
	TravelDateDao travelDateDao;
	
	
	public TrainServiceImpl() 
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ApiResponse addNewTrain(TrainReqDto dto) 
	{
		// TODO Auto-generated method stub
		
		Station startstation= stationdao.findById(dto.getStartstationid()).orElseThrow(()->new ResourceNotFoundException("Invalid Source Station Choosen"));
		Station destinationstation= stationdao.findById(dto.getEndstationid()).orElseThrow(()->new ResourceNotFoundException("Invalid destination Station Choosen"));
	    
		if(traindao.findByNumber(dto.getNumber()).isEmpty())
	    {
		
				Train train=mapper.map(dto, Train.class);//transient
			    train.setArrival(LocalTime.parse(dto.getArrival()));//parsing received time in string format into LocalTime
			    train.setDeparture(LocalTime.parse(dto.getDeparture()));
			    
			    train.setStart(startstation);//************establishing relationship between train and station(association)  --> unidirectional train has start_station_id
			    train.setEnd(destinationstation);//************establishing relationship between train and station(association)
			    train.setDestarrival(LocalTime.parse(dto.getDestarrival()));
			    train.setTotalEconomyClassSeats(dto.getTotalEconomyClassSeats());
			    train.setTotalFirstClassSeats(dto.getTotalFirstClassSeats());
			    		    
			    Train trainpersistent=traindao.save(train);//transient --> persistent
			    
			    //UUUUUUUUUUUUUUUUUUUUU<-----ESTABLISH RELATIONSHIP BETWEEN TRAIN AND DATE UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU
			    
			    TravelDate trdate=new TravelDate();
			    trdate.setStartDate(dto.getDate());
			    trdate.setEndDate(dto.getEnddate());
			    trdate.setBookedSeats(0);
			    trdate.setTotalEconomyClassBookedSeats(0);
			    trdate.setTotalFirstClassBookedSeats(0);
			    trdate.setTotalseats(trainpersistent.getTotalseats());
			    trdate.setTotalEconomyClassSeats(dto.getTotalEconomyClassSeats());
			    trdate.setTotalFirstClassSeats(dto.getTotalFirstClassSeats());
			    trdate.setTrain(trainpersistent);
			    
			    travelDateDao.save(trdate);
			    
			    
			    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
			    
			    
                //******************************establishing relationship between train and routes*****************************************
			    
			    Route startroute=new Route();//transient
			    startroute.setArrival(train.getArrival());
			    startroute.setDistance(0);
			    startroute.setDeparture(train.getDeparture());
			    startroute.setStation(startstation);//establishing relationship between Route and Station
			    train.addTrainRoute(startroute);   //making changes in persistent entity --> automatic insert query will be fired on routes tables
			    //calling helper method
			    
			    Route destroute=new Route();//transient
			    destroute.setArrival(train.getDestarrival());
			    destroute.setDistance(train.getDistance());
			    destroute.setStation(destinationstation);
			    train.addTrainRoute(destroute);
			    
			    
			    return new ApiResponse("successfully added the train with id "+trainpersistent.getId());
			    
			    
			    
	    
	    }
	    
		return new ApiResponse("Please Enter Unique train Number ");
	}

	@Override
	public List<TrainRespDto> getTrainDetails(String source, String destination,LocalDate traveldate)
	{
		// TODO Auto-generated method stub
		System.out.println("source ==>"+source);
		System.out.println("destination==>"+destination);
		if(stationdao.findByName(source).isEmpty())
		{
			throw new ResourceNotFoundException("Source station Not Found"); 
		}
		if(stationdao.findByName(destination).isEmpty())
		{
			throw new ResourceNotFoundException("destination station Not Found"); 
		}
		
		//get list of routes
		 List<Route> routes= routedao.findroutes(source, destination);
		System.out.println(routes);
		
		
		
		
		 List<Train> trains=new ArrayList<Train>();
		 
		 List<TrainRespDto> trainrespDtolist=new ArrayList<TrainRespDto>();
		 
		 PricePerKm priceperkm= pricedao.findAll().get(0);
        for (int i = 0; i < routes.size(); i++) 
        {
		   for(int j = i+1; j < routes.size(); j++)
		   {
			   if(routes.get(i).getTrain().getId()==routes.get(j).getTrain().getId() /*&& routes.get(i).getTrain().getDate().equals(traveldate)*/ )
			   {//train found
				   Route sourceroute=null;
				   Route destinationroute=null;
				   
				   if(travelDateDao.findtrainwithdate(traveldate,routes.get(i).getTrain().getId()).isEmpty()) //train on given date not found
				   {
					   continue;
				   }
				   Long travelDateId=travelDateDao.findTravelDateId(traveldate, routes.get(i).getTrain().getId());
				   TravelDate tr=travelDateDao.findById(travelDateId).get();
				   //System.out.println( "i ==>"+ routes.get(i).getStation().getName());
				   if(routes.get(i).getStation().getName().equalsIgnoreCase(source))//i->kadegaon
				   {
					 sourceroute=routes.get(i);
					 destinationroute=routes.get(j);
				
				   }
				   else
				   { //sangli->kadegaon
					     sourceroute=routes.get(j);//kadegaon
						 destinationroute=routes.get(i);//sangli
					
				   }
				 
				   if(sourceroute.getDistance()>destinationroute.getDistance())
				   {
					 continue;   
				   }
				   
				   
				   TrainRespDto trainRespDto=new TrainRespDto();
				   
				   Train train= routedao.findTrains(routes.get(i).getTrain().getId()).get(0);
				   trainRespDto.setId(train.getId());
				   trainRespDto.setNumber(train.getNumber());
				   trainRespDto.setName(train.getName());
				   trainRespDto.setDate(traveldate);
				   trainRespDto.setTotalseats(tr.getTotalseats());
				   trainRespDto.setSourcearrival(sourceroute.getArrival());
				   trainRespDto.setDestinatiionarrival(destinationroute.getArrival());
				   trainRespDto.setDist(destinationroute.getDistance()-sourceroute.getDistance());
				   trainRespDto.setDestination(destination);
				   trainRespDto.setSource(source);
				   trainRespDto.setTavelDateId(travelDateId);
				   trainRespDto.setSourceRouteid(sourceroute.getId());
				   trainRespDto.setDestinationRouteid(destinationroute.getId());
				   trainRespDto.setAvailableseats(tr.getTotalseats()-tr.getBookedSeats());
				   trainRespDto.setPrice(trainRespDto.getDist()*priceperkm.getEconomyClassPrice()+trainRespDto.getDist()*priceperkm.getFirstClassPrice());
				   trainrespDtolist.add(trainRespDto);
				   
			   }
		   }
		}	
        
       
		
		return trainrespDtolist;
		
		/*
		 private Long id;  //from train
    private String number;//from train
	private String name;//from train
	private LocalDate date;//start_date-->from train
	private LocalTime sourcearrival;//use getter in Route to set it     -->from Route
	private LocalTime destinatiionarrival;//use getter in Route to set it   -->from Route
	private Integer totalseats;//from train
	private Integer dist;////updated between stations    //distance of destination from train start station - distance of source from  train start station
		 */
		
	}


	/*
	 OP-->
	 
	 [
		  {
		    "id": 1,
		    "number": "IND9941",
		    "name": "KARAD EXPRESS",
		    "date": "2024-07-25",
		    "sourcearrival": "10:15:30",
		    "destinatiionarrival": "13:55:20",
		    "totalseats": 500,
		    "dist": 100
		  }
	]
	 */

	
	
	@Override
	public List<TrainRespDto> getAllTrainDetails()
	{		
		
		 List<Train> trains= traindao.findAll();
		 
		 List<TrainRespDto> list=new ArrayList<TrainRespDto>();
		 
		 for(Train train:trains)
		 {
			   
			   TrainRespDto trainRespDto=new TrainRespDto();
			   trainRespDto.setId(train.getId());
			   trainRespDto.setNumber(train.getNumber());
			   trainRespDto.setName(train.getName());
			//   trainRespDto.setDate(traveldate);     //*********************will see in details button on each train in table
			   trainRespDto.setTotalseats(train.getTotalseats());
			   trainRespDto.setSourcearrival(train.getArrival());
			   trainRespDto.setDestinatiionarrival(train.getDestarrival());
			   trainRespDto.setDist(train.getDistance());
			   trainRespDto.setSource(train.getStart().getName());
			   trainRespDto.setDestination(train.getEnd().getName());
			   
			   list.add(trainRespDto);
		
		 }
		 
		 
		return list;
	}

	@Override
	public OneTrainRespDto getOneTrainDetail(Long trainId) 
	{
		// TODO Auto-generated method stub
	
		Train train= traindao.findById(trainId).orElseThrow(()->new ResourceNotFoundException("Train Not Found"));
		
		List<Route> routes= routedao.findByTrainId(trainId);
		List<TravelDate> traveldates=travelDateDao.findByTrainId(trainId);
		
		OneTrainRespDto oneTrainRespDto=mapper.map(train, OneTrainRespDto.class);//*********************
		
		/*
		 	private LocalDate date;//start_date-->from train	
		    private Double fare;
		    private List<TravelDateRespDto> dates;
		   
		 */
		oneTrainRespDto.setSourceStationName(train.getStart().getName());
		oneTrainRespDto.setSourcearrival(train.getArrival());
		oneTrainRespDto.setDestinatiionarrival(train.getDestarrival());
		oneTrainRespDto.setDestinationStationName(train.getEnd().getName());
		
		
		
		List<RouteRespDto> routesresp=new ArrayList<RouteRespDto>();
		for(Route route:routes)
		{
			RouteRespDto routeRespDto=new RouteRespDto();
			routeRespDto.setArrivalTime(route.getArrival());
			routeRespDto.setDepartureTime(route.getDeparture());
			routeRespDto.setDistanceFromStart(route.getDistance());
			routeRespDto.setStationName(route.getStation().getName());
			routesresp.add(routeRespDto);
		}
		Collections.sort(routesresp, (o1,o2)-> o1.getDistanceFromStart()-o2.getDistanceFromStart());
		
		oneTrainRespDto.setRoutes(routesresp);//****************************************************
		
		
		
		
		List<TravelDateRespDto> dates=new ArrayList<TravelDateRespDto>();
		
		
		for(TravelDate traveldate:traveldates)
		{
			TravelDateRespDto travelDateRespDto=new TravelDateRespDto();
			travelDateRespDto.setBookedSeats(traveldate.getBookedSeats());
			travelDateRespDto.setStartDate(traveldate.getStartDate());
			travelDateRespDto.setTotalEconomyClassSeats(traveldate.getTotalEconomyClassSeats());
			travelDateRespDto.setTotalFirstClassSeats(traveldate.getTotalFirstClassSeats());
			travelDateRespDto.setTotalEconomyClassBookedSeats(traveldate.getTotalEconomyClassBookedSeats());
			travelDateRespDto.setTotalFirstClassBookedSeats(traveldate.getTotalFirstClassBookedSeats());
			dates.add(travelDateRespDto);
		}
		
		oneTrainRespDto.setDates(dates);//****************************************************
		
		
		return oneTrainRespDto;
	}


	
	
	/*   Train class 
	private Long id;****
	private String number;****
	private String name;****
	

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "train")
    private List<Route> routes=new ArrayList<Route>();
    
	
	private Integer totalseats;****
	
    private Integer distance;****
	 
	 *
	 *
	 *
	 
	OneTrainRespDto class
	
	private Long id;  //from train****
    private String number;//from train****
	private String name;//from train****
	private Integer totalseats;//from train****
	private Integer distance;****
	
	
	
	
	private LocalDate date;//start_date-->from train
	private String SourceStationName;
	private LocalTime sourcearrival;//use getter in Route to set it     -->from Route
	private String destinationStationName;
	private LocalTime destinatiionarrival;//use getter in Route to set it   -->from Route
	
    private Double fare;
    private List<TravelDateRespDto> dates;
    private List<RouteRespDto> routes;
	 
	 
	 
	 
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 @Override
	public List<Train> getTrainDetails(String source, String destination)
	{
		// TODO Auto-generated method stub
		
		if(stationdao.findByName(source).isEmpty())
		{
			throw new ResourceNotFoundException("Source station Not Found"); 
		}
		if(stationdao.findByName(destination).isEmpty())
		{
			throw new ResourceNotFoundException("destination station Not Found"); 
		}
		
		//get list of routes
		 List<Route> routes= routedao.findroutes(source, destination);
		System.out.println(routes);
		
		List<Train> trains=new ArrayList<Train>();
        for (int i = 0; i < routes.size(); i++) 
        {
		   for(int j = i+1; j < routes.size(); j++)
		   {
			   if(routes.get(i).getTrain().getId()==routes.get(j).getTrain().getId())
			   {
				   trains.add(routedao.findTrains(routes.get(i).getTrain().getId()).get(0));
				 
			   }
		   }
		}	
		
		return trains;
	}
	 */
	
	/*
	 
	 SOURCE -->SANLGI
	 DESTINATION-->TAKARI
	 
	 
	 OP-->
	    
	    [
			  {
			    "id": 1,
			    "createdOn": "2024-06-30",
			    "updatedOn": "2024-06-30T17:49:57.896214",
			    "number": "IND9941",
			    "name": "KARAD EXPRESS",
			    "start": {                       ///////////////STATION INFO
			      "id": 1,
			      "createdOn": "2024-06-30",
			      "updatedOn": "2024-06-30T17:31:52.303623",
			      "code": "MH10SANGLI",
			      "name": "SANGLI"
			    },
			    "end": {                        ///////////////STATION INFO
			      "id": 3,
			      "createdOn": "2024-06-30",
			      "updatedOn": "2024-06-30T17:32:40.345164",
			      "code": "MH09KOLHAPUR",
			      "name": "KOLHAPUR"
			    },
			    "date": "2024-07-25",
			    "enddate": "2024-07-25",
			    "arrival": "10:15:30",
			    "destarrival": "13:55:20",
			    "departure": "11:55:20",
			    "totalseats": 500,
			    "bookedseats": 0,
			    "distance": 100
			  }
       ]

	 
	 
	 */
	
	@Override
	public OneTrainRespDto getTrainDetailsByNumber(String trainNumber)
	{
	Train train= traindao.findByNumber(trainNumber).orElseThrow(()->new ResourceNotFoundException("Train Not Found"));
		
		List<Route> routes= routedao.findByTrainId(train.getId());
		List<TravelDate> traveldates=travelDateDao.findByTrainId(train.getId());
		
		OneTrainRespDto oneTrainRespDto=mapper.map(train, OneTrainRespDto.class);//*********************
		
		/*
		 	private LocalDate date;//start_date-->from train	
		    private Double fare;
		    private List<TravelDateRespDto> dates;
		 */
		oneTrainRespDto.setSourceStationName(train.getStart().getName());
		oneTrainRespDto.setSourcearrival(train.getArrival());
		oneTrainRespDto.setDestinatiionarrival(train.getDestarrival());
		oneTrainRespDto.setDestinationStationName(train.getEnd().getName());
		
		
		
		List<RouteRespDto> routesresp=new ArrayList<RouteRespDto>();
		for(Route route:routes)
		{
			RouteRespDto routeRespDto=new RouteRespDto();
			routeRespDto.setArrivalTime(route.getArrival());
			routeRespDto.setDepartureTime(route.getDeparture());
			routeRespDto.setDistanceFromStart(route.getDistance());
			routeRespDto.setStationName(route.getStation().getName());
			routesresp.add(routeRespDto);
		}
		Collections.sort(routesresp, (o1,o2)-> o1.getDistanceFromStart()-o2.getDistanceFromStart());
		
		oneTrainRespDto.setRoutes(routesresp);//****************************************************
		
		
		
		
		List<TravelDateRespDto> dates=new ArrayList<TravelDateRespDto>();
		
		
		for(TravelDate traveldate:traveldates)
		{
			TravelDateRespDto travelDateRespDto=new TravelDateRespDto();
			travelDateRespDto.setBookedSeats(traveldate.getBookedSeats());
			travelDateRespDto.setStartDate(traveldate.getStartDate());
			travelDateRespDto.setTotalEconomyClassSeats(traveldate.getTotalEconomyClassSeats());
			travelDateRespDto.setTotalFirstClassSeats(traveldate.getTotalFirstClassSeats());
			travelDateRespDto.setTotalEconomyClassBookedSeats(traveldate.getTotalEconomyClassBookedSeats());
			travelDateRespDto.setTotalFirstClassBookedSeats(traveldate.getTotalFirstClassBookedSeats());
			dates.add(travelDateRespDto);
		}
		
		oneTrainRespDto.setDates(dates);//****************************************************
		
		
		return oneTrainRespDto;
	}

	@Override
	public List<TravelDateBookingRespDto> getTrainTraveldateDetailsByNumber(String trainNumber) 
	{
		// TODO Auto-generated method stub
	    Train train=traindao.findByNumber(trainNumber).orElseThrow(()->new ResourceNotFoundException("Train Not Found"));
		List<TravelDate> trains=travelDateDao.findByTrainId(train.getId());     
		
		List<TravelDateBookingRespDto> responses=new ArrayList<TravelDateBookingRespDto>();
		
		for(TravelDate traintravel:trains)
		{
			TravelDateBookingRespDto dto=mapper.map(traintravel,TravelDateBookingRespDto.class);     //new TravelDateBookingRespDto();
			dto.setNumber(trainNumber);
			dto.setTrainid(traintravel.getTrain().getId());
	        dto.setTravelDateId(traintravel.getId());
	        responses.add(dto);
		}
		Collections.sort(responses,(o1,o2)->o1.getStartDate().compareTo(o2.getStartDate()) );
		
		return responses;
	}

	@Override
	public ApiResponse updateTrainTravelTimemethod(UpdatetrainTimeDTO updatetrainTimeDTO, String trainNumber) 
	{
		
		System.out.println("Inside Method updateTrainTravelTimemethod(UpdatetrainTimeDTO updatetrainTimeDTO, String trainNumber)");
		//System.out.println("updated time : "+updatetrainTimeDTO.getSourcearrivaltime() + "  "+updatetrainTimeDTO.getDestinationarrivaltime());
		
		// TODO Auto-generated method stub
	/*
	 	    
	    B.update travel time --> requires train id , route id , all bookings related to given train 
	      two cases-->
	      
	      	  A.already has booked(i.e. changing in traveltime of whole train)
	      	    --> requires changes in train,route,booking table
	      	    
	      	  B.No bookings
	            --> doesn't require changes in booking table but only train and route table
	      
	      steps-->
	      1.find train id by given train number
	      2.find the difference between the earlier arrival and new arrival + difference between earlier arrival and departure
	      3.update source arrival,departure and destination arrival in train class + route class for both source and destination
	      4.if(earlier arrival < new arrival)
	        A.add the difference time in each route arrival
	        B.add the difference between earlier arrival and departure in departure of every route
	      5.if(earlier arrival > new arrival)
	        A.substract the difference time in each route arrival
	        B.add the difference between earlier arrival and departure in departure of every route
	      
	 
	 
	 
Update Time--> 
    ////////////
    classes involved
     1.Train for source and destination arrival time
     2.Route
     3.booking
   ////////////   
	class Train
	{
	 	1.private LocalTime arrival;     //source arrival
	 	2.private LocalTime destarrival; //destination arrival
	 	3.private LocalTime departure;   //source departure
	  	4.private Integer totalseats;
	}
	
	
	 **Requires updation in Route
	   
	   class Route
	   {
	     private LocalTime arrival;
         private LocalTime departure;   
	   }
	   
	   
	   
	   
	   
	 */
		
		
		
		Train train=traindao.findByNumber(trainNumber).orElseThrow(()->new ResourceNotFoundException("Train Not Found"));
		System.out.println("Train found");
		
		if(updatetrainTimeDTO.getSourcearrivaltime().getHour()>train.getArrival().getHour())
		{
		  System.out.println("inside if block"); 
		 
		  
		  // steps-->
		   //1.**********************Logic for difference between new source arrival time and earlier source arrival**********************
		     int totalnewseconds=( updatetrainTimeDTO.getSourcearrivaltime().getHour()*3600 ) + (updatetrainTimeDTO.getSourcearrivaltime().getMinute()*60) + (updatetrainTimeDTO.getSourcearrivaltime().getSecond());
		     int totalearlierseconds=(train.getArrival().getHour()*3600)+(train.getArrival().getMinute()*60)+(train.getArrival().getSecond());
		   
		     int diffsec=totalnewseconds-totalearlierseconds;//totalearlierseconds-totalnewseconds ***********change
		     
		     int hr=diffsec/3600;
		     int remsec=diffsec % 3600;
		     int min=remsec/60;
		     int sec=remsec % 60;
		   
		   
		     //setting the destination arrival time based upon train arrival time
		     
		     LocalTime destinationarrival=train.getDestarrival();
		     int totaldestinationarrivalsec=(destinationarrival.getHour()*3600)+(destinationarrival.getMinute()*60)+(destinationarrival.getSecond());
		     int diffbdestarrandsourcearrival=totaldestinationarrivalsec-totalearlierseconds;   
		     int totalnewdestarrivalsec=totalnewseconds+diffbdestarrandsourcearrival;
		     
		        int traindesthr=totalnewdestarrivalsec/3600;
			    int destrem=totalnewdestarrivalsec%3600;
			    int traindestarrmin=destrem/60;
			    int traindestarrsec=destrem%60;
		     
			 LocalTime newdestarr=LocalTime.of(traindesthr, traindestarrmin,traindestarrsec);   
		     
		     
		     
		     
		     List<Route> routes= routedao.findByTrainId(train.getId());
		     //System.out.println("ROUTE : "+routes);
		     for(Route route:routes)
		     {
		    	 System.out.println("route : "+route);
		    	LocalTime routeariivaltime= route.getArrival();
		    	int routeseconds=(routeariivaltime.getHour()*3600)+(routeariivaltime.getMinute()*60)+routeariivaltime.getSecond();
		    	int totalseconds=diffsec+routeseconds; //routeseconds-diffsec; *****change
		    	
		    	int routehr=totalseconds/3600;
			    int routeremsec=totalseconds % 3600;
			    int routemin=routeremsec/60;
			    int routesec=routeremsec % 60;
		    	//above calculated the route new arrival time
			    
			    if(route.getDeparture()==null)
			    {
			     // System.out.println("destination arrival time"+updatetrainTimeDTO.getDestinationarrivaltime());	
			      route.setArrival(newdestarr);
			      continue;	
			    }
			    
			    LocalTime routedeparturetime=route.getDeparture();
			    int routedepartureseconds=(routedeparturetime.getHour()*3600)+(routedeparturetime.getMinute()*60)+(routedeparturetime.getSecond());
			    int diff=routedepartureseconds-routeseconds;
			    int rrdiff=diff+totalseconds;
			    
			    int routedephr=rrdiff/3600;
			    int rem=rrdiff%3600;
			    int routedepmin=rem/60;
			    int routedepsec=rem%60;
			    //above calculated the route new departure time
			    
			    
			    //update the route arrival and departure--> persistent entity
			      route.setArrival(LocalTime.of(routehr, routemin, routesec)); 
			      route.setDeparture(LocalTime.of(routedephr, routedepmin, routedepsec));
		     }
		   
		  
		   //updating train arrival,departure,destination arrival time
		   //totalearlierseconds  
		     //1.calculating departure time
		     LocalTime trainsourcedep=train.getDeparture();
		     int trainsourcedepsec=(trainsourcedep.getHour()*3600)+(trainsourcedep.getMinute()*60)+(trainsourcedep.getSecond());
		     int secdiff=trainsourcedepsec-totalearlierseconds;
		     int finalsec=totalnewseconds+secdiff;
		     
		        int trainsrcdephr=finalsec/3600;
			    int rem=finalsec%3600;
			    int trainsrcdepmin=rem/60;
			    int trainsrcdepsec=rem%60;
		     
			    train.setDeparture(LocalTime.of(trainsrcdephr, trainsrcdepmin, trainsrcdepsec));
			    
			    
			 //2.setting destination arrival time  
			 //totalnewdestarrivalsec   
			 
			    
		     train.setDestarrival(newdestarr);
		     
		     
		   //3.setting source arrival time
		   
		     train.setArrival(updatetrainTimeDTO.getSourcearrivaltime());
		     
		     
		 /*  
		     //************************logic for addition of time in route case(hr,min,sec)**************************completed
		   //hr,min and sec are the difference we obtained --> now add this difference in each route arrival
		   //repeat logic number 2 in each route case
		   
		  
		    2.**********************Logic for source departure**********************************  completed
		   
		   //substract departure time from arrival time
		    
		    int totaldepartureseconds=(train.getDeparture().getHour()*3600)+(train.getDeparture().getMinute()*60)+(train.getDeparture().getSecond());
		   //above will work only for source departure
		   
		     int secdiff=totaldepartureseconds-totalearlierseconds;
		     int dhr=secdiff/3600;
		     int dremsec=secdiff % 3600;
		     int dmin=dremsec/60;
		     int dsec=dremsec % 60;
		   
		   //logic for addition of dhr,dmin,dsec with new source arrival time
		  
		   
		   
		   
		   */
		  
		}
		else
		{
          //updatetrainTimeDTO.getSourcearrivaltime().getHour()<train.getArrival().getHour()
          /*
           1.**********************Logic for difference between new source arrival time and earlier source arrival**********************
		     int totalnewseconds=( updatetrainTimeDTO.getSourcearrivaltime().getHour()*3600 ) + (updatetrainTimeDTO.getSourcearrivaltime().getMinute()*60) + (updatetrainTimeDTO.getSourcearrivaltime().getSecond());
		     int totalearlierseconds=(train.getArrival().getHour()*3600)+(train.getArrival().getMinute()*60)+(train.getArrival().getSecond());
		   
		     int diffsec=totalearlierseconds-totalnewseconds;
		   	 
		   	 int hr=diffsec/3600;
		     int remsec=diffsec % 3600;
		     int min=remsec/60;
		     int sec=remsec % 60;
		   			
		  
		     			
		   			
		   			
		   			
		   	  //************************logic for substraction of time(hr,min,sec) in route case**************************
		   //hr,min and sec are the difference we obtained --> now substract this difference in each route arrival
		   //repeat logic number 2 in each route case		
         
         
         
            2.**********************Logic for source departure**********************************  
		   
		   //substract departure time from arrival time
		    
		    int totaldepartureseconds=(train.getDeparture().getHour()*3600)+(train.getDeparture().getMinute()*60)+(train.getDeparture().getSecond());
		   //above will work only for source departure
		   
		     int secdiff=totaldepartureseconds-totalearlierseconds;
		     int dhr=secdiff/3600;
		     int dremsec=secdiff % 3600;
		     int dmin=dremsec/60;
		     int dsec=dremsec % 60;
		   
		   //logic for addition of dhr,dmin,dsec with earlier source arrival time   			
		 
           
           */
			System.out.println("inside else block"); 
			 
			  
			  // steps-->
			   //1.**********************Logic for difference between new source arrival time and earlier source arrival**********************
			     int totalnewseconds=( updatetrainTimeDTO.getSourcearrivaltime().getHour()*3600 ) + (updatetrainTimeDTO.getSourcearrivaltime().getMinute()*60) + (updatetrainTimeDTO.getSourcearrivaltime().getSecond());
			     int totalearlierseconds=(train.getArrival().getHour()*3600)+(train.getArrival().getMinute()*60)+(train.getArrival().getSecond());
			   
			     int diffsec=totalearlierseconds-totalnewseconds;//totalearlierseconds-totalnewseconds ***********change
			     
			     int hr=diffsec/3600;
			     int remsec=diffsec % 3600;
			     int min=remsec/60;
			     int sec=remsec % 60;
			   
			   
			     //setting the destination arrival time based upon train arrival time
			     
			     LocalTime destinationarrival=train.getDestarrival();
			     int totaldestinationarrivalsec=(destinationarrival.getHour()*3600)+(destinationarrival.getMinute()*60)+(destinationarrival.getSecond());
			     int diffbdestarrandsourcearrival=totaldestinationarrivalsec-totalearlierseconds;   
			     int totalnewdestarrivalsec=totalnewseconds+diffbdestarrandsourcearrival;
			     
			        int traindesthr=totalnewdestarrivalsec/3600;
				    int destrem=totalnewdestarrivalsec%3600;
				    int traindestarrmin=destrem/60;
				    int traindestarrsec=destrem%60;
			     
				 LocalTime newdestarr=LocalTime.of(traindesthr, traindestarrmin,traindestarrsec);   
			     
			     
			     
			     
			     List<Route> routes= routedao.findByTrainId(train.getId());
			     //System.out.println("ROUTE : "+routes);
			     for(Route route:routes)
			     {
			    	 System.out.println("route : "+route);
			    	LocalTime routeariivaltime= route.getArrival();
			    	int routeseconds=(routeariivaltime.getHour()*3600)+(routeariivaltime.getMinute()*60)+routeariivaltime.getSecond();
			    	int totalseconds=routeseconds-diffsec; //routeseconds-diffsec; *****change
			    	
			    	int routehr=totalseconds/3600;
				    int routeremsec=totalseconds % 3600;
				    int routemin=routeremsec/60;
				    int routesec=routeremsec % 60;
			    	//above calculated the route new arrival time
				    
				    if(route.getDeparture()==null)
				    {
				   //   System.out.println("destination arrival time"+updatetrainTimeDTO.getDestinationarrivaltime());	
				      route.setArrival(newdestarr);
				      continue;	
				    }
				    
				    LocalTime routedeparturetime=route.getDeparture();
				    int routedepartureseconds=(routedeparturetime.getHour()*3600)+(routedeparturetime.getMinute()*60)+(routedeparturetime.getSecond());
				    int diff=routedepartureseconds-routeseconds;
				    int rrdiff=diff+totalseconds;
				    
				    int routedephr=rrdiff/3600;
				    int rem=rrdiff%3600;
				    int routedepmin=rem/60;
				    int routedepsec=rem%60;
				    //above calculated the route new departure time
				    
				    
				    //update the route arrival and departure--> persistent entity
				      route.setArrival(LocalTime.of(routehr, routemin, routesec)); 
				      route.setDeparture(LocalTime.of(routedephr, routedepmin, routedepsec));
			     }
			   
			  
			   //updating train arrival,departure,destination arrival time
			   //totalearlierseconds  
			     //1.calculating departure time
			     LocalTime trainsourcedep=train.getDeparture();
			     int trainsourcedepsec=(trainsourcedep.getHour()*3600)+(trainsourcedep.getMinute()*60)+(trainsourcedep.getSecond());
			     int secdiff=trainsourcedepsec-totalearlierseconds;
			     int finalsec=totalnewseconds+secdiff;
			     
			        int trainsrcdephr=finalsec/3600;
				    int rem=finalsec%3600;
				    int trainsrcdepmin=rem/60;
				    int trainsrcdepsec=rem%60;
			     
				    train.setDeparture(LocalTime.of(trainsrcdephr, trainsrcdepmin, trainsrcdepsec));
				    
				    
				 //2.setting destination arrival time  
				 //totalnewdestarrivalsec   
				 
				    
			     train.setDestarrival(newdestarr);
			     
			     
			   //3.setting source arrival time
			   
			     train.setArrival(updatetrainTimeDTO.getSourcearrivaltime());
			
		}
		return new ApiResponse("Schedule changed successfully");
	}

	@Override
	public ApiResponse updateTrainNoNameSeatService(UpdateTrainNoNameSeatsDTO updatetrainDTO, String trainNumber) 
	{
		// TODO Auto-generated method stub
		Train train=traindao.findByNumber(trainNumber).orElseThrow(()->new ResourceNotFoundException("Train Not Found"));
		train.setNumber(updatetrainDTO.getNumber());
		train.setName(updatetrainDTO.getName());
		train.setTotalseats(updatetrainDTO.getTotalseats());
		train.setTotalEconomyClassSeats(updatetrainDTO.getTotalEconomyClassSeats());
		train.setTotalFirstClassSeats(updatetrainDTO.getTotalFirstClassSeats());
		return new ApiResponse("Train updated successfully");
	}

	@Override
	public ApiResponse getOneTrainDetailsByNumber(String trainNumber) 
	{
		// TODO Auto-generated method stub
		// Train train=traindao.findByNumber(trainNumber).orElseThrow(()->new ResourceNotFoundException("Train Not Found"));
		 Optional<Train> trains=traindao.findByNumber(trainNumber);
		 if(trains.isPresent())
		 {
			Train train=trains.get();
			return new ApiResponse(train.getId(),train.getNumber(), "success");
		 }
		 
		 
		return new ApiResponse("failure");
	}
	
}
