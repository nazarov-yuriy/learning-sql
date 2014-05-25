package ru.usb7.db.pg_test;

import java.util.Random;

/**
 * Created by firefish on 5/25/14.
 */
public class Customer {
    private static final String[] firstNames = {"Noah", "Liam", "Jacob", "Mason", "William", "Ethan", "Michael",
            "Alexander", "Jayden", "Daniel", "Elijah", "Aiden", "James", "Benjamin", "Matthew", "Jackson", "Logan",
            "David", "Anthony", "Joseph", "Joshua", "Andrew", "Lucas", "Gabriel", "Samuel", "Christopher", "John",
            "Dylan", "Isaac", "Ryan", "Nathan", "Carter", "Caleb", "Luke", "Christian", "Hunter", "Henry", "Owen",
            "Landon", "Jack", "Wyatt", "Jonathan", "Eli", "Isaiah", "Sebastian", "Jaxon", "Julian", "Brayden", "Gavin",
            "Levi", "Aaron", "Oliver", "Jordan", "Nicholas", "Evan", "Connor", "Charles", "Jeremiah", "Cameron",
            "Adrian", "Thomas", "Robert", "Tyler", "Colton", "Austin", "Jace", "Angel", "Dominic", "Josiah", "Brandon",
            "Ayden", "Kevin", "Zachary", "Parker", "Blake", "Jose", "Chase", "Grayson", "Jason", "Ian", "Bentley",
            "Adam", "Xavier", "Cooper", "Justin", "Nolan", "Hudson", "Easton", "Jase", "Carson", "Nathaniel", "Jaxson",
            "Kayden", "Brody", "Lincoln", "Luis", "Tristan", "Damian", "Camden", "Juan"
    };
    private static final String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller",
            "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson",
            "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young",
            "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson",
            "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards",
            "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey",
            "Rivera", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James",
            "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson",
            "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler",
            "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes"
    };

    public String firstName;
    public String lastName;
    public String middleName;

    public static Customer randomCustomer() {
        Customer customer = new Customer();
        Random rnd = new Random();
        customer.firstName = firstNames[rnd.nextInt(firstNames.length)];
        customer.lastName = lastNames[rnd.nextInt(lastNames.length)];
        customer.middleName = "";
        return customer;
    }
}
