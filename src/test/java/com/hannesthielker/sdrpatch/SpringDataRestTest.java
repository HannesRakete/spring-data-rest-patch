package com.hannesthielker.sdrpatch;

import com.hannesthielker.sdrpatch.entities.Course;
import com.hannesthielker.sdrpatch.entities.Pupil;
import com.hannesthielker.sdrpatch.entities.SchoolClass;
import com.hannesthielker.sdrpatch.repositories.CourseRepository;
import com.hannesthielker.sdrpatch.repositories.SchoolClassRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringDataRestTest {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    SchoolClassRepository schoolClassRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        // courses
        Course dartCourse = new Course();
        dartCourse.setName("dart");

        Course tennisCourse = new Course();
        tennisCourse.setName("tennis");

        Iterable<Course> courses = courseRepository.saveAll(List.of(dartCourse, tennisCourse));
        for (Course course : courses) {
            System.out.println("course: " + course.getName());
        }
        System.out.println("courses: " + courses);

        // class and pupil
        Pupil pupil = new Pupil();
        pupil.setName("pupil");
        pupil.setCourse(dartCourse);

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setName("class");

        schoolClass.setPupils(List.of(pupil));

        SchoolClass savedSchoolClass = schoolClassRepository.save(schoolClass);
        System.out.println(savedSchoolClass);
    }

    @Test
    @SneakyThrows
    void test() {
        printSchoolClass();

        ResultActions patchResult = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .patch("http://localhost:8080/schoolClasses/1")
                                .content("""
                                                 {
                                                   "pupils" : [ {
                                                     "name" : "pupil",
                                                     "course" :"http://localhost:8080/courses/2"
                                                   } ]
                                                 }""")
                );

        printSchoolClass();


        // this assert is successful for spring-data-rest in spring boot version 3.2.7 but not in 3.3.2
        assertThat(
                schoolClassRepository.findById(1L).orElseThrow().getPupils().getFirst().getCourse().getId()
        )
                .isEqualTo(2L);
    }

    @SneakyThrows
    void printSchoolClass() {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("http://localhost:8080/schoolClasses/1"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }


}
