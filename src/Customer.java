/**
 * Created by gprawdzik on 2016-12-05.
 */
import java.util.ArrayList;
import java.util.List;
public class Customer {
    static public ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    public Integer id = 0;
    public String name = "";
    public String address = "";
    public String state = "";
    public String primaryContact = "";
    public String domain = "";
    public Boolean enabled = true;
    public Customer() {}
    public static List<String> getEnabledCustomerNames() {
        ArrayList<String> outList = new ArrayList<String>();
        for(Customer customer : Customer.allCustomers) {
            if(customer.enabled) {
                outList.add(customer.name);
            }
        }
        return outList;
    }
    public static List<String> getEnabledCustomerStates() {
        ArrayList<String> outList = new ArrayList<String>();
        for(Customer customer : Customer.allCustomers) {
            if(customer.enabled) {
                outList.add(customer.state);
            }
        }
        return outList;
    }
    public static List<String> getEnabledCustomerPrimaryContacts() {
        ArrayList<String> outList = new ArrayList<String>();
        for(Customer customer : Customer.allCustomers) {
            if(customer.enabled) {
                outList.add(customer.primaryContact);
            }
        }
        return outList;
    }
    public static List<String> getEnabledCustomerDomains() {
        ArrayList<String> outList = new ArrayList<String>();
        for(Customer customer : Customer.allCustomers) {
            if(customer.enabled) {
                outList.add(customer.domain);
            }
        }
        return outList;
    }


    public static List<String> getEnableCustomerAddresses() {
        return Customer.getEnabledCustomerField(new CustomerAddress());
    }

    public static List<String> getEnableCustomerAddresses1() {
        return Customer.getEnabledCustomerField1(new CustromerAddress1());
    }
    public static List<String> getEnableCustomerNames1() {
        return Customer.getEnabledCustomerField1(new CustromerName1());
    }
    public static List<String> getEnableCustomerStates1() {
        return Customer.getEnabledCustomerField1(new CustromerState1());
    }
    public static List<String> getEnableCustomerPrimaryContacts1() {
        return Customer.getEnabledCustomerField1(new CustromerPrimaryContact1());
    }
    public static List<String> getEnableCustomerDomains() {
        return Customer.getEnabledCustomerField1(new CustromerDomain1());
    }


    public static List<String> getEnabledCustomerField(ConversionFunction function) {
        ArrayList<String> outList = new ArrayList<String>();
        for(Customer customer : Customer.allCustomers) {
            if(customer.enabled) {
                function.call(customer);
            }
        }
        return outList;
    }

    public static <B> List<B> getEnabledCustomerField1(Function1<Customer, B> function) {
        ArrayList<B> outList = new ArrayList<B>();
        for(Customer customer : Customer.allCustomers) {
            if(customer.enabled) {
                function.call(customer);
            }
        }
        return outList;
    }

    //typizacja interfejsu
    public interface Function1<A1, B> {
        public B call(A1 in1);
    }

    static private class CustromerAddress1 implements Function1<Customer, String> {
        public String call(Customer customer) {
            return customer.address;
        }
    }
    static private class CustromerName1 implements Function1<Customer, String> {
        public String call(Customer customer) {
            return customer.name;
        }
    }
    static private class CustromerState1 implements Function1<Customer, String> {
        public String call(Customer customer) {
            return customer.state;
        }
    }
    static private class CustromerPrimaryContact1 implements Function1<Customer, String> {
        public String call(Customer customer) {
            return customer.primaryContact;
        }
    }

    static private class CustromerDomain1 implements Function1<Customer, String> {
        public String call(Customer customer) {
            return customer.domain;
        }
    }
    static private class CustromerAsCustomer1 implements Function1<Customer, Customer> {
        public Customer call(Customer customer) {
            return customer;
        }
    }

    private interface ConversionFunction {
        public String call(Customer customer);
    }

    static private class CustomerAddress implements ConversionFunction {
        public String call(Customer customer) {
            return customer.address;
        }
    }

}