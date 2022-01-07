package dtu.helloservice;
import lombok.NoArgsConstructor;

/**
 * Class representing a person
 */
@NoArgsConstructor
public class Person {

        String name, address;

        public Person(String name, String address){
            this.name = name;
            this.address = address;
        }

        public void setAddress(String address) {this.address = address;}

        public void setName(String name) {this.name = name;}

        public String getAddress() {return address;}

        public String getName() {return name;}

}
