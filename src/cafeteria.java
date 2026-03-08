import java.util.*;

public class cafeteria {

    static ArrayDeque<String> line = new ArrayDeque<>();
    static HashMap<String, Integer> arrival = new HashMap<>();

    static int currentTime = 0;
    static int servedCount = 0;
    static long totalWait = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        help();

        while (true) {

            System.out.print("> ");
            String cmd = sc.next();

            if (cmd.equalsIgnoreCase("HELP")) {
                help();
            }

            else if (cmd.equalsIgnoreCase("ARRIVE")) {

                String name = sc.next();

                if (arrival.containsKey(name)) {
                    System.out.println("Name already in system");
                } else {
                    line.addLast(name);
                    arrival.put(name, currentTime);
                    System.out.println(name + " arrived");
                }
            }

            else if (cmd.equalsIgnoreCase("VIP_ARRIVE")) {

                String name = sc.next();

                if (arrival.containsKey(name)) {
                    System.out.println("Name already in system");
                } else {
                    line.addFirst(name);
                    arrival.put(name, currentTime);
                    System.out.println("VIP " + name + " arrived");
                }
            }

            else if (cmd.equalsIgnoreCase("SERVE")) {

                if (line.isEmpty()) {
                    System.out.println("No one to serve");
                } else {

                    String name = line.removeFirst();

                    int wait = currentTime - arrival.get(name);

                    totalWait += wait;
                    servedCount++;

                    arrival.remove(name);

                    System.out.println("Served " + name + " wait " + wait);
                }
            }

            else if (cmd.equalsIgnoreCase("PRINT")) {

                System.out.println(line);
            }

            else if (cmd.equalsIgnoreCase("SIZE")) {

                System.out.println("Size = " + line.size());
            }

            else if (cmd.equalsIgnoreCase("PEEK")) {

                if (line.isEmpty())
                    System.out.println("Empty");
                else
                    System.out.println(line.peekFirst());
            }

            else if (cmd.equalsIgnoreCase("LEAVE")) {

                String name = sc.next();

                if (line.remove(name)) {
                    arrival.remove(name);
                    System.out.println(name + " left");
                } else {
                    System.out.println("Not found");
                }
            }

            else if (cmd.equalsIgnoreCase("TICK")) {

                int t = sc.nextInt();

                if (t >= 0) {
                    currentTime += t;
                }
            }

            else if (cmd.equalsIgnoreCase("STATS")) {

                double avg = 0;

                if (servedCount > 0)
                    avg = (double) totalWait / servedCount;

                System.out.println("Served = " + servedCount);
                System.out.println("Avg wait = " + avg);
            }

            else if (cmd.equalsIgnoreCase("EXIT")) {
                break;
            }

            else {
                System.out.println("Unknown command");
            }
        }
    }


    static void help() {

        System.out.println("HELP ARRIVE VIP_ARRIVE SERVE LEAVE PRINT SIZE PEEK TICK STATS EXIT");

    }
}