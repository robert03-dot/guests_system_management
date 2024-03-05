import java.util.List;

public class Guest {
    private String last_name;
    private String first_name;
    private String email;
    private String phone_number;

    public Guest(String last_name, String first_name, String email, String phone_number) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.email = email;
        this.phone_number = phone_number;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public int hashCode() {
        final int prime_number = 23;
        int result = 1;
        result = prime_number*result+((this.last_name == null) ? 0 : this.last_name.hashCode());
        result = prime_number*result+((this.first_name == null) ? 0 : this.first_name.hashCode());
        result = prime_number*result+((this.phone_number == null) ? 0 : this.phone_number.hashCode());
        result = prime_number*result+((this.email == null) ? 0 : this.email.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(obj==null){
            return false;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        Guest guestobj = (Guest) obj;
        if((this.first_name.equals(guestobj.first_name) && this.last_name.equals(guestobj.last_name)) ||
                (this.email.equals(guestobj.email)) || (this.phone_number.equals(guestobj.phone_number))){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Nume:"+last_name + " prenume:"+first_name+ " phone number:"+phone_number + " email:"+email;
    }
    public void notifyGuests(int orderNumber){
        if(orderNumber<0){
            System.out.println(last_name  + " " + first_name + " Felicitari!Locul tau la eveniment este confirmat.Te asteptam!");
        }else{
            System.out.println("Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine " + orderNumber + ".Te vom notifica daca un loc devine disponibil!");
        }
    }
    public int getRegistrationStatus(List<Guest> registeredGuests,List<Guest> waitingList){
        if (isGuestRegistered(registeredGuests, (List<Guest>) this)) {
            return -1;
        }else if(isGuestRegistered(registeredGuests, (List<Guest>) this)){
            return 0;
        }else{
            int positionInWaitingList = waitingList.indexOf(this);
            return positionInWaitingList+1;
        }
    }
    private boolean isGuestRegistered(List<Guest> guests, List<Guest> guest){
        for(Guest registeredGuest:guests){
            if(registeredGuest.equals(guest)){
                return true;
            }
        }
        return false;
    }
    public boolean personElimination(List<Guest> list){
        return list.remove(this);
    }
    public int detailsActualization(String newLastName, String newFirstName, String newEmail, String newPhoneNumber){
        if(newLastName!=null){
            this.last_name=newLastName;
        }
        if(newFirstName!=null){
            this.first_name=newFirstName;
        }
        if(newEmail!=null){
            this.email=newEmail;
        }
        if(newPhoneNumber!=null){
            this.phone_number=newPhoneNumber;
        }
        return 1;
    }
}