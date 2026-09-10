package lk.ijse.boat_reservation.controller;

import lk.ijse.boat_reservation.constant.CommonResponse;
import lk.ijse.boat_reservation.dto.BoatCategoryDTO;
import lk.ijse.boat_reservation.service.BoatCategoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static lk.ijse.boat_reservation.constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.boat_reservation.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping("/api/boat-categories")
public class BoatCategoryController {

    private final BoatCategoryService boatCategoryService;

    public BoatCategoryController(BoatCategoryService boatCategoryService) {
        this.boatCategoryService = boatCategoryService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveCategory(@RequestBody BoatCategoryDTO categoryDTO) {
        boatCategoryService.saveCategory(categoryDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateCategory(@RequestBody BoatCategoryDTO categoryDTO) {
        BoatCategoryDTO updated = boatCategoryService.updateCategory(categoryDTO.getCategoryId(), categoryDTO);
        return new CommonResponse(OPERATION_SUCCESS, updated, SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteCategory(@PathVariable long categoryId) {
        boatCategoryService.deleteCategory(categoryId);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getCategoryById(@PathVariable long categoryId) {
        BoatCategoryDTO category = boatCategoryService.getCategoryById(categoryId);
        return new CommonResponse(OPERATION_SUCCESS, category, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllCategories() {
        List<BoatCategoryDTO> categories = boatCategoryService.getAllCategories();
        return new CommonResponse(OPERATION_SUCCESS, categories, SUCCESS_MESSAGE);
    }

    //search ek passe add krmu.Godak category nathi nisa all withrk thbbth shape.
    //delete ek wenas krnna one
}