package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.persistence.EntityNotFoundException;

import static junit.framework.TestCase.assertEquals;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceImplTest
{

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @Before
    public void setUp()
    {
    }
    @After
    public void tearDown()
    {
    }

    // unit test findCourseById
    @Test
    public void testFindCourseById()
    {
        assertEquals("Data Science", courseService.findCourseById(1).getCoursename());
    }

    // unit test deleteNotFound
    @Test (expected = EntityNotFoundException.class)
    public void deleteNotFound()
    {
        courseService.delete(100);
        assertEquals(6, courseService.findAll().size());
    }

    // unit test deleteFound
    @Test
    public void deleteFound()
    {
        courseService.delete(2);
        assertEquals(5, courseService.findAll().size());
    }

    // unit test save
    @Test
    public void save()
    {
        String courseName = "Java";
        Instructor i1 = instructorService.findInstructorById(1);
        Course newCourse = new Course(courseName, i1);

        Course returnCourse = courseService.save(newCourse);
        assertNotNull(returnCourse);

        Course foundCourse = courseService.findCourseById(returnCourse.getCourseid());
        assertEquals(foundCourse.getCoursename(), returnCourse.getCoursename());
    }
}
