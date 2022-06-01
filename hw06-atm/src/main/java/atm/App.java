package atm;

public class App {
    public static void main(String[] args) {
        ATM atm = IoC.getATM();

        System.out.println(atm.getDescription());

        System.out.println("-------");
        var stack = atm.issue(100);
        if(stack.isEmpty()) {
            System.out.println("not issued 100");
        } else {
            System.out.println("issued " + stack.get() );
        }

        System.out.println("-------");

        stack = atm.issue(1650);
        if(stack.isEmpty()) {
            System.out.println("not issued 1650");
        } else {
            System.out.println("issued " + stack.get() );
        }

        System.out.println(atm.getDescription());

        System.out.println("-------");

        stack = atm.issue(10040);
        if(stack.isEmpty()) {
            System.out.println("not issued 10040");
        } else {
            System.out.println("issued " + stack.get() );
        }
        System.out.println(atm.getDescription());

        System.out.println("-------");

        stack = atm.issue(10050);
        if(stack.isEmpty()) {
            System.out.println("not issued 10050");
        } else {
            System.out.println("issued " + stack.get() );
        }
        System.out.println(atm.getDescription());

        System.out.println("-------");

        stack = atm.issue(18700);
        if(stack.isEmpty()) {
            System.out.println("not issued 18700");
        } else {
            System.out.println("issued " + stack.get() );
        }
        System.out.println(atm.getDescription());
    }
}
