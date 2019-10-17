package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, secure = false)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private ArrayList<Course> courseList = new ArrayList<>();

    @Before
    public void setUp() throws Exception
    {
        Instructor instructor1 = new Instructor("John");
        instructor1.setInstructid(1);
        Instructor instructor2 = new Instructor("Dustin");
        instructor2.setInstructid(2);
        Instructor instructor3 = new Instructor("Dan");
        instructor3.setInstructid(3);

        Course course1 = new Course("Java", instructor1);
        course1.setCourseid(4);
        Course course2 = new Course("React", instructor2);
        course2.setCourseid(5);
        Course course3 = new Course("UI", instructor3);
        course3.setCourseid(6);

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
    }

    @After
    public void B_tearDown() throws Exception {

    }

    @Test
    public void listAllCourses() throws Exception {
        // list all courses test

        String apiUrl = "/courses/courses";


        System.out.println("SIZE: " + courseList.size());
        Mockito.when(courseService.findAll()).thenReturn(courseList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        // the following performs a controller call
        System.out.println(rb);
        MvcResult r = mockMvc.perform(rb).andReturn();
        System.out.println("GET endpoint accessed?");

        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(courseList);

        assertEquals("Rest API Returns List", er, tr);
    }

    // Add a unit test for addNewCourse
    @Test
    public void addNewCourse() throws Exception
    {
        String apiUrl = "/courses/course/add";

        String courseName = "Java";

        Instructor i1 = new Instructor("John");

        Course newCourse = new Course(courseName, i1);

        ObjectMapper mapper = new ObjectMapper();

        String courseString = mapper.writeValueAsString(newCourse);

        Mockito.when(courseService.save(any(Course.class))).thenReturn(newCourse);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(courseString);

        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }



}

