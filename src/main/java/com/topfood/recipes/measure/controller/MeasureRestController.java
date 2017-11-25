package com.topfood.recipes.measure.controller;

import com.topfood.recipes.common.Enums.ErrorCodeMap;
import com.topfood.recipes.common.Enums.ErrorCodes;
import com.topfood.recipes.measure.model.Measure;
import com.topfood.recipes.measure.service.MeasureService;
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
@RequestMapping("/api/measure")
public class MeasureRestController {
    @Autowired
    private MeasureService measureService;

    @ApiOperation(value = "Get all measures", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = GET)
    public ResponseEntity<List<Measure>> getAllCuisines() {
        List<Measure> measures = measureService.findAll();
        return new ResponseEntity<List<Measure>>(measures, HttpStatus.OK);
    }

    @ApiOperation(value = "Get measure by id", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Measure> getMeasure(@ApiParam(value = "Measure id", required = true) @PathVariable String id) {
        Measure measure = measureService.getByID(id);
        return new ResponseEntity<Measure>(measure, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a measure", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = POST)
    public ResponseEntity<? extends Object> createMeasure(@RequestBody Measure measure) {
        ErrorCodes code = measureService.add(measure);

        if (!code.equals(ErrorCodes.OK))
            return new ResponseEntity<String>(ErrorCodeMap.errors.get(code), HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<Measure>(measure, HttpStatus.OK);
    }

    @ApiOperation(value = "Update a measure", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<Measure> updateCuisine(@RequestBody Measure measure) {
        measureService.update(measure);
        return new ResponseEntity<Measure>(measure, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a measure", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Measure> deleteCuisine(@ApiParam(value = "Measure id", required = true) @PathVariable String id) {
        measureService.delete(id);
        return new ResponseEntity<Measure>(HttpStatus.OK);
    }

}
