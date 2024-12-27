package com.generation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student
        extends Person
        implements Evaluation {
    private double average;

    private final List<Course> courses = new ArrayList<>();

    private final Map<String, Course> approvedCourses = new HashMap<>();

    private final Map<String, Double> grades = new HashMap<>();


    public Student(String id, String name, String email, Date birthDate) {
        super(id, name, email, birthDate);
    }


    public void enrollToCourse(Course course) {                                           //TODO implemented this method
        if (isAttendingCourse(course.getCode())) {
            System.out.println("You are already enrolled in this course.");
        }else {
            courses.add(course);
            System.out.println("Successfully enrolled in " + course.getName());
        }
    }


    public void registerApprovedCourse(Course course) {
        approvedCourses.put(course.getCode(), course);
    }

    public boolean isCourseApproved(String courseCode)                                    //TODO implemented this method
    {
        return approvedCourses.containsKey(courseCode);
    }

    public List<Course> findPassedCourses(Course course) {                                //TODO implemented this method
        List<Course> passedCourses = new ArrayList<>();
        for (Course enrolledCourse : courses) {
            if (isCourseApproved(enrolledCourse.getCode())) {
                passedCourses.add(enrolledCourse);
            }
        }
        return passedCourses;
    }

    public boolean isAttendingCourse(String courseCode) {                                 //TODO implemented this method
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    public void setGrade(String courseCode, double grade) {
        if (isAttendingCourse(courseCode)) {
            grades.put(courseCode, grade);
            System.out.println("Grade " + grade + " assigned to course " + courseCode);
        } else {
            System.out.println("The student is not enrolled in this course.");
        }
    }

    public Double getGrade(String courseCode) {
        return grades.get(courseCode);
    }


    public List<Course> getCourses() {
        return courses;
    }



    public List<Course> getEnrolledCourses() {
        return courses;
    }


    public List<String> getEnrolledCoursesWithGrades() {
        List<String> courseDetails = new ArrayList<>();
        for (Course course : courses) {
            Double grade = getGrade(course.getCode());
            if (grade != null) {
                courseDetails.add("Course: " + course.getName() + ", Grade: " + grade);
            } else {
                courseDetails.add("Course: " + course.getName() + ", Grade: Not graded");
            }
        }
        return courseDetails;
    }


    @Override
    public double getAverage() {
        double sum = 0;
        int count = 0;
        for (double grade : grades.values()) {
            sum += grade;
            count++;
        }
        return count > 0 ? sum / count : 0.0;
    }

    @Override
    public List<Course> getApprovedCourses() {                                              //TODO implemented this method
        return new ArrayList<>(approvedCourses.values());
    }

    @Override
    public String toString() {
        StringBuilder studentDetails = new StringBuilder();
        studentDetails.append("Student {")
                .append("ID: ").append(getId()).append(", Name: ").append(getName())
                .append(", Email: ").append(getEmail())
                .append(", Birthdate: ").append(getBirthDate()).append(", Enrolled Courses: ");
        for (String courseId : grades.keySet()) {
            studentDetails.append("\n  - Course ID: ").append(courseId)
                    .append(", Grade: ").append(grades.get(courseId));
        }
        studentDetails.append("\nOverall Average: ").append(getAverage())
                .append(" }");
        return studentDetails.toString();
    }
}
