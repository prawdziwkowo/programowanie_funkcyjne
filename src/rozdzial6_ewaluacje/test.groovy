package rozdzial6_ewaluacje

//pamiętać zmienna leniwa jest ewaluowana tylko raz

class TestClass {
    def all = [0, 1, 2, 3, 4, 5, 6]
    @Lazy def odd = all.findAll{num -> println("test"); num%2 == 1;}
}

tc = new TestClass()
println("Po wywoalniu konstruktora")
println(tc.odd)
tc.all = [1, 2, 3]
println(tc.odd)
