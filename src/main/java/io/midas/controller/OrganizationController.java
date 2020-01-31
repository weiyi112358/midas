package io.midas.controller;

import io.midas.model.Organization;
import io.midas.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import java.awt.*;

@RestController
@RequestMapping(value = "/org")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @RequestMapping(value = "/save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestBody Organization o)
    {
        boolean isSuccess = organizationService.save(o);
        if(isSuccess)
        {
            return "success";

        }
        else
        {
            return "fail";
        }
    }


}
