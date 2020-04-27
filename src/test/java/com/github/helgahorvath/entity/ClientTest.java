package com.github.helgahorvath.entity;

import com.github.helgahorvath.repository.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientTest {

  private Validator validator;
  @Autowired
  private ClientRepository clientRepository;

  @Before
  public void setup() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
    clientRepository.deleteAll();
    Client client = new Client();
    client.setEmail("mmmmmmmm@nnnnn.com");
    client.setName("valid name");
    clientRepository.save(client);

  }

  @Test
  public void testInvalidEmailNotAccepted() {
    Object[] violation = validator.validateValue(Client.class, "email", "not.an.email").toArray();
    Assertions.assertEquals("Please enter a valid email",
            ((ConstraintViolation) violation[0]).getMessage());
  }

  @Test
  public void testEmailLengthMax50Character() {
    Object[] violation = validator.validateValue(Client.class, "email",
            "veryveryloooooooongemail@morethan50character.invalid").toArray();
    Assertions.assertEquals("Please enter an email between 0 and 50 characters.",
            ((ConstraintViolation) violation[0]).getMessage());
  }

  @Test
  public void testNameLengthMin3Character() {
    Object[] violation = validator.validateValue(Client.class, "name","no").toArray();
    Assertions.assertEquals("Please enter a name between 0 and 50 characters.",
            ((ConstraintViolation) violation[0]).getMessage());
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testEmailIsUnique() {
    Client client = new Client();
    client.setEmail("mmmmmmmm@nnnnn.com");
    client.setName("name");
    clientRepository.save(client);
  }
}
