package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class StudentService
{
        private final Map<String, Student> students = new HashMap<>();

    public void subscribeStudent( Student student )
    {
        students.put( student.getId(), student );
    }

    public Map<String, Student> getStudents() {
        return students;
    }

    public Student findStudent( String studentId )
    {
        if ( students.containsKey( studentId ) ) {
            return students.get( studentId );
        }
        return null;
    }

    public boolean isSubscribed( String studentId )                             // TODO IMPLEMENTED
    {
        return students.containsKey(studentId);
    }

    public void showSummary() {
        System.out.println("Student Summary:");
        for (Student student : students.values()) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName());

            List<Course> enrolledCourses = student.getEnrolledCourses();
            if (enrolledCourses.isEmpty()) {
                System.out.println("Enrolled Courses: None");
            } else {
                System.out.println("Enrolled Courses:");
                for (Course course : enrolledCourses) {
                    Double grade = student.getGrade(course.getCode());
                    String gradeDisplay = (grade != null) ? String.format("%.2f", grade) : "Not graded";
                    System.out.println("  Course: " + course.getCode() + " - " + course.getName() + ", Grade: " + gradeDisplay);
                }
            }
        }
    }

    public void enrollToCourse( String studentId, Course course )
    {
        if ( students.containsKey( studentId ) ) {
            students.get( studentId ).enrollToCourse( course );
        }
    }


}
