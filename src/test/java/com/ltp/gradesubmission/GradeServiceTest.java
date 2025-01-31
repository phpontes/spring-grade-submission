package com.ltp.gradesubmission;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ltp.gradesubmission.pojo.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.GradeService;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {
    
    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    @Test
    public void getGradesFromRepoTest() {
        // Arrange
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
            new Grade("Joãozinho", "Matemática", "C"),
            new Grade("Mariazinha", "Português", "A")
        ));

        // Act
        List<Grade> result = gradeService.getGrades();

        // Assert
        assertEquals("Joãozinho", result.get(0).getName());
        assertEquals("Português", result.get(1).getSubject());
    }

    @Test
    public void gradeIndexTest() {
        // Arrange
        Grade grade = new Grade("Joãozinho", "Matemática", "C");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        // Act
        int valid = gradeService.getGradeIndex(grade.getId());
        int notFound = gradeService.getGradeIndex("123");

        // Assert
        assertEquals(0, valid);
        assertEquals(Constants.NOT_FOUND, notFound);
    }

    @Test
    public void returnGradeByIdTest() {
        // Arrange
        Grade grade = new Grade("Joãozinho", "Matemática", "C");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        // Act
        String id = grade.getId();
        Grade result = gradeService.getGradeById(id);

        // Assert
        assertEquals(grade, result);
    }

    @Test
    public void addGradeTest() {
        // Arrange
        Grade grade = new Grade("Joãozinho", "Matemática", "C");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        // Act
        Grade newGrade = new Grade("Mariazinha", "Português", "A");
        gradeService.submitGrade(newGrade);

        // Assert
        verify(gradeRepository, times(1)).addGrade(newGrade);
    }

    @Test
    public void updateGradeTest() {
        // Arrange
        Grade grade = new Grade("Joãozinho", "Matemática", "C");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        // Act
        grade.setScore("A-");
        gradeService.submitGrade(grade);

        // Assert
        verify(gradeRepository, times(1)).updateGrade(grade, 0);
    }
}
