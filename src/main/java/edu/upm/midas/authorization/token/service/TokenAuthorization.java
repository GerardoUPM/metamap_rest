package edu.upm.midas.authorization.token.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.upm.midas.authorization.model.ValidationResponse;
import edu.upm.midas.authorization.service.AuthResourceService;
import edu.upm.midas.authorization.token.component.JwtTokenUtil;

import edu.upm.midas.model.receiver.Configuration;
import edu.upm.midas.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
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


    public Response validateService(String userToken, Configuration configuration, String path, Device device){
        Response response = new Response();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String token = jwtTokenUtil.generateToken( userToken, gson.toJson(configuration), path, device );

        System.out.println( "Call Authorization API... " );
        ValidationResponse validationResponse = authResourceService.validationServiceByToken( token );
        response.setAuthorization( validationResponse.isAuthorized() );
        response.setAuthorizationMessage( validationResponse.getMessage() );
        response.setToken( userToken );

        return response;

    }


}