/**
 * Created by gprawdzik on 2016-12-05.
 */
import java.util.ArrayList;
import java.util.List;
public class CustomerWithLambda {
    static public ArrayList<CustomerWithLambda> allCustomers = new ArrayList<CustomerWithLambda>();
    public Integer id = 0;
    public String name = "";
    public String address = "";
    public String state = "";
    public String primaryContact = "";
    public String domain = "";
    public Boolean enabled = true;
    public CustomerWithLambda() {}

    public static List<String> getEnableCustomerAddresses() {
        return CustomerWithLambda.getEnabledCustomerField(new Function1<CustomerWithLambda, String>(){
            public String call(CustomerWithLambda customer) {
                return customer.address;
            }
        });
    }

    public static List<String> getEnableCustomerBoosEmail() {
        return CustomerWithLambda.getEnabledCustomerField(new Function1<CustomerWithLambda, String>(){
            public String call(CustomerWithLambda customer) {
                return "boss@" + customer.domain;
            }
        });
    }


    public interface Function1<A1, B> {
        public B call(A1 in1);
    }

    public static <B> List<B> getEnabledCustomerField(Function1<CustomerWithLambda,B> func) {
        ArrayList<B> outList = new ArrayList<B>();
        for(CustomerWithLambda customer : CustomerWithLambda.allCustomers) {
            if(customer.enabled) {
                outList.add(func.call(customer));
            }
        }
        return outList;
    }

}