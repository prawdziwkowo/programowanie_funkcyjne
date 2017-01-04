package rozdzial4

class Foo {
    String str
}

def f = new Foo(str: "a")

def func(obj) {
    obj.str = "b"
}

println f.str
func(f)
println f.str