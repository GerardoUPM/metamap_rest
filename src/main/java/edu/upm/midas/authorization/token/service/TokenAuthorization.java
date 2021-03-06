package edu.upm.midas.authorization.token.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.upm.midas.authorization.model.ValidationResponse;
import edu.upm.midas.authorization.service.AuthResourceService;
import edu.upm.midas.authorization.token.component.JwtTokenUtil;

import edu.upm.midas.model.receiver.Configuration;
import edu.upm.midas.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gerardo on 26/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project tvp_rest
 * @className TokenAuthorization
 * @see
 */
@Service
public class TokenAuthorization {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthResourceService authResourceService;

    @Value("${my.service.authorization.url}")
    private String authorization_url;
    @Value("${my.service.authorization.path}")
    private String authorization_path;


    public Response validateService(String userToken, Configuration configuration, String url, Device device){
        Response response = new Response();
/*
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String token = jwtTokenUtil.generateToken( userToken, gson.toJson(configuration), url, device );

        System.out.println( "Call Authorization API... (" + authorization_url + authorization_path + ")");
        ValidationResponse validationResponse = authResourceService.validationServiceByToken( token );
        response.setAuthorization( validationResponse.isAuthorized() );
        response.setAuthorizationMessage( validationResponse.getMessage() );
        response.setToken( userToken );
*/

        return response;

    }


}
