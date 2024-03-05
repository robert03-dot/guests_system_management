import java.util.ArrayList;
import java.util.List;

public class GuestList {
    private int guest_capacity;
    private List<Guest> guestList;
    private List<Guest> waitingList;

    public GuestList(int guest_capacity) {
        this.guest_capacity = guest_capacity;
        this.guestList = new ArrayList<Guest>(this.guest_capacity);
    }
    public boolean isOnTheList(Guest guest){
        for(Guest g:guestList){
            if(g.equals(guest)){
                System.out.println("Persoana este deja inregistrata");
                return true;
            }
            if(g.getPhone_number().equals(guest.getPhone_number())){
                System.out.println("Numarul de telefon este deja inregistrat");
                return true;
            }
            if(g.getEmail().equals(guest.getEmail())){
                System.out.println("Adresa de email este deja iregistrata");
                return true;
            }
        }
        return false;
    }
    public int addGuests(Guest guest){
        if(isOnTheList(guest)){
            return -1;
        }
        this.guestList.add(guest);
        if(guestList.size()<=this.guest_capacity){
            guest.notifyGuests(-1);
            return 0;
        }
        int waitListPosition = this.guestList.size()-this.guest_capacity;
        guest.notifyGuests(waitListPosition);
        return waitListPosition;
    }
}