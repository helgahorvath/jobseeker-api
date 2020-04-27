package com.github.helgahorvath.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PositionTest {

  private Validator validator;

  @Before
  public void setup() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Test
  public void testJobTitleLengthMax50Character() {
    Object[] violation = validator.validateValue(Position.class, "jobTitle","long invalid job title more than fifty characters not just 49").toArray();
    Assertions.assertEquals(
            "Please enter a job title between 3 and 50 characters.",
            ((ConstraintViolation) violation[0]).getMessage());
  }

  @Test
  public void testJobTitleLengthMin3Character() {
    Object[] violation = validator.validateValue(Position.class, "jobTitle","d").toArray();
    Assertions.assertEquals(
            "Please enter a job title between 3 and 50 characters.",
            ((ConstraintViolation) violation[0]).getMessage());
  }

  @Test
  public void testLocationLengthMax50Character() {
    Object [] violation = validator.validateValue(Position.class, "location","").toArray();
    Assertions.assertEquals(
            "Please enter a location between 1 and 50 characters.",
            ((ConstraintViolation) violation[0]).getMessage());
  }

  @Test
  public void testLocationLengthMin1Character() {
    Object [] violation = validator.validateValue(Position.class, "location",
            "location shouldnt be this long no jobs in Taumatawhakatangihangakoauauotamateaturipukakapikimaungahoronukupokaiwhenuakitanatahu")
            .toArray();
    Assertions.assertEquals(
            "Please enter a location between 1 and 50 characters.",
            ((ConstraintViolation) violation[0]).getMessage());
  }
}
