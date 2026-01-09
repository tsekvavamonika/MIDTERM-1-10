import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class CalendarApp {
    public static void main(String[] args) {

        ArrayList<Event> events = new ArrayList<>();
        Scanner myScanner = new Scanner(System.in);
        int choice;
        System.out.println("1 - დამატება, 2 - ძიება, 3 - სტატუსი, 4 - ყველა, 0 - გასვლა");

        while (true) {
            choice = myScanner.nextInt();
            myScanner.nextLine();

            if (choice == 0) {
                break;
            } else if (choice == 1) {
                System.out.println("შეიყვანეთ ივენთის სახელი: ");
                String name = myScanner.nextLine();

                System.out.println("შეიყვანეთ ივენთის აღწერა: ");
                String description = myScanner.nextLine();

                LocalDate date = null;

                while (date == null) {
                    System.out.print("შეიყვანეთ თარიღი (yyyy-MM-dd): ");
                    String dateInput = myScanner.nextLine();

                    try {
                        date = LocalDate.parse(dateInput);
                    } catch (Exception e) {
                        System.out.println("არასწორი ფორმატი! სცადეთ თავიდან.");
                    }
                }

                Event event = new Event(name, description, date, false);
                events.add(event);
                System.out.println("ივენთი წარმატებით დაემატა");


            } else if (choice == 2) {
                System.out.println("ძიება");

                LocalDate searchDate = null;

                while (searchDate == null) {
                    System.out.print("შეიყვანეთ ძებნის თარიღი (yyyy-MM-dd): ");
                    String dateInput = myScanner.nextLine();
                    try {
                        searchDate = LocalDate.parse(dateInput);
                    } catch (Exception e) {
                        System.out.println("არასწორი ფორმატი! სცადეთ თავიდან.");
                    }
                }

                boolean found = false;
                for (Event event : events) {
                    if (event.date.equals(searchDate)) {
                        String status = event.isDone() ? "დასრულებული" : "არ არის დასრულებული";
                        System.out.println("სახელი: " + event.name + ", აღწერა: " + event.description + " [" + status + "]");
                        found = true;
                    }

                }
                if (!found) {
                    System.out.println("ივენთები ამ თარიღით არ მოიძებნა");
                }

            } else if (choice == 3) {
                System.out.println("სტატუსი");
                for (int i = 0; i < events.size(); i++) {
                    Event event = events.get(i);
                    String status = event.isDone() ? "დასრულებული" : "არ არის დასრულებული";
                    System.out.println(( i +1) +". " + event.name + " - " + event.description + " [" + status + "]");
                }

                System.out.print("აირჩიეთ ივენთი (ნომრით) რომ შეცვალოთ სტატუსი : ");
                int eventNumber = myScanner.nextInt();
                myScanner.nextLine();

                if (eventNumber >= 1 && eventNumber <= events.size()){
                    Event selectedEvent = events.get(eventNumber - 1);
                    selectedEvent.setDone(true);
                    System.out.println("სტატუსი შეიცვალა: " + selectedEvent.name);

                } else{
                    System.out.println("არასწორი ნომერი!");
                }

            } else if (choice == 4) {
                if (events.isEmpty()) {
                    System.out.println("ივენთები არ არის დამატებული.");
                } else {
                    events.sort(Comparator.comparing(Event::getDate));
                    System.out.println("ყველა ივენთი (თარიღი ზრდადობით): ");
                    for (int i = 0; i < events.size(); i++) {
                        Event event = events.get(i);
                        String status = event.isDone() ? "დასრულებული" : "არ არის დასრულებული";
                        System.out.println((i + 1) + ". " + event.getDate() + " - " + event.name + " [" + status + "]");
                    }
                }
            } else {
                System.out.println("არასწორი არჩევანი");
            }
        }
        myScanner.close();
    }
}
