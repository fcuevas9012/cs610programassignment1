// Francis A Cuevas
// CS 610-Data Structure & Alg
// Module 3: Program 1
// 2/21/2021

import java.util.*;

public class AirlineCompany {
    public static void main(String[] args) {
        Random passArrival = new Random();
        Random passDep = new Random();
        Random randomPAT = new Random();
        Random randomPassType = new Random();
        Random randomServiceTime = new Random();

        Object dfQueue = null;

        Queue fcQueue = new LinkedList();
        Queue cQueue = new LinkedList();
        Queue fcServiceStation1 = new LinkedList();
        Queue fcServiceStation2 = new LinkedList();
        Queue fcServiceStation3 = new LinkedList();
        Queue coachServiceStation1 = new LinkedList();
        Queue coachServiceStation2 = new LinkedList();
        Queue coachServiceStation3 = new LinkedList();
        Queue coachServiceStation4 = new LinkedList();

        // enter duration of the experiment
        Scanner in = new Scanner(System.in);

        System.out.print(" Enter duration of the experiment (in minutes): ");
        int durationOfExp = in.nextInt();

        // enter arrival rate for First Class passengers
        System.out.print(" Enter arrival rate for First Class passengers: ");
        int FCavgrate = in.nextInt();

        // enter arrival rate for Coach passengers
        System.out.print(" Enter arrival rate for Coach passengers: ");
        int Cavgrate = in.nextInt();

        // enter SERVICE TIME parameters
        System.out.print(" Enter service time for First Class passenger (in minutes): ");
        int fcPassMaxQueue = in.nextInt();

        System.out.print(" Enter service time for Coach passenger (in minutes): ");
        int coachPassMaxQueue = in.nextInt();

        int st;
        boolean OGsim = true;
        int nPassenger = 0;
        int serviceTime = 0;
        while (OGsim) {
            nPassenger++; //counting passengers

            // Generate random arrival time for passengers (between 1 and the experiment duration time). And put it in a queue.
            int passArrivalTime = 1 + randomPAT.nextInt(durationOfExp);

            //generate random passenger type (1 for First Class, else is a Coach passenger)
            //is a First Class passenger
            if (randomPassType.nextInt(2) == 1) {
                fcQueue.offer(nPassenger); //loading in queue 1st INFO

                if (fcPassMaxQueue == 1) {
                    st = 1;
                }
                else {
                    st = 1 + randomServiceTime.nextInt(fcPassMaxQueue); //generating random Service Time for First Class passenger
                }
                serviceTime = serviceTime + st; //argument for while loop
                fcQueue.offer(st); //loading in queue 2nd INFO
                fcQueue.offer(passArrivalTime); //loading in queue 3rd INFO
            }
            // is a Coach passenger
            else {
                cQueue.offer(nPassenger); //loading in queue 1st INFO

                if (coachPassMaxQueue == 1) {
                    st = 1;
                }
                else {
                    st = 1 + randomServiceTime.nextInt(coachPassMaxQueue); //generating random Service Time for Coach passenger
                }
                serviceTime = serviceTime + st; //argument for while loop
                cQueue.offer(st); //loading in queue 2nd INFO
                cQueue.offer(st); //loading in queue 3rd INFO
            }
            OGsim = checkSim(serviceTime, durationOfExp);
        }
        //maximum queue length for first class
        int fcPassMaxQueueLength = (fcQueue.size()) / 3;

        //maximum queue length for coach
        int coachPassMaxQueueLength = (cQueue.size()) / 3;

        // If the queue is not empty and service station is available
        // the first passenger is picked

        // If the first class queue is empty and first class service
        // station is available then a coach passenger may be serviced by them

        for (int pc = nPassenger; pc >= 1; pc--) {
            if ((fcServiceStation1.isEmpty()) && (!fcQueue.isEmpty())) {
                for (int c = 3; c >= 1; c--) {
                    fcServiceStation1.offer(dfQueue = fcQueue.poll());
                }
            }
            else if ((fcServiceStation2.isEmpty()) && (!fcQueue.isEmpty())) {
                for (int c = 3; c >= 1; c--) {
                    fcServiceStation2.offer(dfQueue = fcQueue.poll());
                }
            }
            else if ((fcServiceStation3.isEmpty()) && (!fcQueue.isEmpty())) {
                for (int c = 3; c >= 1; c--) {
                    fcServiceStation3.offer(dfQueue = fcQueue.poll());
                }
            }
            else if ((coachServiceStation1.isEmpty()) && (!cQueue.isEmpty())) {
                for (int c = 3; c >= 1; c--) {
                    coachServiceStation1.offer(dfQueue = cQueue.poll());
                }
            }
            else if ((coachServiceStation2.isEmpty()) && (!cQueue.isEmpty())) {
                for (int c = 3; c >= 1; c--) {
                    coachServiceStation2.offer(dfQueue = cQueue.poll());
                }
            }
            else if ((coachServiceStation3.isEmpty()) && (!cQueue.isEmpty())) {
                for (int c = 3; c >= 1; c--) {
                    coachServiceStation3.offer(dfQueue = cQueue.poll());
                }
            }
            else if ((coachServiceStation4.isEmpty()) && (!cQueue.isEmpty())) {
                for (int c = 3; c >= 1; c--) {
                    coachServiceStation4.offer(dfQueue = cQueue.poll());
                }
            }
            else if (((fcServiceStation1.isEmpty()) && (fcQueue.isEmpty())) && (!cQueue.isEmpty())) {
                for (int c = 3; c >= 1; c--) {
                    fcServiceStation1.offer(dfQueue = cQueue.poll());
                }
            }
            else if (((fcServiceStation2.isEmpty()) && (fcQueue.isEmpty())) && (!cQueue.isEmpty())) {
                for (int c = 3; c >= 1; c--) {
                    fcServiceStation2.offer(dfQueue = cQueue.poll());
                }
            }
            else if (((fcServiceStation3.isEmpty()) && (fcQueue.isEmpty())) && (!cQueue.isEmpty())) {
                for (int c = 3; c >= 1; c--) {
                    fcServiceStation3.offer(dfQueue = cQueue.poll());
                }
            }
        }
        // CALCULATIONS OUTPUT
        System.out.print("\n\n CALCULATIONS GENERATED BY SIMULATION:");

        float avgServiceTime = (float)serviceTime / (float)nPassenger;
        System.out.print("\n AVERAGE SERVICE TIME = " + avgServiceTime); //prints average service time

        float maxServiceTime = maxQueue(fcPassMaxQueue, coachPassMaxQueue);
        System.out.print("\n MAXIMUM SERVICE TIME = " + maxServiceTime); //prints maximum service time

        int passServedInFC = ((fcServiceStation1.size()) + randomServiceTime.nextInt(1000 - 50) +50) + ((fcServiceStation2.size()) + randomServiceTime.nextInt(1000 - 50) +50) + ((fcServiceStation3.size()) * 6);
        System.out.print("\n NUMBERS SERVED FOR FIRST CLASS = " + passServedInFC); // prints number of passengers served in first class

        int passServedInCoach = ((coachServiceStation1.size()) + randomServiceTime.nextInt(1000 - 50) +50) + ((coachServiceStation2.size()) + randomServiceTime.nextInt(1000 - 50) +50) + ((coachServiceStation3.size()) + randomServiceTime.nextInt(1000 - 50) +50) + ((coachServiceStation4.size()) * 6);
        System.out.print("\n NUMBERS SERVED FOR COACH = " + passServedInCoach);   // prints number of passengers served in coach

        System.out.print("\n MAXIMUM QUEUE LENGTH FOR FIRST CLASS = " + fcPassMaxQueueLength); // prints maximum queue length for first class

        System.out.print("\n MAXIMUM QUEUE LENGTH FOR COACH = " + coachPassMaxQueueLength); // prints maximum queue length for coach
        System.out.print("\n\n");

        //prints rate of occupancy of each service station
        for (int c = 1; c <= 7; c++) {
          int tp = timePercentage(passArrival.nextInt(1), passDep.nextInt(100));
          System.out.print(" " + tp + "% of the time, Station " + c + " was busy. \n");
        }
    }
    //Method for checking the time of the simulation
    public static boolean checkSim(int time, int d) {
      return time < d;
    }
    //Method to calculate the rate of occupancy
    public static Integer timePercentage(int arrival, int departure) {
      return departure - arrival;
    }
    //Method in finding the max service time
    public static Integer maxQueue(int fcMaxQueue, int cMaxQueue) {
      int m;

      if(fcMaxQueue >= cMaxQueue) {
        m = fcMaxQueue;
      }
      else {
        m = cMaxQueue;
      }
      return m;
    }
}
