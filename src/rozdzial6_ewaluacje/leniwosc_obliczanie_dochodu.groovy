package rozdzial6_ewaluacje

class Customer2 {
    final Integer id
    final Boolean enabled
    final List<Double> contracts
    @Lazy volatile Double revenue = calculateRevenue(this.contracts)
    static def calculateRevenue(contracts) {
        Double sum = 0.0
        for(Double contract : contracts) {
            sum += contract
        }
        sum
    }
    public Customer2(id, enabled, contracts) {
        this.id = id
        this.enabled = enabled
        this.contracts = contracts
    }
}
class CustomerContainer2 {
    public List<Customer2> customers = []
    public List<Customer2> onlyEnabled = []
    public CustomerContainer2() { this([]) }
    public CustomerContainer2(customers) {
        this.customers = customers
        this.onlyEnabled = customers.findAll { customer -> customer.enabled }
    }
    def addCustomer(c) {
        new CustomerContainer(customers.plus(customers.size(), [c]))
    }
    def removeCustomer(c) {
        new CustomerContainer(customers.findAll { customer -> customer.id != c.id })
    }
}
def cc = new CustomerContainer2()
cc = cc.addCustomer(new Customer2(1, true, [100.0, 200.0, 300.0]))
cc = cc.addCustomer(new Customer2(2, false, [100.0, 150.0, 500.0]))
println(cc.customers)
Double sum = 0.0
for(Customer2 customer : cc.onlyEnabled) {
    sum += customer.revenue
}
println("Dochody z aktywnych klientów: ${sum}")