package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;

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
public class CourseServiceImplTest {
    @Autowired
    private CourseService courseService;

    @Before
    public void A_setUp()
    {
    }

    @After
    public void B_tearDown()
    {
    }

    // findCourseById
    @Test
    public void findCourseById() {
        assertEquals("Data Science", courseService.findCourseById(1).getCoursename());
    }

    @Test (expected = EntityNotFoundException.class)
    public void FdeleteNotFound()
    {
        courseService.delete(100);
        assertEquals(2, courseService.findAll().size());
    }


    @Test
    public void GdeleteFound()
    {
        courseService.delete(10);
        assertEquals(2, courseService.findAll().size());
    }
}
