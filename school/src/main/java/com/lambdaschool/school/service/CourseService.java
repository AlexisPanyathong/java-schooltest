package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.view.CountStudentsInCourses;

import java.util.ArrayList;

public interface CourseService
{
    ArrayList<Course> findAll();

    // Write a unit test for CourseServiceImpl findCourseById
    Course findCourseById(long id);

    Course findCourseByName(String name);

    ArrayList<CountStudentsInCourses> getCountStudentsInCourse();

    void delete(long id);
}
