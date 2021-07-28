package com.busleiman.oauthservice.clients;

import com.busleiman.oauthservice.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "users-service")
public interface UserFeignClient{

    @RequestMapping(method = RequestMethod.GET, value = "/users/username/{username}")
    ResponseEntity<User> findUserByUsername(@PathVariable("username") String username);

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
     User changeStateUser(@RequestBody User user, @PathVariable("id")Long id);
}
