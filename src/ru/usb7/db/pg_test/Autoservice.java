package ru.usb7.db.pg_test;

import java.util.Random;

/**
 * Created by firefish on 5/25/14.
 */
public class Autoservice {
    String name;
    String address;

    private static final String[] streets = {
            "Main Street", "Church Street", "Main Street North", "High Street", "Main Street South", "Elm Street",
            "Washington Street", "Main Street West", "Main Street East", "Chestnut Street", "2nd Street", "Park Avenue",
            "Walnut Street", "Broad Street", "Maple Street", "Maple Avenue", "Water Street", "Center Street",
            "Oak Street", "Pine Street", "South Street", "Market Street", "North Street", "Union Street", "3rd Street",
            "River Road", "School Street", "Court Street", "Prospect Street", "Washington Avenue", "Spring Street",
            "Cherry Street", "Park Street", "Central Avenue", "Front Street", "Cedar Street", "Jefferson Street",
            "4th Street", "Franklin Street", "Mill Street", "1st Street", "Highland Avenue", "West Street",
            "Bridge Street", "Pearl Street", "Spruce Street", "Jackson Street", "Pleasant Street", "5th Street",
            "Academy Street", "Madison Avenue", "Pennsylvania Avenue", "Madison Street", "Park Place", "State Street",
            "Adams Street", "East Street", "Locust Street", "Ridge Road", "Church Road", "Elizabeth Street",
            "Green Street", "Grove Street", "Hill Street", "Lincoln Avenue", "Jefferson Avenue", "Route 30",
            "Vine Street", "11th Street", "12th Street", "Dogwood Drive", "Liberty Street", "River Street", "Route 1",
            "2nd Avenue", "Hillside Avenue", "Lincoln Street", "10th Street", "4th Street West", "5th Avenue",
            "6th Street", "7th Street", "Clinton Street", "Delaware Avenue", "Meadow Lane", "Winding Way",
            "3rd Street West", "9th Street", "Broadway", "Monroe Street", "New Street", "Railroad Street", "Route 6",
            "3rd Avenue", "Beech Street", "Cherry Lane", "Colonial Drive", "Hickory Lane", "Valley Road", "13th Street",
            "1st Avenue", "2nd Street West", "Brookside Drive", "Charles Street", "Front Street North", "Lake Street",
            "Laurel Lane", "Oak Lane", "Poplar Street", "Primrose Lane", "Railroad Avenue", "Summit Avenue",
            "Virginia Avenue", "Williams Street", "Willow Street", "Woodland Drive", "4th Avenue", "8th Street",
            "Division Street", "Fairway Drive", "King Street", "Mill Road", "Route 29", "Smith Street",
            "4th Street North", "7th Avenue", "Arch Street", "Cambridge Court", "Canterbury Court", "College Street",
            "Creek Road", "Harrison Street", "Heather Lane", "Highland Drive", "Holly Drive", "Mulberry Street",
            "Myrtle Avenue", "Penn Street", "Prospect Avenue", "Riverside Drive", "Route 32", "Sunset Drive",
            "5th Street North", "Bank Street", "Canal Street", "Cedar Lane", "Circle Drive", "Colonial Avenue",
            "Dogwood Lane", "Durham Road", "Glenwood Avenue", "Grant Avenue", "Hamilton Street", "John Street",
            "Lafayette Avenue", "Maple Lane", "Route 10", "Surrey Lane", "Valley View Drive", "5th Street West",
            "6th Street North", "6th Street West", "Beechwood Drive", "Buckingham Drive", "Cedar Avenue",
            "Clark Street", "Deerfield Drive", "Devon Road", "Elm Avenue", "Essex Court", "Fairview Avenue",
            "Forest Drive", "Franklin Court", "Garden Street", "George Street", "Henry Street", "James Street",
            "Lakeview Drive", "Laurel Street", "Lilac Lane", "Oak Avenue", "Oxford Court", "Park Drive", "Ridge Avenue",
            "Route 11", "Route 70", "Walnut Avenue", "Warren Street", "Woodland Avenue", "York Road", "2nd Street East",
            "2nd Street North", "6th Avenue", "Aspen Court", "Atlantic Avenue", "Church Street North", "Cottage Street",
            "Franklin Avenue", "Garfield Avenue", "Grant Street", "Hillside Drive", "Hilltop Road", "Lafayette Street",
            "Linden Street", "Locust Lane", "Magnolia Avenue", "Olive Street", "Orange Street", "Orchard Street",
            "Race Street", "Route 4", "Route 41", "Route 7", "Route 9", "Sycamore Drive", "Taylor Street",
            "Windsor Court", "3rd Street East", "3rd Street North", "5th Street South", "Arlington Avenue",
            "Belmont Avenue", "Berkshire Drive", "Cambridge Drive", "Cardinal Drive", "Carriage Drive", "Cedar Court",
            "Chapel Street", "Chestnut Avenue", "Clay Street", "College Avenue", "Creekside Drive", "Crescent Street",
            "Cypress Court", "Devonshire Drive", "Fawn Lane", "Forest Avenue", "Front Street South", "Fulton Street",
            "Grove Avenue", "Hickory Street", "Hillcrest Avenue", "Inverness Drive", "Jackson Avenue", "Laurel Drive",
            "Magnolia Drive", "Mechanic Street", "Pin Oak Drive", "Rose Street", "Route 20", "Route 202", "Street Road",
            "Summit Street", "Tanglewood Drive", "Wall Street", "William Street", "Windsor Drive", "York Street",
            "8th Avenue", "8th Street South", "8th Street West", "Ann Street", "Brown Street", "Buttonwood Drive",
            "Cambridge Road", "Canterbury Drive", "Cemetery Road", "Church Street South", "Cobblestone Court",
            "Columbia Street", "Cooper Street", "Depot Street", "Edgewood Drive", "Elmwood Avenue", "Evergreen Lane",
            "Fieldstone Drive", "Forest Street", "Hawthorne Lane", "Heritage Drive", "Hillcrest Drive", "Holly Court",
            "Howard Street", "Jefferson Court", "Lantern Lane", "Magnolia Court", "Meadow Street", "Mulberry Lane",
            "Queen Street", "Redwood Drive", "Ridge Street", "Route 100", "Route 44", "Sherman Street",
            "Sherwood Drive", "Strawberry Lane", "Sunset Avenue", "Valley Drive", "Westminster Drive", "Wood Street",
            "12th Street East", "14th Street", "9th Street West", "Augusta Drive", "Briarwood Drive", "Bridle Lane",
            "Brook Lane", "Canterbury Road", "Catherine Street", "Country Club Drive", "Country Club Road",
            "Country Lane", "Durham Court", "Eagle Road", "Eagle Street", "Edgewood Road", "Euclid Avenue",
            "Evergreen Drive", "Grand Avenue", "Hamilton Road", "Harrison Avenue", "Hartford Road", "Hawthorne Avenue",
            "Heather Court", "Ivy Court", "Ivy Lane", "Lake Avenue", "Lawrence Street", "Lexington Drive",
            "Madison Court", "Morris Street", "Mulberry Court", "Myrtle Street", "North Avenue", "Old York Road",
            "Orchard Avenue", "Orchard Lane", "Oxford Road", "Pheasant Run", "Rosewood Drive", "Route 17", "Route 27",
            "Sheffield Drive", "Somerset Drive", "Sycamore Lane", "Sycamore Street", "Valley View Road",
            "Victoria Court", "Warren Avenue", "West Avenue", "Woodland Road", "4th Street South", "5th Street East",
            "Adams Avenue", "Amherst Street", "Andover Court", "Ashley Court", "Aspen Drive", "B Street", "Bay Street",
            "Bayberry Drive", "Brandywine Drive", "Briarwood Court", "Bridle Court", "Broad Street West",
            "Cleveland Avenue", "Cleveland Street", "Cottonwood Drive", "Cross Street", "Devon Court", "East Avenue",
            "Elm Court", "Fairview Road", "Fawn Court", "Glenwood Drive", "Grandview Drive", "Hanover Court",
            "Hemlock Lane", "Highland Court", "Holly Lane", "Homestead Drive", "Hudson Street", "Jones Street",
            "Laurel Avenue", "Lexington Court", "Lincoln Drive", "Linda Lane", "Linden Avenue", "Maiden Lane",
            "Manor Drive", "Marshall Street", "Monroe Drive", "Overlook Circle", "Parker Street", "Roberts Road",
            "Roosevelt Avenue", "Route 13", "Route 2", "Route 5", "Route 64", "Schoolhouse Lane", "Shady Lane",
            "Spruce Avenue", "State Street East", "Sunset Lane", "Virginia Street", "White Street", "Willow Avenue",
            "Willow Drive", "Willow Lane"
    };

