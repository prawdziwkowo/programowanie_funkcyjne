package rozdzial5_rekurencja

//import rozdzial2.Closure

def suma_rekurencyjna(x) {
    if (x <= 0) {
        return 0
    }else {
        return x + suma_rekurencyjna(x - 1)
    }
}

println suma_rekurencyjna(10)


def sumaRekurencyjnaTrampoline = null
sumaRekurencyjnaTrampoline = {Integer x, Integer sum = 0 ->
    if (x <= 0) {
        return sum
    }else {
        return sumaRekurencyjnaTrampoline.trampoline(x - 1, sum + x)
    }
}.trampoline()

println sumaRekurencyjnaTrampoline(10)

def <T> List<T> Filter_nierekurencyjny(List<T> list, Closure cls) {
    ArrayList<T> out = new ArrayList<T>()
    for(T obj : list) {
        if(cls(obj)) {
            out.add(obj)
        }
    }
    return out
}

def <T> List<T> Filter(List<T> list, Closure cls) {
    if (list.isEmpty()) {
        return []
    }
    //jeżeli mamy listę, to head to jest głowa (pierwszy element)
    //ogon to jest reszta, czyli przekazujemy bez pierwszego argumentu
    return (cls(list.head()) ? [list.head()] : []) + Filter(list.tail(), cls)
}

//groovy nie robi optymalizaji dla rekurencji ogonowej
//Przykład rekurencji ogonowej
//element dodawany na koncu listy, a nie odwrotnie jak w powyzszym przypadku
def <T> List<T> Filter_tail_call(List<T> list, List<T> output, cls) {
    if (list.isEmpty()) {
        return output
    }else {
        return Filter_tail_call(list.tail(), cls(list.head()) ? output + [list.head()] : output, cls)
    }
}

//zoptymalizowane dla rekurencji ogonowej
//trampoline
ListFilterTailCallTrampoline = {List list, List output, cls ->
    if (list.isEmty()) {
        return output
    }else {
        return ListFilterTailCallTrampoline.trampoline(list.tail(), cls(list.head()) ? output + [list.head()] : output, cls)
    }
}.trampoline()

