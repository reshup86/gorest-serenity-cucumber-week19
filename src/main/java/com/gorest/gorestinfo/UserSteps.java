package com.gorest.gorestinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {
    @Step("Creating user with name : {0}, gender:{1},staus:{2},email:{3}")
    public ValidatableResponse createUser(String name, String gender, String email, String status, String token) {

        UserPojo userPojo = UserPojo.getUserPojo(name,gender,email,status,token);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();
    }

    @Step("Get user info by id:{0}")
    public HashMap<String, Object> getUserInfoById(int userID, String token){
        HashMap<String, Object> userData = SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then()
                .extract().path("");
        return userData;
    }

    @Step("Update user details with name :{0},gender:{1},userid:{2},email:{3},status:{4}")
    public ValidatableResponse updateUser(String name,String gender, int userID, String email, String status, String token){
        UserPojo userPojo = UserPojo.getUserPojo(name,gender,email,status,token);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("userID",userID)
                .body(userPojo)
                .when()
                .patch(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }

    @Step
    public ValidatableResponse getUserByID(int userID, String token) {

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then();
    }

    @Step("Delete User with ID:{0}")
    public ValidatableResponse deleteUser(int userID, String token) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("userID",userID)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }

    @Step("Getting Users information")
    public ValidatableResponse getAllUsersInfo(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_USER)
                .then();
    }

    @Step("Getting the user information with email: {0}")
    public HashMap<String, Object> getUserInfoByEmail(String email){
        String p1 = "findAll{it.email == '";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_USER)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + email + p2);
    }

    @Step("Getting the user information with name")
    public HashMap<String, Object> getUserByName(String name){

        String p1 = "findAll{it.name == '";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_USER)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);
    }
}
