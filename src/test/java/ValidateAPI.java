
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;

public class ValidateAPI {
    ObjectMapper objectMapper = new ObjectMapper();
    SoftAssert softAssert = new SoftAssert();
    ReadContext readContext;
    Response jsonResponse;

    @Test
    public void validateAPIResponse() throws IOException {
        //Hit the API to get the Response
        jsonResponse = given().when().get("https://interviewtestapi.azurewebsites.net/api/candidate");
        //Check the status code , if 200 proceed with further validation
        if (jsonResponse.getStatusCode() == 200) {
            //Check the sorting of lastUpdated field
            sortDescendingOrder();
            //Check for NULL using jackson , 2 ways while creating the DTO and another one cheking during validation
            validateNullCheck();
        }


    }

    //Sort in Descending Order
    private void sortDescendingOrder() {
        //Parse the response and store in a variable
        readContext = JsonPath.parse(jsonResponse.getBody().asPrettyString());
        //Get the required fields value in a list from the parsed json by passing the query string
        List<String> lastUpdatedDates = readContext.read("$..lastUpdated");
        //Sort the collection in decending order
        Collections.sort(lastUpdatedDates, Collections.reverseOrder());
        //print the sorted list
        System.out.println(lastUpdatedDates);
    }


    //Method to to check the Null values
    private void validateNullCheck() throws JsonProcessingException {
        //Check for NULL using jackson , 2 ways while creating the DTO and another one cheking during validation
        //Convert the response to DTO
        Root[] myJson = objectMapper.readValue(jsonResponse.asPrettyString(), Root[].class);
        //get all the fields in a list
        List<Root> allAttributes = new ArrayList<>(Arrays.asList(myJson));
        //loop through the list to get all the field value and add validation as per need
        for (Root value : allAttributes) {
            softAssert.assertFalse(value.getLastUpdated().isEmpty(), "Check for Null value in LastUpdated field");
            softAssert.assertFalse(value.getLocation().isEmpty(), "Check for Null value in Location field");
            softAssert.assertFalse(value.getLng().toString().isEmpty(), "Check for Null value in Lng field");
            softAssert.assertFalse(value.getLat().toString().isEmpty(), "Check for Null value in Lat field");
            softAssert.assertFalse(value.getDriverName().isEmpty(), "Check for Null value in Drivername field");
            softAssert.assertFalse(value.getPlateNo().isEmpty(), "Check for Null value in PlateNo field");
        }
        softAssert.assertAll();
    }

}
