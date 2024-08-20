package com.codeaddi.mrc_resources.controller;

import static org.mockito.Mockito.when;

import com.codeaddi.mrc_resources.controller.db.BladeService;
import com.codeaddi.mrc_resources.testUtils.TestData;
import com.codeaddi.mrc_resources.testUtils.TestUtils;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.List;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BladeControllerTests {

  private TestUtils testUtils = new TestUtils();

  @Mock BladeService bladeService;
  @InjectMocks BladeController bladeController;

  @BeforeEach
  public void setUpMockSessionController() {
    RestAssuredMockMvc.standaloneSetup(bladeController);
  }

  @Test
  void getAllBoats_boatsInDb_returnsListOfBoats() throws JSONException {
    when(bladeService.getAllBlades()).thenReturn(List.of(TestData.blade1));

    String expectedBody = testUtils.convertToJson(List.of(TestData.blade1));

    String actual =
        RestAssuredMockMvc.when()
            .get("/blades/get_all")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .body()
            .asString();

    JSONAssert.assertEquals(expectedBody, actual, false);
  }
}
