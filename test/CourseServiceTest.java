import com.generation.model.Course;
import com.generation.model.Module;
import com.generation.model.Student;
import com.generation.service.CourseService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CourseServiceTest {

    @Test
    public void testRegisterCourse() {
        CourseService courseService = new CourseService();

        Module module = new Module("INTRO-CS", "Introduction to Computer Science", "Introductory module");
        Course course = new Course("INTRO-CS-8", "Computer Science Fundamentals", 9, module);

        courseService.registerCourse(course);

        Course retrievedCourse = courseService.getCourse("INTRO-CS-8");
        assertNotNull(retrievedCourse);
        assertEquals("Computer Science Fundamentals", retrievedCourse.getName());
    }

    @Test
    public void testEnrollStudent() {
        CourseService courseService = new CourseService();

        Module module = new Module("INTRO-WEB", "Web Fundamentals", "Web development introduction");
        Course course = new Course("INTRO-WEB-1", "Introduction to Web Applications", 9, module);
        Student student = new Student("S001", "Gina", "gina@gina.com", new java.util.Date());

        courseService.registerCourse(course);
        courseService.enrollStudent("INTRO-WEB-1", student);

        courseService.showEnrolledStudents("INTRO-WEB-1");


        List<Student> enrolledStudents = courseService.getEnrolledStudents("INTRO-WEB-1");
        assertTrue(enrolledStudents.contains(student));
    }

    @Test
    public void testShowSummary() {
        CourseService courseService = new CourseService();

        Module module = new Module("INTRO-CS", "Introduction to Computer Science", "Introductory module");
        Course course = new Course("INTRO-CS-1", "Introduction to Computer Science", 9, module);
        Student student = new Student("S001", "Gina", "gina@gina.com", new java.util.Date());

        courseService.registerCourse(course);
        courseService.enrollStudent("INTRO-CS-1", student);

        courseService.showSummary();
    }
}
