package rozdzial4

import java.util.ArrayList;
import java.util.List;

public class Customer {

    static public List<Customer> allCustomers = new ArrayList<Customer>();
    public final Integer id = 0;
    public final String name = "";
    public final String state = "";
    public final String domain = "";
    public final Boolean enabled = true;
    public final Contract contract = null;
    public final List<Contact> contacts = new ArrayList<Contact>();

    public Customer(Integer id,
                    String name,
                    String state,
                    String domain,
                    Boolean enabled,
                    Contract contract,
                    List<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.domain = domain;
        this.enabled = enabled;
        this.contract = contract;
        this.contacts = contacts;
    }



    static def EnabledCustomer = { customer -> customer.enabled == true }
    static def DisabledCustomer = { customer -> customer.enabled == false }


    public static void sendEnabledCustomersEmails(String msg) {
        Customer.allCustomers.findAll {
            customer -> customer.enabled && customer.contract.enabled
        }.each {
            customer -> customer.contacts.findAll{
                contact -> contact.enabled
            }.each {
                contact -> contact.sendMail(msg)
            }
        }
    }

    public static void sendEnabledCustomersEmailsVerClosure(String msg) {
       Customer.eachEnabledContact {contact ->
           contact.sendEmail(msg)
       }
    }
    public static void eachEnabledContact(Closure cls) {
        Customer.allCustomers.findAll {
            customer ->
            customer.enabled && customer.contract.enabled
        }.each { customer ->
            customer.contacts.each(cls)
        }
    }


    public static List<Customer> setContractForCustomerList(List<Integer>ids, Boolean status) {
        Customer.updateContractForCustomerList(ids, {contract ->
            new Contract(contract.begin_date, contract.end_date, status)
        })
    }


    public static List<Customer> updateContractForCustomerList(List<Integer>ids, Closure cls) {
        Customer.allCustomers.collect{customer ->
            if (ids.indexOf(customer.id) >= 0) {
                new Customer(
                        customer.id,
                        customer.name,
                        customer.state,
                        customer.domain,
                        customer.enabled,
                        cls(customer.contract),
                        customer.contacts
                )
            }else {
                customer
            }
        }
    }

    public static List<Customer> updateContact(Integer customer_id, Integer contact_id, Closure cls) {
        Customer.allCustomers.collect{customer ->
            if (customer.id == customer_id) {
                new Customer(
                        customer.id,
                        customer.name,
                        customer.state,
                        customer.domain,
                        customer.enabled,
                        customer.contract,
                        customer.contacts.collect{contact->
                            if (contact.contact_id == contact_id) {
                                cls(contact)
                            }else {
                                contact
                            }
                        }
                )
            }else {
                customer
            }
        }
    }
}