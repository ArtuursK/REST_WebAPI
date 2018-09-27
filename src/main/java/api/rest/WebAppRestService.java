package api.rest;

import api.rest.dto.Request;
import api.rest.dto.UpdateRequestDO;
import entity.User;
import enums.EGeneralErrorCode;
import enums.EUserErrorDetail;
import exceptions.BusinessException;
import exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserManagerService;

import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class WebAppRestService {

    private UserManagerService userManagerService = new UserManagerService();

    @RequestMapping(value="/users", method = RequestMethod.GET, headers="Accept=application/json")
    public List<User> getAllUsers(@RequestParam(required = false, value = "username") String username) throws SQLException, BusinessException {
        if(username != null){
            System.out.println("Searching for user with username: " + username);
            return userManagerService.getUser(username);
        }else{
            return userManagerService.getAllUsers();
        }
    }

    @RequestMapping(value="/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity createUser(@RequestBody Request request) throws SQLException, BusinessException {
        if (request.getUsername() == null) {
            throw BusinessException.create(EGeneralErrorCode.BAD_REQUEST, EUserErrorDetail.USERS_USERNAME_MISSING.getMessage());
        }else if (request.getPassword().equals("") || request.getPassword().isEmpty() || request.getPassword() == null) {
            throw BusinessException.create(EGeneralErrorCode.BAD_REQUEST, EUserErrorDetail.USER_PASSWORD_CAN_NOT_BE_EMPTY.getMessage());
        }else if (request.getUsername().equals("")) {
            throw BusinessException.create(EGeneralErrorCode.BAD_REQUEST, EUserErrorDetail.USERS_USERNAME_INVALID.getMessage());
        }else if (userManagerService.usernameExists(request.getUsername())) {
            throw BusinessException.create(EGeneralErrorCode.BAD_REQUEST, EUserErrorDetail.USERS_USERNAME_ALREADY_EXISTS.getMessage());
        }
        User createdUser = userManagerService.addUser(request.getUsername(), request.getPassword());
        if(createdUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }else{
            throw BusinessException.create(EGeneralErrorCode.BAD_REQUEST, EUserErrorDetail.USERS_CODE_MISSING.getMessage());
        }
    }

    @RequestMapping(value="/user/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity updateUser(@RequestBody UpdateRequestDO request, @PathVariable("username") String username) throws Exception {

        User updatedUser = userManagerService.updateUser(username, request.getPassword(), request.getStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
    }

    @RequestMapping(value="/user/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("username") String username) throws BusinessException, SQLException {
        userManagerService.deleteUser(username);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(ex.getErrorCode());
        error.setErrorCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

























    /*
    @RequestMapping(value="/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity createUser(@RequestBody Request request) throws SQLException {
        ErrorResponse errorResponse = new ErrorResponse();
        if (request.getUsername() == null) {
            errorResponse.setError("Username is required!");
            throw new IllegalArgumentException();

        }else if (request.getUsername().equals("")) {
            errorResponse.setError("Username should not be empty!");
        }else if (userManagerService.usernameExists(request.getUsername())) {
            errorResponse.setError("User with this username already exists!");
        }else{
            errorResponse.setError("User could not be created!");
        }
        User createdUser = userManagerService.addUser(request.getUsername());
        if(createdUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
*/
/*
            throw new BusinessException(EFapiaoErrorCode.FAPIAO_JIRA_CREATE_TICKET_REQUEST_FAILED,
                    "Response from OpenInformer is missing values to create a propper JIRA ticket");
*/

/*
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "This is an exception : Username is required!")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler(){

    }
*/

/*
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Message")
    @ExceptionHandler(BusinessException.class)
    public void badIdExceptionHandler(){

    }
*/
/*
    @RequestMapping(value="/user/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity updateUser(@RequestBody Request request) throws SQLException {

        //userManagerService.getUsers(username);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
*/




    /*
    public String addUser(@RequestParam(value="username", defaultValue="") Request request) throws SQLException {
        System.out.println("*********************************************    " + username + "    *********************************************");

        return String.format(template, username);
    }
    */
    /*
    @POST
    @Path("tests/results")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get the results for a particular test", tags = "Tests")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Success", response = TestResultContainer.class),
        @ApiResponse(code = 404, message = "Test not found", response = ErrorResponse.class)
    })
    public Response testResults(@ApiParam(required = true) @NotNull TestResultRequest request) throws BusinessException {
        final Map<String, List<TestResult>> testResults = testService.getTestResults(request.getTestRunId(),
            request.getExecutedOn(), request.getUri(), request.getEnvironment(), request.getAll() == null ? false : request.getAll());
        if (testResults == null || testResults.isEmpty()) {
            throw BusinessException.create(EGeneralErrorCode.NOT_FOUND, "No test results found");
        }
        final String testPackage = testResults.values().iterator().next().get(0).getTestPackage();
        final TestResultContainer result = new TestResultContainer()
            .testRunId(request.getTestRunId())
            .executedOn(request.getExecutedOn())
            .testPackage(testPackage)
            .uri(request.getUri());
        testResults.forEach((env, results) -> result.addTestExecutionResultsItem(new TestExecutionResult()
            .environment(env)
            .results(results.stream()
                .map(res -> new com.bmw.odm.common.integrationTest.api.dto.TestResult()
                    .startedAt(res.getStartedAt())
                    .finishedAt(res.getFinishedAt())
                    .status(res.getStatus())
                    .result(res.getResult()))
                .collect(Collectors.toList()))));
        return Response.status(Status.OK).entity(result).build();
    }

     */

}