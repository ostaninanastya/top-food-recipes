package com.topfood.recipes.like.controller;

import com.topfood.recipes.common.enums.ErrorCodeMap;
import com.topfood.recipes.common.enums.ErrorCodes;
import com.topfood.recipes.like.model.Like;
import com.topfood.recipes.like.service.LikeService;
import com.topfood.recipes.recipe.model.Recipe;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/like")
public class LikeRestController {
    @Autowired
    private LikeService likeService;

    @ApiOperation(value = "Get all likes", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = GET)
    public ResponseEntity<List<Like>> getAllLikes(@ApiParam(value = "Recipe", required = true) @PathVariable Recipe recipe) {
        List<Like> likes = likeService.findByRecipe(recipe);
        return new ResponseEntity<List<Like>>(likes, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new like", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = POST)
    public ResponseEntity<? extends Object> createLike(@RequestBody Like like) {
        ErrorCodes code = likeService.add(like);

        if (!code.equals(ErrorCodes.OK))
            return new ResponseEntity<String>(ErrorCodeMap.errors.get(code), HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<Like>(like, HttpStatus.OK);
    }

}
