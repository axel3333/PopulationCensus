package PopulationCensus;

import java.util.*;
import java.util.stream.Collectors;

import static PopulationCensus.Education.*;
import static PopulationCensus.Sex.MAN;
import static PopulationCensus.Sex.WOMAN;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        int count = (int) persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Всего несовершеннолетних - " + count);

        List<String> result = persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getAge() < 27)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
//        System.out.println(result);

        List<Person> result2 = persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getAge() < 65)
                .filter(x -> x.getEducation() == HIGHER)
                .filter(x -> x.getSex() == MAN)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
//        System.out.println(result2);

        List<Person> result3 = persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getAge() < 60)
                .filter(x -> x.getEducation() == HIGHER)
                .filter(x -> x.getSex() == WOMAN)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(result3);
    }
}