    private static final String[] cities = {
            "Sitka, Alaska", "Juneau, Alaska", "Wrangell, Alaska", "Anchorage, Alaska", "Jacksonville, Florida",
            "Anaconda, Montana", "Butte, Montana", "Oklahoma City, Oklahoma", "Houston, Texas", "Phoenix, Arizona",
            "Nashville, Tennessee", "Los Angeles, California", "San Antonio, Texas", "Suffolk, Virginia",
            "Buckeye, Arizona", "Indianapolis, Indiana", "Chesapeake, Virginia", "Dallas, Texas", "Fort Worth, Texas",
            "Louisville, Kentucky", "San Diego, California", "Memphis, Tennessee", "Kansas City, Missouri",
            "New York City, New York", "Augusta, Georgia", "Austin, Texas", "Charlotte, North Carolina",
            "Lexington, Kentucky", "El Paso, Texas", "Virginia Beach, Virginia", "Cusseta, Georgia",
            "Chicago, Illinois", "Tucson, Arizona", "Columbus, Ohio", "Columbus, Georgia", "Valdez, Alaska",
            "Preston, Georgia", "Huntsville, Alabama", "Boulder City, Nevada", "California City, California",
            "Tulsa, Oklahoma", "Colorado Springs, Colorado", "Goodyear, Arizona", "Albuquerque, New Mexico",
            "Scottsdale, Arizona", "Hibbing, Minnesota", "Norman, Oklahoma", "San Jose, California", "Peoria, Arizona",
            "New Orleans, Louisiana", "Corpus Christi, Texas", "Montgomery, Alabama", "Wichita, Kansas",
            "Aurora, Colorado", "Denver, Colorado", "Sierra Vista, Arizona", "Georgetown, Georgia",
            "Birmingham, Alabama", "Fayetteville, North Carolina", "Carson City, Nevada", "Raleigh, North Carolina",
            "Bakersfield, California", "Mobile, Alabama", "Detroit, Michigan", "Bunnell, Florida", "Mesa, Arizona",
            "Las Vegas, Nevada", "Chattanooga, Tennessee", "Philadelphia, Pennsylvania", "Portland, Oregon",
            "Atlanta, Georgia", "Winston-Salem, North Carolina", "Brownsville, Texas", "Columbia, South Carolina",
            "Lynchburg, Tennessee", "Athens, Georgia", "Little Rock, Arkansas", "Omaha, Nebraska", "Lubbock, Texas",
            "Tampa, Florida", "Unalaska, Alaska", "Orlando, Florida", "Salt Lake City, Utah",
            "Columbia, South Carolina", "Yuma, Arizona", "Babbitt, Minnesota", "Cape Coral, Florida", "Abilene, Texas",
            "Palmdale, California", "Jackson, Mississippi", "Greensboro, North Carolina", "Fresno, California",
            "Shreveport, Louisiana", "St. Marys, Pennsylvania", "Sacramento, California", "Charleston, South Carolina",
            "Nightmute, Alaska", "Plymouth, Massachusetts", "Milwaukee, Wisconsin", "Arlington, Texas",
            "Tallahassee, Florida", "Clarksville, Tennessee", "Durham, North Carolina", "Palm Springs, California",
            "Lancaster, California", "Knoxville, Tennessee", "Amarillo, Texas", "Dothan, Alabama",
            "Oak Ridge, Tennessee", "Edmond, Oklahoma", "Beaumont, Texas", "Waco, Texas", "Seattle, Washington",
            "Port Arthur, Texas", "Baltimore, Maryland", "Toledo, Ohio", "Kansas City, Kansas", "El Reno, Oklahoma",
            "Henderson, Nevada", "Jonesboro, Arkansas", "Ellsworth, Maine", "Caribou, Maine", "Laredo, Texas",
            "Fort Wayne, Indiana", "North Las Vegas, Nevada", "Independence, Missouri", "Riverside, California",
            "Cincinnati, Ohio", "Las Cruces, New Mexico", "Cleveland, Ohio", "Baton Rouge, Louisiana",
            "Fremont, California", "Presque Isle, Maine", "Des Moines, Iowa", "Port St. Lucie, Florida",
            "Lawton, Oklahoma", "Rome, New York", "North Port, Florida", "Savannah, Georgia", "Lincoln, Nebraska",
            "Enid, Oklahoma", "Rio Rancho, New Mexico", "Apple Valley, California", "Springfield, Missouri",
            "Victorville, California", "Marana, Arizona", "Plano, Texas", "Grand Prairie, Texas", "Wichita Falls, Texas"
    };

    private static final String[] templates = {
            "{fname}'s Auto Service", "{fname}'s Repair Center", "{fname}'s Garage", "{fname}'s Car Care",
            "{fname}'s Auto & Tire"
    };

    public static Autoservice RandomAutoservice() {
        Autoservice autoservice = new Autoservice();
        autoservice.name = "";
        Random rnd = new Random();
        autoservice.name = templates[rnd.nextInt(templates.length)];
        if (rnd.nextInt(2) > 0) {
            autoservice.name += " Inc";
        }
        Customer customer = Customer.randomCustomer();
        autoservice.name = autoservice.name.replace("{fname}", customer.firstName);

        autoservice.address = ""+( rnd.nextInt(1000000) % (20+rnd.nextInt(200)) )+" ";
        autoservice.address += streets[ rnd.nextInt(streets.length) ] + ", ";
        autoservice.address += cities[ rnd.nextInt(cities.length) ];

        return autoservice;
    }
}
