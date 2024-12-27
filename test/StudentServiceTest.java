import com.generation.model.Course;
import com.generation.model.Module;
import com.generation.model.Student;
import com.generation.service.StudentService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentServiceTest {

    @Test
    public void testSubscribeStudent() throws Exception {
        StudentService studentService = new StudentService();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = sdf.parse("10/10/1995");
        Student student = new Student("0101", "Gina", "gina@gina.com", birthDate);

        studentService.subscribeStudent(student);

        assertNotNull(studentService.getStudents().get("0101"));
        assertEquals("Gina", studentService.getStudents().get("0101").getName());
        assertEquals("0101", studentService.getStudents().get("0101").getId());
    }

    @Test
    public void testEnrollStudentToCourse() throws Exception {
        StudentService studentService = new StudentService();

        Module module = new Module("CS101", "Intro to Computer Science", "Basic computer science concepts");
        Course course = new Course("CS101", "Intro to Computer Science", 4, module);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = sdf.parse("10/10/1995");
        Student student = new Student("0101", "Gina", "gina@gina.com", birthDate);

        studentService.subscribeStudent(student);

        student.enrollToCourse(course);

        assertTrue(student.getCourses().contains(course));
    }
}
