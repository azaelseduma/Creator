package com.creator.web.controller.api.user;

import com.creator.ApplicationTest;
import com.creator.model.user.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Azael on 2017/08/28.
 */
public class UserRestControllerTest extends ApplicationTest {
    public static final String REST_SERVICE_URI = "http://localhost:9000/api";
    public static final String USERNAME = "aseduma";
    public static final String PASSWORD = "xbox360";

    private HttpHeaders getHeaders() {
        String plainCredentials = USERNAME + ":" + PASSWORD;
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }


    /*
     * Send a GET request to get a specific user.
     */
    @Test
    public void listAllUsers() {
        try {
            HttpEntity<?> request = new HttpEntity<>(getHeaders());

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<User[]> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/user/", HttpMethod.GET, request, User[].class);

            System.out.println(responseEntity.getBody());

            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                List<User> users = Arrays.asList(responseEntity.getBody());
                for (User user : users) {
                    System.out.println(user.toString());
                }
            } else {
                System.out.println(responseEntity.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUser() {
        try {
            System.out.println("\nTesting getUser API----------");
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> request = new HttpEntity<>(getHeaders());
            ResponseEntity<User> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/user/aseduma", HttpMethod.GET, request, User.class);
            System.out.println(responseEntity.getStatusCode());
            User user = responseEntity.getBody();
            System.out.println(user.toString());
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    /*
     * Send a POST request to create a new user.
     */
    @Test
    public void createUser() {
        try {
            System.out.println("\nTesting create User API----------");
            RestTemplate restTemplate = new RestTemplate();
            User user = new User();
            user.setUsername("api");
            user.setEmail("api@gmail.com");
            user.setName("Api");
            user.setSurname("Rest");
            user.setPassword("xbox360");
            user.setActive(true);
            HttpEntity<Object> request = new HttpEntity<>(user, getHeaders());
            URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/user/save", request, User.class);
            System.out.println("Location : " + uri.toASCIIString());
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    /*
     * Send a PUT request to update an existing user.
     */
    @Test
    public void updateUser() {
        try {
            System.out.println("\nTesting update User API----------");
            RestTemplate restTemplate = new RestTemplate();
            User user = new User();
            user.setUsername("api");
            user.setEmail("ipa@gmail.com");
            user.setName("API");
            user.setSurname("REST");
            user.setPassword("api_rest");
            user.setActive(true);
            HttpEntity<Object> request = new HttpEntity<>(user, getHeaders());
            ResponseEntity<User> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/user/update/api", HttpMethod.PUT, request, User.class);
            System.out.println(responseEntity.getStatusCode());
            User _user = responseEntity.getBody();
            System.out.println(_user.toString());
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    /*
     * Send a DELETE request to delete a specific user.
     */
    @Test
    public void deleteUser() {
        try {
            System.out.println("\nTesting delete User API----------");
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> request = new HttpEntity<>(getHeaders());
            ResponseEntity<User> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/user/delete/api", HttpMethod.DELETE, request, User.class);
            System.out.println(responseEntity.getStatusCode() + " " + responseEntity.getBody());
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    /*
     * Full test pack
     */
    @Test
    public void testUserRestControllerPack() {
        listAllUsers();
        getUser();

        createUser();
        listAllUsers();

        updateUser();
        listAllUsers();

        deleteUser();
        listAllUsers();
    }


}
