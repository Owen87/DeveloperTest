import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Run {
    public static void main(String[] args){
    	
        ArrayList<Event> events = new ArrayList<Event>();
        // By defining the rows and columns as variables, I can increase/decrease the size of the grid
        int rows = 21;
        int columns = 21;
        int i, j;
        Event[][] eventMap = new Event[rows][columns];
        
        // I've created instances of events to randomly place inside the 2D array
        Event e1 = new Event(1, "Film Festival", 10.00f);
        Event e2 = new Event(2, "Circus", 8.00f);
        Event e3 = new Event(3, "Fun Run", 20.00f);
        Event e4 = new Event(4, "Ballet", 50.00f);
        Event e5 = new Event(5, "Basketball Game", 16.50f);
        Event e6 = new Event(6, "Wrestling Show", 24.95f);
        Event e7 = new Event(7, "Battle of the Bands", 7.50f);
        Event e8 = new Event(8, "Art Exhibition", 9.00f);
        Event e9 = new Event(9, "Silent Disco", 5.00f);
        Event n = new Event();
        
        Event[] eventList = {e1,e2,e3,e4,e5,e6,e7,e8,e9};
        
     // PRINT THE GRID
        System.out.println("_____________________________________EVENT MAP_________________________________________");
        System.out.println("Each event on this map is located in the grid below. The distance between events is 1km");
        System.out.println("The number 0 indicates that there is no event booked at that location.");
        System.out.println("");
        
        System.out.println("------------------------------------ MAP LEGEND ---------------------------------------");
        System.out.println("1: Film Festival          		2: Circus               		3: Fun Run");
        System.out.println("4: Ballet                 		5: Basketball Game      		6: Wrestling Show");
        System.out.println("7: Battle of the Bands    		8: Art Exhibition       		9: Silent Disco");
        System.out.println("");
        
        // PRINTING THE MAP
        for(i=0; i < rows; i++){
            for(j=0; j < columns; j++){
            	Random rn = new Random(); 
            	int randomNumber = rn.nextInt(9-0+1)+0; // Create a random number between 0 and 10
            	if(randomNumber % 4 == 0){ // If it is divisible by 4, then
                	Event re = getRandomEvent(eventList); // Select a random event from the event array
                	eventMap[i][j] = re; // then populate this location with the event
                	System.out.print(re.getEventID() + " ");
            	}else{ // Otherwise, put a non-event in the location
            		eventMap[i][j] = n; 
            		System.out.print(n.getEventID() + " ");
            	}
            }
            System.out.println("");
        }
        System.out.println("");
        
        // LOCATING ALL OF THE EVENTS ON THE MAP
        for(i=0; i < rows; i++){
            for(j=0; j < columns; j++){ 
            	if(eventMap[i][j].getEventID() > 0){ // If the event's ID number isn't 0, then it's an event
            		eventMap[i][j].setXLocation(i); // Store the event's location as a field in the event
            		eventMap[i][j].setYLocation(j);
            		events.add(eventMap[i][j]); // add the event to the "events" ArrayList
            	}
            }         
        }
        System.out.println("");
        
        // USER INPUT
        int xLocation = 0;
        int yLocation = 0;
        int xIndex, yIndex;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter your X coordinate (between 0 an 21):");
        xIndex = scanner.nextInt();
        for(j=0; j < xIndex; j++){
        		xLocation++;
        }
        
        System.out.println("Enter your Y coordinate (between 0 an 21):");
        yIndex = scanner.nextInt();
        for(i=0; i < yIndex ; i++){
        	yLocation++;
        }
        System.out.println("");
        
        System.out.println("You are at position [" + xIndex + "][" + yIndex + "], which is a " + eventMap[yLocation][xLocation].getEventName());
        System.out.println("");
        
        // NOW FOR THE SEARCHING
        int xSteps;
        int ySteps;
        ArrayList<Event> distanceList = new ArrayList<Event>();
        
        // FIND THE DISTANCE BETWEEN THE USER'S LOCATION AND ALL EVENTS
        for(Event event:events){ // for each event in the events arraylist
        	if(eventMap[yLocation][xLocation].getXLocation() > event.getXLocation()){ // if the X location in the eventmap event is bigger than the X location stored in the event
        		xSteps = eventMap[yLocation][xLocation].getXLocation() - event.getXLocation(); // subtract 
        	}else{
        		xSteps = event.getXLocation() - eventMap[yLocation][xLocation].getXLocation();
        	}
        	if(eventMap[yLocation][xLocation].getYLocation() > event.getYLocation()){ // perform the same with the Y locations
        		ySteps = eventMap[yLocation][xLocation].getYLocation() - event.getYLocation();
        	}else{
        		ySteps = event.getYLocation() - eventMap[yLocation][xLocation].getYLocation();
        	}

            int distance = xSteps + ySteps; // the distance between 
            event.setDistance(distance);
            distanceList.add(event);
        }
        
        // SORT THE EVENTS BY DISTANCE
        Collections.sort(distanceList, new Comparator<Event>(){
			@Override
			public int compare(Event eventDistance1, Event eventDistance2) {
				for(int searchDistance = 0; searchDistance < distanceList.size(); searchDistance++){
					if(eventDistance1.getEventID() == eventDistance2.getEventID() && eventDistance1.getDistance() == eventDistance2.getDistance()){
						continue;
					}
				}
				return eventDistance1.getDistance() - eventDistance2.getDistance();
			}
        });

        // Put the sorted events into a Hash Set to remove duplicate entries
        LinkedHashSet<Event> uniqueEvents = new LinkedHashSet<Event>();
        for(int ue = 0; ue < distanceList.size(); ue++){
        	uniqueEvents.add(distanceList.get(ue));
        }

        // Put the Hashset back into an ArrayList and print the 5 closest events
        System.out.println("THE FIVE NEAREST EVENTS TO YOUR LOCATION ARE: ");
        ArrayList<Event> sortedEventList = new ArrayList<Event>(uniqueEvents);
        for(int p = 1; p < 6; p++){
        	System.out.println("The " + sortedEventList.get(p).getEventName() + " is " + sortedEventList.get(p).getDistance() + "km away.");
        	System.out.printf("Tickets are $%.2f", sortedEventList.get(p).getTicketPrice());
        	System.out.println("\n");
        }
    }
    
    public static Event getRandomEvent(Event[] eventList) {
        int randomEvent = new Random().nextInt(eventList.length);
        return eventList[randomEvent];
    }
}