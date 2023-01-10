package com.gorest.cucumber.steps;

import com.gorest.gorestinfo.UserSteps;
import com.gorest.utils.PropertyReader;
import com.gorest.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;

public class MyStepdefs {
    static String token = PropertyReader.getInstance().getProperty("token");

    static String name = "ami mathur" + TestUtils.getRandomValue();
    static String email = "ami" + TestUtils.getRandomValue() + "@email.com";
    static String gender = "female";
    static String status = "active";
    static ValidatableResponse response;
    static int userID;

    @Steps
    UserSteps usersSteps;
    @When("^User sends a GET request to users endpoint$")
    public void userSendsAGETRequestToUsersEndpoint() {
        response = usersSteps.getAllUsersInfo();
    }
    @Then("^User must get back a valid status code 200$")
    public void userMustGetBackAValidStatusCode() {
        response.statusCode(200);
    }
    @When("^User creates a new user by providing the information name \"([^\"]*)\" gender \"([^\"]*)\" email \"([^\"]*)\" status \"([^\"]*)\"$")
    public void userCreatesANewUserByProvidingTheInformationNameGenderEmailStatus(String name, String gender, String email, String status){
        response = usersSteps.createUser(name, gender, email, status, token);
        userID = response.log().all().extract().path("id");
    }
    @Then("^User verifies that the user is created$")
    public void userVerifiesThatTheUserIsCreated() {
        response.statusCode(201);
    }

    @When("^User updates created user by providing the information name \"([^\"]*)\" email \"([^\"]*)\"$")
    public void userUpdatesCreatedUserByProvidingTheInformationNameEmail(String _name, String _email){
        name = _name + "updated";
        //  email = TestUtils.getRandomValue() + _email;
        response = usersSteps.updateUser(name, gender, userID,email, status, token);
    }
    @Then("^User verifies user is updated$")
    public void userVerifiesUserIsUpdated() {
        response.statusCode(200);
        HashMap<String, Object> userMap = response.log().all().extract().path("");
        Assert.assertThat(userMap, hasValue(name));
    }

    @When("^User delete created user using id $")
    public void userDeleteCreatedUserUsingIdAndToken()  {
        response = usersSteps.deleteUser(userID, token);
    }

    @Then("^User verifies user is deleted with status code 204$")
    public void userVerifiesUserIsDeletedWithStatusCode() {
        response.statusCode(204);
    }

    @Given("^GoRest application is running$")
    public void gorestApplicationIsRunning() {
    }

    @Then("^User verifies that the user with name \"([^\"]*)\" is created$")
    public void userVerifiesThatTheUserWithNameIsCreated(String name) {
        response.statusCode(201).log().body().body("name", equalTo(name));
    }

    @And("^User with name \"([^\"]*)\" is updated successfully$")
    public void userWithNameIsUpdatedSuccessfully(String _name)  {
        HashMap<?, ?> userMap = response.log().all().extract().path("");
        Assert.assertThat(userMap, hasValue(name));
    }

    @And("^User delete created user using name \"([^\"]*)\"$")
    public void userDeleteCreatedUserUsingName(String _name) {
        response = usersSteps.deleteUser(userID, token);
    }

    @Then("^User is deleted successfully from the application$")
    public void userIsDeletedSuccessfullyFromTheApplication() {
        response.statusCode(204);
        usersSteps.getUserByID(userID,token).statusCode(404);
    }

    @When("^User delete created user using id$")
    public void userDeleteCreatedUserUsingId() {
        response = usersSteps.deleteUser(userID, token);
    }
    @When("^User creates a new user by name \"([^\"]*)\" gender \"([^\"]*)\" email \"([^\"]*)\" status \"([^\"]*)\"$")
    public void userCreatesANewUserByNameGenderEmailStatus(String name, String gender, String email, String status) throws Throwable {
        response = usersSteps.createUser(name, gender, email, status, token);
        userID = response.log().all().extract().path("id");
    }
}