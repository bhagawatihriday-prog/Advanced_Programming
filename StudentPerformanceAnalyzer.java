import java.util.*;
import java.util.stream.Collectors;

class Student {

    private int id;
    private String name;
    private List<String> courses;
    private Map<String, Integer> scores;

    public Student(int id, String name,
                    List<String> courses,
                    Map<String, Integer> scores) {

        this.id = id;
        this.name = name;
        this.courses = courses;
        this.scores = scores;
    }

    public double getAverageScore() {
        return scores.values()
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    public List<String> getCourses() {
        return courses;
    }

    public Map<String, Integer> getScores() {
        return scores;
    }

    @Override
    public String toString() {
        return id + " - " + name + " | Avg: " +
                String.format("%.2f", getAverageScore());
    }
}

public class StudentPerformanceAnalyzer {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        List<String> courses = Arrays.asList("DAA", "CAO", "TOC", "AP");

        students.add(createStudent(24001, "Arjun", courses, 40,35,47,58));
        students.add(createStudent(24002, "Rohan", courses, 89,91,95,85));
        students.add(createStudent(24004, "Karan", courses, 54,36,84,90));
        students.add(createStudent(24006, "Aisha", courses, 74,64,36,87));
        students.add(createStudent(24007, "Neha", courses, 56,79,67,80));
        students.add(createStudent(24008, "Vikram", courses, 43,73,56,48));
        students.add(createStudent(24009, "Sneha", courses, 90,92,87,65));
        students.add(createStudent(24011, "Aditya", courses, 68,72,70,75));
        students.add(createStudent(24013, "Priya", courses, 81,77,83,79));
        students.add(createStudent(24016, "Rahul", courses, 59,61,65,63));
        students.add(createStudent(24017, "Ananya", courses, 72,69,74,71));
        students.add(createStudent(24018, "Siddharth", courses, 66,58,60,64));
        students.add(createStudent(24019, "Meera", courses, 85,88,90,92));
        students.add(createStudent(24021, "Yash", courses, 49,55,52,60));
        students.add(createStudent(24022, "Ishita", courses, 78,82,80,76));

        long startSort = System.nanoTime();
        List<Student> topStudents = getTopNStudents(students, 3);
        long endSort = System.nanoTime();

        System.out.println("Top 3 Students:");
        topStudents.forEach(System.out::println);

        System.out.println("Sorting Time: " + (endSort - startSort) + " ns");

        long startAvg = System.nanoTime();
        Map<String, Double> averages = getAverageScorePerCourse(students);
        long endAvg = System.nanoTime();

        System.out.println("\nCourse Averages:");
        averages.forEach((c, a) -> System.out.printf("%s -> %.2f\n", c, a));

        System.out.println("Average Calculation Time: " + (endAvg - startAvg) + " ns");

        System.out.println("\nUnique Courses:");
        System.out.println(getAllUniqueCourses(students));
    }

    private static Student createStudent(int id, String name,
                                        List<String> courses,
                                        int daa, int cao, int toc, int ap) {

        Map<String, Integer> scores = new HashMap<>();
        scores.put("DAA", daa);
        scores.put("CAO", cao);
        scores.put("TOC", toc);
        scores.put("AP", ap);

        return new Student(id, name, new ArrayList<>(courses), scores);
    }

    public static List<Student> getTopNStudents(List<Student> students, int n) {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverageScore).reversed())
                .limit(Math.min(n, students.size()))
                .collect(Collectors.toList());
    }

    public static Map<String, Double> getAverageScorePerCourse(List<Student> students) {

        Map<String, Double> averages = new HashMap<>();
        Set<String> courses = getAllUniqueCourses(students);

        for (String course : courses) {

            double avg = students.stream()
                    .mapToInt(s -> s.getScores().getOrDefault(course, 0))
                    .average()
                    .orElse(0.0);

            averages.put(course, avg);
        }

        return averages;
    }

    public static Set<String> getAllUniqueCourses(List<Student> students) {
        return students.stream()
                .flatMap(s -> s.getCourses().stream())
                .collect(Collectors.toCollection(HashSet::new));
    }
}