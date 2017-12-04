package com.topfood.recipes.cuisine.controller;

import com.topfood.recipes.common.enums.ErrorCodeMap;
import com.topfood.recipes.common.enums.ErrorCodes;
import com.topfood.recipes.cuisine.model.Cuisine;
import com.topfood.recipes.cuisine.service.CuisineService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/cuisine")
public class CuisineRestController {
    @Autowired
    private CuisineService cuisineService;

    @ApiOperation(value = "Get all cuisines", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = GET)
    public ResponseEntity<List<Cuisine>> getAllCuisines() {
        List<Cuisine> cuisines = cuisineService.findAll();
        return new ResponseEntity<List<Cuisine>>(cuisines, HttpStatus.OK);
    }

    @ApiOperation(value = "Get cuisine by id", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cuisine> getCuisine(@ApiParam(value = "Cuisine id", required = true) @PathVariable String id) {
        Cuisine cuisine = cuisineService.getByID(id);
        return new ResponseEntity<Cuisine>(cuisine, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a cuisine", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = POST)
    public ResponseEntity<? extends Object> createCuisine(@RequestBody Cuisine cuisine) {
        ErrorCodes code = cuisineService.add(cuisine);

        if (!code.equals(ErrorCodes.OK))
            return new ResponseEntity<String>(ErrorCodeMap.errors.get(code), HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<Cuisine>(cuisine, HttpStatus.OK);
    }

    @ApiOperation(value = "Update a cuisine", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<Cuisine> updateCuisine(@RequestBody Cuisine cuisine) {
        cuisineService.update(cuisine);
        return new ResponseEntity<Cuisine>(cuisine, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a cuisine", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Cuisine> deleteCuisine(@ApiParam(value = "Cuisine id", required = true) @PathVariable String id) {
        cuisineService.delete(id);
        return new ResponseEntity<Cuisine>(HttpStatus.OK);
    }

}
